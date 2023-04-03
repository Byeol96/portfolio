package com.code.eclass.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="board")
public class Board {
	@Column(name="username")
	private String userName;
	
	@Id
	@Column(name="board_number")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int boardNumber;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="board_views")
	private int boardViews;
	
	@Column(name="class_number")
	private int classNumber;
	
	@Column(name="board_date")
	private LocalDateTime boardDate;
	
	@Column(name="board_content")
	private String boardContent;
	
	@Column(name="board_private")
	private boolean boardPrivate;

	public Board() {
		
		boardDate = LocalDateTime.now();
		boardViews = 1;
	}
	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public int getBoardViews() {
		return boardViews;
	}

	public void setBoardViews(int boardViews) {
		this.boardViews = boardViews;
	}

	public int getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}

	public LocalDateTime getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(LocalDateTime boardDate) {
		this.boardDate = boardDate;
	}

	public boolean isBoardPrivate() {
		return boardPrivate;
	}

	public void setBoardPrivate(boolean boardPrivate) {
		this.boardPrivate = boardPrivate;
	}

	@Override
	public String toString() {
		return "Board [userName=" + userName + ", boardNumber=" + boardNumber + ", boardTitle=" + boardTitle
				+ ", boardViews=" + boardViews + ", classNumber=" + classNumber + ", boardDate=" + boardDate
				+ ", boardContent=" + boardContent + ", boardPrivate=" + boardPrivate + "]";
	}

	
	
	
	
}
