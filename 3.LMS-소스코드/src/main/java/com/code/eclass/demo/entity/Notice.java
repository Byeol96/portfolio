package com.code.eclass.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="notice")
public class Notice {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="notice_number")
	private int noticeNumber;
	
	@Column(name="class_number")
	private int classNumber;
	
	@Column(name="notice_title")
	private String noticeTitle;
	
	@Column(name="notice_writer")
	private String noticeWriter;
	
	@Column(name="notice_date")
	private LocalDateTime noticeDate;
	
	@Column(name="notice_content")
	private String noticeContent;
	
	@Column(name="notice_views")
	private int noticeViews;
	
	public Notice() {
		
		noticeViews = 1;
		noticeDate = LocalDateTime.now();
	}
	public int getNoticeNumber() {
		return noticeNumber;
	}

	public void setNoticeNumber(int noticeNumber) {
		this.noticeNumber = noticeNumber;
	}

	public int getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public LocalDateTime getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(LocalDateTime noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public int getNoticeViews() {
		return noticeViews;
	}

	public void setNoticeViews(int noticeViews) {
		this.noticeViews = noticeViews;
	}

	@Override
	public String toString() {
		return "notice [noticeNumber=" + noticeNumber + ", classNumber=" + classNumber + ", noticeTitle=" + noticeTitle
				+ ", noticeWriter=" + noticeWriter + ", noticeDate=" + noticeDate + ", noticeContent=" + noticeContent
				+ ", noticeViews=" + noticeViews + "]";
	}
}
