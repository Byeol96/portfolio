package com.code.eclass.demo.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="noticereadrecord")
public class NoticeReadRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="read_number")
	private int readNumber; 
	
	@Column(name="read_date")
	private LocalDateTime readDate;
	
	@Column(name="notice_number")
	private int noticeNumber;
	
	@Column(name="username")
	private String userName;
	
	public NoticeReadRecord() {}
	
	public NoticeReadRecord(int noticeNumber,String userName) {
		
		readDate = LocalDateTime.now();
		this.noticeNumber = noticeNumber;
		this.userName = userName;
	}
	public int getReadNumber() {
		return readNumber;
	}

	public void setReadNumber(int readNumber) {
		this.readNumber = readNumber;
	}

	public LocalDateTime getReadDate() {
		return readDate;
	}

	public void setReadDate(LocalDateTime readDate) {
		this.readDate = readDate;
	}

	public int getNoticeNumber() {
		return noticeNumber;
	}

	public void setNoticeNumber(int noticeNumber) {
		this.noticeNumber = noticeNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "noticeReadRecord [readNumber=" + readNumber + ", readDate=" + readDate + ", noticeNumber="
				+ noticeNumber + ", userName=" + userName + "]";
	}
}
