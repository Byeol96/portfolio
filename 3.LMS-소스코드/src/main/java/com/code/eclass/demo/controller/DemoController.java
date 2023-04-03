package com.code.eclass.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.code.eclass.demo.dto.Semester;
import com.code.eclass.demo.entity.ClassInfo;
import com.code.eclass.demo.entity.UserInfo;
import com.code.eclass.demo.entity.Users;
import com.code.eclass.demo.service.EclassService;

@Controller
public class DemoController {
	
	@Autowired
	private EclassService eclassService;
	
	@RequestMapping("/")
	public String home(@RequestParam(value="change",required=false, defaultValue= "false") boolean change,Principal p,Model model) {
		
		try {
			eclassService.myUserInfo(p.getName());
		}catch(NoResultException e) {
			model.addAttribute("userInfoExistence","false");
		}catch(NullPointerException ae) {
			
		}

		return "home";
	}

	@GetMapping("/passwordChange")
	public String passwordChange(RedirectAttributes redirectAttr) {
		redirectAttr.addFlashAttribute("passwordChange","true");
		return"redirect:/";
	}
	
		
	@RequestMapping("/myPage")
	public String boo2k(Model myModel,Principal principal,@RequestParam(value="semesterNumber",required=false, defaultValue= "0")int semesterNumber) {
		
		String userName = principal.getName();
		// 교수의 경우 다르게 만들기 
		List<Semester> semesterInfo;
		List<ClassInfo> classInfos;
		if(eclassService.confirmAuth(userName)) {
			
			semesterInfo =eclassService.getSemesterInfosForProfessor(userName);
			classInfos = eclassService.mySignUpInfoForProfessor(userName,semesterInfo.get(semesterNumber));
			
		}else {
			semesterInfo = eclassService.getSemesterInfos(userName);
			classInfos = eclassService.mySignUpInfo(userName,semesterInfo.get(semesterNumber));
			
		}
		
		myModel.addAttribute("nameMapper",eclassService.convertRealName(classInfos));
		myModel.addAttribute("courseList",classInfos);
		myModel.addAttribute("semesterInfos",semesterInfo);
		myModel.addAttribute("semesterNumber",semesterNumber);
		
		return "myPage";
	}

	
	
	//-------------------------------------------
	
	@RequestMapping("/modifyUserInfo") // 회원정보 누르면 그 회원에대한 정보가 나오게끔
	public String modifyUserInfo(UserInfo userInfo, Model myModel, Principal principal) {
	
		myModel.addAttribute("userInfoList", eclassService.myUserInfo(principal.getName()));
		System.out.println("히히: "+eclassService.myUserInfo(principal.getName()));
		return "modifyUserInfoForm"; // 여기다가 보내줌
		
	}
	
	@RequestMapping("/modifyUserInfoForm/save") // 기존 회원 정보에서 다른 값을 입력할 경우 save 함
	public String saveUserInfo(UserInfo userInfo, Model myModel) {
		System.out.println("수정할 userInfo : "+userInfo);
		eclassService.saveUserInfo(userInfo);
		return "redirect:/";
	}
	
	
	@RequestMapping("/modifyPassword") // 비밀번호 변경 누르면 기존 비밀번호가 나오도록 하기 위함
	public String modifyPassword(Users users, Model myModel, Principal principal) {
		
		myModel.addAttribute("userList", eclassService.modifyPassword(principal.getName()));
		return "modifyPasswordForm"; // 여기다가 보내줌
	}
	
	@PostMapping("/modifyPasswordForm/save") // 새로운 비밀번호를 누르면 save 해줌
	public String saveUserPassword(Users users,Model myModel) {
		eclassService.saveUserPassword(users);
		return "redirect:/";
	}
	
	@RequestMapping("/adminPage") // Users 의 임시 username 을가지고옴 
	public String goToCreateAccountForm(Users users, Model myModel, Principal principal) {

		return "adminPage";
	}
	
	//-----------------------------------------------------------------
	
	
	
	//error
	@RequestMapping("/createAccount/save") // 가지고온 임시 username 에 새로운 username을 넣고 거기서 생성할 계정에 100개 라고 적으면 적은 username을 시작으로 +100 까지 감
	public String saveAllOfAccount(@RequestParam("numberOfAccount") int numberOfAccount ,Users users, Model myModel) { // 파라미터값으로 100개를 입력하면 계정 100개 service 에서 for문 반복문 돌릴려고 
		eclassService.saveAccountList(users, numberOfAccount);
		return "redirect:/home";
	}
}
