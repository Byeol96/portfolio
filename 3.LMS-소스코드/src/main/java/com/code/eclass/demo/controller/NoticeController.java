
package com.code.eclass.demo.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.eclass.demo.dto.InputData;
import com.code.eclass.demo.entity.Notice;
import com.code.eclass.demo.entity.NoticeReadRecord;
import com.code.eclass.demo.service.EclassService;

@Controller
@RequestMapping(value = "/courses/notice")
public class NoticeController {

	@Autowired
	private EclassService eclassService;

	@PostMapping("/{classNumber}")
	public String noticeGet(@ModelAttribute @PathVariable int classNumber, Model theModel) {
		
		theModel.addAttribute("title","공지사항");
		theModel.addAttribute("noticeList", eclassService.noticeList(classNumber));
		return "notice";
	}

	@PostMapping("/search/{classNumber}")
	public String searchNotice(String searchName, @ModelAttribute @PathVariable int classNumber,Model theModel) {
		
		
		theModel.addAttribute("noticeList", eclassService.searchNotice(classNumber, searchName));
		return "notice";
	}

	@PostMapping("/read/{classNumber}")
	public String readNotice(int noticeNumber, @ModelAttribute @PathVariable int classNumber, Model theModel,Principal p) {
		eclassService.saveNoticeReadRecord(new NoticeReadRecord(noticeNumber,p.getName()));
		theModel.addAttribute("specificNotice", eclassService.getSpecificNotice(noticeNumber));
		return "specificNotice";
	}

	@PostMapping("/delete/{classNumber}")
	public String deleteNotice(int noticeNumber, @ModelAttribute @PathVariable int classNumber, Model theModel) {

		eclassService.deleteNotice(noticeNumber);
		
		theModel.addAttribute("url",new StringBuilder("/courses/notice/").append(classNumber).toString());	
		return "redirect";
		
	}

	@PostMapping("/modifyForm/{classNumber}")
	public String modifyFormNotice(int noticeNumber, @ModelAttribute @PathVariable int classNumber, Model theModel) {

		theModel.addAttribute("specificNotice", eclassService.getSpecificNotice(noticeNumber));
		return "noticeWriteForm";
	}
//----------------------------------------------------------
	
	@PostMapping("/writeForm/{classNumber}")
	public String writeFormNotice(@ModelAttribute("specificNotice")Notice notice,@ModelAttribute @PathVariable int classNumber, Model theModel) {

		return "noticeWriteForm";
	}

	@PostMapping("/write/{classNumber}") // 작성한것을 글쓰기 버튼을 눌러서 공지사항에 올림 public
	public String writeNotice(Notice notice,Model theModel,@RequestParam(defaultValue="false")String modify,Principal p) {
			
		notice.setNoticeWriter(eclassService.getRealName(p.getName()));
		System.out.println("notice : "+notice);
			
			eclassService.saveNotice(notice);
			
			if(modify.equals("false")) {
				
				theModel.addAttribute("url",new StringBuilder("/courses/notice/").append(notice.getClassNumber()).toString());	
				return "redirect";
			}
			
			theModel.addAttribute("url",new StringBuilder("/courses/notice/read/").append(notice.getClassNumber()).toString());
			ArrayList<InputData> inputDataList = new ArrayList<>();
			inputDataList.add(new InputData("noticeNumber",notice.getNoticeNumber()+""));
			theModel.addAttribute("inputs",inputDataList);
			return "redirect";
	}// 새롭게 작성한것을 notice 로 보냄(수정 필요) 값 넘어가긴하나 페이지 error }
	
	@PostMapping("/unreadNotice/{classNumber}") // 메시지 안읽은거 구현 아직 못함 public
	public String showUnReadNotice(@PathVariable int classNumber, Model theModel,Principal principal) {
		
		theModel.addAttribute("title","안 읽은 공지사항");
		theModel.addAttribute("noticeList", eclassService.unreadList(classNumber, principal.getName()));
																									
		return "notice"; 
	}




}
