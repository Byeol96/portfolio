package com.code.eclass.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.code.eclass.demo.dto.BoardPage;
import com.code.eclass.demo.entity.*;
import com.code.eclass.demo.service.EclassService;

@Controller
@RequestMapping(value="/courses")
public class CourseController {
	
	@Autowired
	private EclassService eclassService;
	
	@PostMapping("/{classNumber}")
	public String classHome(@PathVariable int classNumber,Model theModel) {

		theModel.addAttribute("classNumber", classNumber);
		theModel.addAttribute("recentNoticeList",eclassService.recentNoticeList(classNumber));
		return "classHome";
	}

	
	
	@PostMapping("/comment/{classNumber}")
	public String commentPost(Comment comment,@PathVariable int classNumber,String function,Principal p,Model theModel) {
		
		comment.setUserName(p.getName());
		eclassService.saveComment(comment);
		int boardNumber = comment.getPostNumber();
		theModel.addAttribute("post",eclassService.readBoard(boardNumber));
		theModel.addAttribute("commentCollections",eclassService.getComments(boardNumber));
		
		return "post";
	}
	@PostMapping("/attendance/{classNumber}")
	public String attendance(@ModelAttribute @PathVariable int classNumber, Model theModel,Principal p) {
		

		LocalDate startDate = eclassService.getStartDay(classNumber);
		Map<Integer,String> attendanceInfos = eclassService.getAttendanceInfo(startDate, p.getName(),classNumber);
		theModel.addAttribute("lectureList", eclassService.lectureList(classNumber));
		theModel.addAttribute("attendanceInfos",attendanceInfos);
		theModel.addAttribute("weeks",eclassService.todayWeeks(startDate));
		return "attendancePage";
	}
	
//	@PostMapping("/{classNumber}/{boardNumber}")
//	public String searchBoard2(@ModelAttribute @PathVariable int classNumber,BoardPage boardPage,Model theModel) {
//		System.out.println("오긴 잘오는데?2");
//		boardPage=eclassService.boardList(boardPage);
//		theModel.addAttribute("boardList",boardPage);
//		return "board";
//	}

}
