package com.code.eclass.demo.dto;

import java.util.List;

import com.code.eclass.demo.entity.Board;

public class BoardPage {
	
	private boolean previous;
	private boolean next;
	private int start;
	private int end;
	private List<Board> boardList;
	private String searchTaget;
	private String searchContent;
	private int searchCount;
	private int pageNumber;
	private int classNumber;
	
	public BoardPage() {
		searchTaget="";
		searchContent="";
		pageNumber = 1;
	}
	public BoardPage(int classNumber) {
		this();
		this.classNumber=classNumber;
	}
	
	@Override
	public String toString() {
		return "BoardPage [previous=" + previous + ", next=" + next + ", start=" + start + ", end=" + end
				 + ", searchTaget=" + searchTaget + ", searchContent=" + searchContent
				+ ", searchCount=" + searchCount + ", pageNumber=" + pageNumber + ", classNumber=" + classNumber + ", boardList=" + boardList + "]";
	}
	public boolean isPrevious() {
		return previous;
	}
	public void setPrevious(boolean previous) {
		this.previous = previous;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public List<Board> getBoardList() {
		return boardList;
	}
	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}
	public String getSearchTaget() {
		return searchTaget;
	}
	public void setSearchTaget(String searchTaget) {
		this.searchTaget = searchTaget;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public int getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}
	
	public void restSetting() {
		
		if(pageNumber/10.0>1) previous = true;
		
		if(Math.round((searchCount-1)/100) > Math.round((pageNumber-1)/10)) next = true;
		
		start = ((pageNumber-1)/10)*10 +1;

		end = next? start+9: (searchCount-1)/10+1;
		
	}
}
