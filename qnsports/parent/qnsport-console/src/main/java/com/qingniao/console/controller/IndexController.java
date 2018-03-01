package com.qingniao.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}
	@RequestMapping("/top.do")
	public String top() {
		return "top";
	}
	@RequestMapping("/main.do")
	public String main() {
		return "main";
	}
	@RequestMapping("/left.do")
	public String left() {
		return "left";
	}
	@RequestMapping("/right.do")
	public String right() {
		return "right";
	}
	@RequestMapping("/productMain.do")
	public String productMain() {
		return "product/product_main";
	}
	@RequestMapping("/productLeft.do")
	public String productLeft() {
		return "product/product_left";
	}
	
	
}
