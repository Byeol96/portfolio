package com.code.eclass.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.code.eclass.demo.dto.InputData;
import com.code.eclass.demo.entity.Lecture;
import com.code.eclass.demo.entity.LectureReadRecord;
import com.code.eclass.demo.service.EclassService;

@Controller
@RequestMapping(value="/courses/lecture")
public class LectureController {
	
	@Autowired
	private EclassService eclassService;
	
	
	@PostMapping("/{classNumber}")
	public String lectureGet(@ModelAttribute @PathVariable int classNumber, Model theModel){
		
		LocalDate startDate = eclassService.getStartDay(classNumber);
		theModel.addAttribute("lectureList", eclassService.lectureList(classNumber));
		theModel.addAttribute("weeks",eclassService.todayWeeks(startDate));
		return "lecture";
	}
	
	@PostMapping("/read/{classNumber}")
	public String lectureRead(int lectureNumber,@ModelAttribute @PathVariable int classNumber, Model theModel,Principal p) {
		
		
		eclassService.saveLecureReadRecord(new LectureReadRecord(lectureNumber,p.getName()));
		theModel.addAttribute("startDay", eclassService.getStartDay(classNumber));
		theModel.addAttribute("lecture", eclassService.checkLecture(lectureNumber));
		theModel.addAttribute("checkProf", eclassService.checkProf(classNumber));
		
		return "lecturePage";
	}
	
	
	@PostMapping("/delete/{classNumber}")
	public String lectureDelete(Lecture lecture,@PathVariable int classNumber,Model theModel) {
		
		eclassService.deleteContent(lecture.getLectureNumber());
		
		theModel.addAttribute("url",new StringBuilder("/courses/lecture/").append(classNumber).toString());
		return "redirect";
	}
	
	@PostMapping("/modifyForm/{classNumber}")
	public String lectureModifyForm(Lecture lecture,@PathVariable int classNumber,Model theModel) {
		
		theModel.addAttribute("lecture", eclassService.checkLecture(lecture.getLectureNumber())); 

		 return "lectureModifyForm";
	}
	

	@PostMapping("/modify/{classNumber}")
	public String lectureModify(Lecture lecture,@PathVariable int classNumber,Model theModel,String date1) {

		lecture.setLocalDate(date1);
		eclassService.modifyContent(lecture); 
	
		theModel.addAttribute("url",new StringBuilder("/courses/lecture/read/").append(classNumber).toString());
		ArrayList<InputData> inputDataList = new ArrayList<>();
		inputDataList.add(new InputData("lectureNumber",lecture.getLectureNumber()+""));
		theModel.addAttribute("inputs",inputDataList);
		return "redirect";
	
	}
	
	@PostMapping("/addForm/{classNumber}")
	public String lectureInsert(@ModelAttribute Lecture lecture,Model theModel) {
		
		System.out.println(lecture);
		return "lectureAddForm";
	}
	
	   
	@PostMapping("/add/{classNumber}")
	   public String lectureAdd(Lecture lecture,Model theModel,String lectureDate) {
		lecture.setLocalDate(lectureDate);
	    eclassService.addContent(lecture); 
	    
	    theModel.addAttribute("url",new StringBuilder("/courses/lecture/").append(lecture.getClassNumber()).toString());
		return "redirect";
	   }
	   
}
