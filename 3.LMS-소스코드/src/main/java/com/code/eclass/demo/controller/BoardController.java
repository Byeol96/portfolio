package com.code.eclass.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.code.eclass.demo.dto.BoardPage;
import com.code.eclass.demo.dto.InputData;
import com.code.eclass.demo.entity.Board;
import com.code.eclass.demo.entity.Comment;
import com.code.eclass.demo.service.EclassService;

@Controller
@RequestMapping(value="/courses/board")
public class BoardController {
	
	@Autowired
	private EclassService eclassService;
	
	@PostMapping("/{classNumber}")
	public String boardGet(@ModelAttribute @PathVariable int classNumber, Model theModel){
		
		BoardPage boardPage = new BoardPage(classNumber);
		boardPage=eclassService.boardList(boardPage);
		theModel.addAttribute("boardList",boardPage);
		return "board";
	}
	
	@PostMapping("/delete/{classNumber}")
	public String deleteBoard(int boardNumber,@PathVariable int classNumber,Model model) {
		
		eclassService.deletePost(boardNumber);
		model.addAttribute("url",new StringBuilder("/courses/board/").append(classNumber).toString());
		return "redirect";
	}
	
	@PostMapping("/search/{classNumber}")
	public String searchBoard(@ModelAttribute @PathVariable int classNumber,BoardPage boardPage,Model theModel) {
		boardPage=eclassService.boardList(boardPage);
		theModel.addAttribute("boardList",boardPage);
		return "board";
	}
	
	
	@PostMapping("/writeForm/{classNumber}")
	public String writeFormBoard(Board board,Model theModel) {
		
		theModel.addAttribute("title","게시글 작성");
		theModel.addAttribute("post",board);
		return "writeForm";
	}
	
	@PostMapping("/write/{classNumber}")
	public String writeBoard(Board board,Model theModel,@RequestParam(defaultValue="")String modify,@PathVariable int classNumber ) {
		
		eclassService.wirtePost(board);
		if(modify.length()==0) {
			theModel.addAttribute("url",new StringBuilder("/courses/board/").append(classNumber).toString());
			return "redirect";
		}
		
		
		theModel.addAttribute("url",new StringBuilder("/courses/board/read/").append(classNumber).toString());
		ArrayList<InputData> inputDataList = new ArrayList<>();
		inputDataList.add(new InputData("boardNumber",board.getBoardNumber()+""));
		theModel.addAttribute("inputs",inputDataList);
		return "redirect";
	}
	
	@PostMapping("/read/{classNumber}")
	public String readBoard(int boardNumber,Model theModel,@ModelAttribute @PathVariable int classNumber) {
		
		theModel.addAttribute("post",eclassService.readBoard(boardNumber));
		theModel.addAttribute("commentCollections",eclassService.getComments(boardNumber));
		return "post";
	}

	@PostMapping("/modifyForm/{classNumber}")
	public String modifyFormBoard(int boardNumber,Model theModel) {
		
		theModel.addAttribute("title","게시글 수정");
		theModel.addAttribute("post",eclassService.board(boardNumber));
		return "writeForm";
	}
	

	@PostMapping("/commentWrite/{classNumber}")
	public String commentWrite(Comment comment,@PathVariable int classNumber,Principal p,Model theModel) {
		
		System.out.println("write전 컨트롤러 확인 : "+comment);
		comment.setUserName(p.getName());
		eclassService.saveComment(comment);
		int boardNumber = comment.getPostNumber();
		
		theModel.addAttribute("url",new StringBuilder("/courses/board/read/").append(classNumber).toString());
		ArrayList<InputData> inputDataList = new ArrayList<>();
		inputDataList.add(new InputData("boardNumber",boardNumber+""));
		theModel.addAttribute("inputs",inputDataList);
		return "redirect";	
	}
	
	@PostMapping("/commentDelete/{classNumber}")
	public String commentDelete(@RequestParam("postNumber")int boardNumber,int commentNumber,@PathVariable int classNumber,Model theModel) {

		
		eclassService.deleteComment(commentNumber);


		theModel.addAttribute("url",new StringBuilder("/courses/board/read/").append(classNumber).toString());
		ArrayList<InputData> inputDataList = new ArrayList<>();
		inputDataList.add(new InputData("boardNumber",boardNumber+""));
		theModel.addAttribute("inputs",inputDataList);
		return "redirect";
	}
	
	@PostMapping("/commentUpdate/{classNumber}")
	public String commentUpdate(Comment comment,@PathVariable int classNumber,RedirectAttributes redirectAttributes,Model theModel) {
		
		System.out.println(comment);
		int boardNumber = comment.getPostNumber();
		System.out.println("boardNumber : "+boardNumber);
		eclassService.updateComment(comment);
		
		theModel.addAttribute("url",new StringBuilder("/courses/board/read/").append(classNumber).toString());
		ArrayList<InputData> inputDataList = new ArrayList<>();
		inputDataList.add(new InputData("boardNumber",boardNumber+""));
		theModel.addAttribute("inputs",inputDataList);
		return "redirect";
	}
	
	@GetMapping("/read/{classNumber}")
	public String readBoardGet(int boardNumber,@ModelAttribute @PathVariable int classNumber,HttpServletRequest request,Model theModel) {
		
		
			 theModel.addAttribute("post",eclassService.readBoard(boardNumber));
			 theModel.addAttribute("commentCollections",eclassService.getComments(boardNumber));
			 

		return "post";
	}

}
