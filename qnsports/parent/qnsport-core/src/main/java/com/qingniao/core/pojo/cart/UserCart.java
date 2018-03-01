package com.qingniao.core.pojo.cart;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.qingniao.core.pojo.product.Sku;

/**
 * 
 * 购物车
 * @author Administrator
 *
 */
public class UserCart {
	private List<UserItem> userItems = new ArrayList<UserItem>();

	public List<UserItem> getUserItems() {
		return userItems;
	}

	public void setUserItems(List<UserItem> userItems) {
		this.userItems = userItems;
	}
	//添加购物项，如果是同款就合并
	public void addUserItem(UserItem userItem) {
		if (userItems.contains(userItem)) {
			for (UserItem item : userItems) {
				if (item.equals(userItem)) {
					item.setAmount(item.getAmount()+userItem.getAmount());
				}
			}
		} else {
			userItems.add(userItem);
		}
	} 
	//删除购物项
	public void delUserItem(Long skuId) {
		Sku sku = new Sku();
		sku.setId(skuId);
		UserItem userItem = new UserItem();
		userItem.setSku(sku);
		userItems.remove(userItem);
	}
	//因为不需要下面的属性转换成json所以加上jsonignor注解
	//小计
	@JsonIgnore
	public Integer getProductsAmount() {
		Integer sum = 0;
		for (UserItem userItem : userItems) {
			sum += userItem.getAmount();
		}
		return sum;
	}
	//运费 如果商品金额大于99则免运费
	@JsonIgnore
	public Float getExtra() {
		if (getPrice() < 99) {
			return 10f;
		} else {
			return 0f;
		}
	}
	//金额
	@JsonIgnore
	public Double getPrice() {
		Double price = 0d;
		for (UserItem userItem : userItems) {
			price += userItem.getAmount() * userItem.getSku().getPrice();
		}
		return price;
	}
	//总金额
	@JsonIgnore
	public Double getTotalPrice() {
		return getPrice() + getExtra();
	}
	//清空购物车
	public void clearCart() {
		userItems.clear();
	}
	
	
	
}
