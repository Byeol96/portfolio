package com.code.eclass.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String book() {
		
		return "plain-login";
	}
	@RequestMapping("/authenticateTheUser")
	public void a() {
		
		
	}
}
