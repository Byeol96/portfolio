package com.code.eclass.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.eclass.demo.entity.Authorities;
import com.code.eclass.demo.entity.UserInfo;
import com.code.eclass.demo.service.EclassService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private EclassService eclassService;
	
	@PostMapping("/createMemberAccount")
	public String createMemberForm(String auth,Model model) {
		
		model.addAttribute("auth",auth);
		return"createAccount";
	}
	@PostMapping("/createAccount")
	public String createMember(UserInfo userInfo,String auth,String birthDay) {

	
		
		eclassService.createAccount(userInfo,auth);
		return"redirect:/";
	}
	@GetMapping("/member")
	public String member(Model model) {
		
		model.addAttribute("pageHeader","회원검색");
		return"adminPage";
	}
	
	
	@PostMapping("/userSearch")
	public String userSearch(String username,Model model) {
		
		System.out.println("username : "+username);
		List<UserInfo> userInfos = eclassService.searchUserInfos(username);
		model.addAttribute("pageHeader","회원선택");
		model.addAttribute("userInfos",userInfos);
		model.addAttribute("username",username);
		return"adminPage";
	}

	@PostMapping("/userModifyForm")
	public String userModifyForm(String modifyUserName,Model model) {
		
		UserInfo userInfo = eclassService.searchUserInfo(modifyUserName);
		String auth = eclassService.searchAuthority(modifyUserName);
		model.addAttribute("userInfo",userInfo);
		model.addAttribute("auth",auth);
		return"adminModifyUserForm";
	}
	
	
	
	@PostMapping("/modifyUserInfo")
	public String userModifyForm(UserInfo userInfo,@RequestParam(defaultValue="")String auth) {
		
		System.out.println("userInfo : "+userInfo);
		System.out.println("auth : "+auth);
		if(auth.equals("ROLE_ASSISTANT")) {
			
			eclassService.updateAuth(auth, userInfo.getUsername());
		}
		
		eclassService.saveUserInfo(userInfo);
		
	
		return"redirect:/";
	}
}
