package com.qingniao.partal.coontroller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingniao.core.pojo.cart.UserCart;
import com.qingniao.core.pojo.cart.UserItem;
import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.service.SkuService;
import com.qingniao.core.utils.Constants;
import com.qingniao.core.utils.LocalSessionProvider;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
public class CartController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private SkuService skuService;
	@Autowired
	private LocalSessionProvider localSessionProvider;
	@Autowired
	private JedisPool jedisPool;
	
	@RequestMapping("/buy/shopping.html")
	public String toCart(Long skuId, Integer amount, Model model, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		//非登陆下购物车存储
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		//创建购物车
		UserCart userCart = null;
		Cookie[] cookies = request.getCookies();
		userCart = findUserCartByCookies(cookies);
		//判断购物车是否为空，如果为空就初始化购物车
		if (userCart == null) {
			userCart = new UserCart();
		}
		//判断用户是否登陆
		String username = localSessionProvider.getAttribute(request, response, Constants.USER_NAME);
		if (username != null) {
			//登陆情况
			Jedis jedis = jedisPool.getResource();
			//把cookie中的信息保存到redis中
			List<UserItem> userItems = userCart.getUserItems();
			for (UserItem userItem : userItems) {
				if (jedis.hexists("userItem:"+username, userItem.getSku().getId().toString())) {
					jedis.hincrBy("userItem:"+username, userItem.getSku().getId().toString(), userItem.getAmount());
				} else {
					//保存购物项
					jedis.lpush("userCart:"+username, userItem.getSku().getId().toString());
					//保存skuId, amount
					jedis.hset("userItem:"+username, userItem.getSku().getId().toString(), userItem.getAmount().toString());
				}
			}
			userCart.clearCart();
			clearCart(request, response);
			//添加当前购买的商品到购物车,如果已经存在就追加
			if (skuId != null) {
				if (jedis.hexists("userItem:"+username, skuId.toString())) {
					jedis.hincrBy("userItem:"+username, skuId.toString(), amount);
				} else {
					jedis.lpush("userCart:"+username, skuId.toString());
					jedis.hset("userItem:"+username, skuId.toString(), amount.toString());
				}
			}
			//把redis中的数据加载到购物车中
			List<String> skuIds = jedis.lrange("userCart:"+username, 0, -1);
			for (String id : skuIds) {
				Sku sku = new Sku();
				sku.setId(Long.parseLong(id));
				UserItem userItem = new UserItem();
				userItem.setSku(sku);
				userItem.setAmount(Integer.parseInt(jedis.hget("userItem:"+username, id)));
				userCart.addUserItem(userItem);
			}
		} else {
			//非登陆情况
			//把购买的商品追加到购物车
			if (skuId != null) {
				userItemToCart(userCart, skuId, amount, response);
			}
		}
		showCart(userCart);
		//对购物车中的商品进行排序
		Collections.sort(userCart.getUserItems(), new Comparator<UserItem>() {
			@Override
			public int compare(UserItem o1, UserItem o2) {
				//倒序就是返回-1
				return -1;
			}
		});
		model.addAttribute("userCart", userCart);
		return "product/cart";
	}
	/**
	 * 
	 * 删除购物项
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/delete/userItem.html")
	public String userItemDelete(Long skuId, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		String username = localSessionProvider.getAttribute(request, response, Constants.USER_NAME);
		if (username != null) {
			//删除购物车和购物项
			Jedis jedis = jedisPool.getResource();
			jedis.lrem("userCart:"+username, 0, skuId.toString());//第二个参数表示删除的个数，0标识全部删除
			jedis.hdel("userItem:"+username, skuId.toString());
		} else {
			objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
			UserCart userCart = null;
			Cookie[] cookies = request.getCookies();
			userCart = findUserCartByCookies(cookies);
			userCart.delUserItem(skuId);
			//回显浏览器
			userItemToCart(userCart, response);
		}
		return "redirect:/buy/shopping.html";
	}
	/**
	 * 
	 * 商品数量追加
	 * @param skuId
	 * @param amount
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/userItem/addAmount.html")
	public void userItemAddAmount(Long skuId, Integer amount, HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		UserCart userCart = new UserCart();
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		userCart = findUserCartByCookies(request.getCookies());
		//追加商品的时候如果数量是重复的会对数量进行叠加
		userItemToCart(userCart, skuId, amount, response);
		//回显数据
		showCart(userCart);
		//数据写会浏览器
		JSONObject json = new JSONObject();
		json.put("productsAmount", userCart.getProductsAmount());
		json.put("price", userCart.getPrice());
		json.put("extra", userCart.getExtra());
		json.put("totalPrice", userCart.getTotalPrice());
		response.getWriter().write(json.toString());
	}
	/**
	 * 
	 * 清空购物车
	 */
	@RequestMapping("/shopping/clearCart.html")
	public String clearCart(HttpServletRequest request, HttpServletResponse response) {
		//清空cookie
		Cookie cookie = new Cookie(Constants.USER_CART, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/buy/shopping.html";
	}
	/**
	 * 
	 * 从cookie中查询购物车
	 * @param cookies
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	private UserCart findUserCartByCookies(Cookie[] cookies) throws JsonParseException, JsonMappingException, IOException {
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (Constants.USER_CART.equals(cookie.getName())) {
					String value = cookie.getValue();
					return objectMapper.readValue(value, UserCart.class);
				}
			}
		}
		return null;
	}
	
	/**
	 * 商品追加到购物车然后回写cookie
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * 
	 */
	private void userItemToCart(UserCart userCart, Long skuId, Integer amount, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		//创建sku对象
		Sku sku = new Sku();
		sku.setId(skuId);
		//创建购物项
		UserItem userItem = new UserItem();
		userItem.setAmount(amount);
		userItem.setSku(sku);
		//购物项添加到购物车
		userCart.addUserItem(userItem);
		//购物车转换成json字符串
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		StringWriter w = new StringWriter();
		objectMapper.writeValue(w, userCart);
		
		//回写浏览器
		Cookie cookie = new Cookie(Constants.USER_CART, w.toString());
		cookie.setMaxAge(60*60*24*7);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	/**
	 * 
	 * 购物车数据加载回显
	 * @param userCart
	 */
	private void showCart(UserCart userCart) {
		List<UserItem> userItems = userCart.getUserItems();
		if (userItems != null) {
			for (UserItem userItem : userItems) {
				Sku sku = skuService.loadSkuById(userItem.getSku().getId());
				userItem.setSku(sku);
			}
		}
	}
	
	private void userItemToCart(UserCart userCart, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		StringWriter w = new StringWriter();
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		objectMapper.writeValue(w, userCart);
		Cookie cookie = new Cookie(Constants.USER_CART, w.toString());
		cookie.setMaxAge(60*60*24*7);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}