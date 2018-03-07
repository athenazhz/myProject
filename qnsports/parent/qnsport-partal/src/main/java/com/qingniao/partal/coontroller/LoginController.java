package com.qingniao.partal.coontroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 * 
 * 用户登录
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qingniao.core.pojo.user.User;
import com.qingniao.core.service.LoginService;
import com.qingniao.core.utils.Constants;
import com.qingniao.core.utils.LocalSessionProvider;

@Controller
public class LoginController {
	@Autowired
	private LocalSessionProvider localSessionProvider;
	@Autowired
	private LoginService loginService;
	// 处理get登陆请求，跳转登陆页面
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(Model model, String url) {
		model.addAttribute("url", url);
		return "buyer/login";
	}

	// 处理post登陆请求
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, String username, String password,
			String code, String url, Model model) {
		if (code != null) {
			String scode = localSessionProvider.getAttribute(request, response, Constants.USER_CODE);
			if (scode.toLowerCase().equals(code.toLowerCase())) {
				if (username != null && username.trim().length() > 0 && password != null && password.trim().length() > 0) {
					User cuser = loginService.getUser(username, password);
					if (cuser != null) {
						localSessionProvider.setAttribute(request, response, Constants.USER_NAME, cuser.getUsername());
						//跳转到点击登陆前的页面..
						return "redirect:"+url;
					} else {
						model.addAttribute("error", "账号密码错误！");
					}
				} else {
					model.addAttribute("error", "账号密码错误！");
				}
			} else {
				model.addAttribute("error", "验证码错误！");
			}
		} else {
			model.addAttribute("error", "验证码错误！");
		}
		return "buyer/login";
	}

	// 生成验证码
	@RequestMapping(value = "/login/getCode.html")
	public void getCodeImage(HttpServletRequest request, HttpServletResponse response) {
		BufferedImage img = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();

		Random r = new Random();

		Color c = new Color(200, 150, 255);

		g.setColor(c);

		g.fillRect(0, 0, 68, 22);

		StringBuffer sb = new StringBuffer();

		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		int index, len = ch.length;

		for (int i = 0; i < 4; i++) {

			index = r.nextInt(len);

			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt

			(255)));

			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));

			g.drawString("" + ch[index], (i * 15) + 3, 18);

			sb.append(ch[index]);

		}
		// 把上面生成的验证码放到Session域中
		localSessionProvider.setAttribute(request, response, Constants.USER_CODE, sb.toString());
		try {
			ImageIO.write(img, "JPG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}