package com.code.eclass.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="lecture")
public class Lecture {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lecture_number")
	private int lectureNumber;
	
	@Column(name="class_number")
	private int classNumber;
	
	@Column(name="lecture_title")
	private String lectureTitle;
	
	@Column(name="lecture_content")
	private String lectureContent;
	
	public LocalDate getLecture_endDate() {
		return lecture_endDate;
	}

	public void setLecture_endDate(LocalDate lecture_endDate) {
		this.lecture_endDate = lecture_endDate;
	}




	@Column(name="lecture_weeks")
	private int lectureWeeks;
	
	@Column(name="lecture_ordinal")
	private int lectureOrdinal;
	
	@Column(name="lecture_endDate")
	private LocalDate lecture_endDate;	
	
	public Lecture() {
		lecture_endDate =LocalDate.now();
	}
	
	public void setLocalDate(String date) {
		
		this.lecture_endDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE).plusDays(1);
	}
	public int getLectureNumber() {
		return lectureNumber;
	}

	public void setLectureNumber(int lectureNumber) {
		this.lectureNumber = lectureNumber;
	}

	public int getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}

	public String getLectureTitle() {
		return lectureTitle;
	}

	public void setLectureTitle(String lectureTitle) {
		this.lectureTitle = lectureTitle;
	}

	public String getLectureContent() {
		return lectureContent;
	}

	public void setLectureContent(String lectureContent) {
		this.lectureContent = lectureContent;
	}

	public int getLectureWeeks() {
		return lectureWeeks;
	}

	public void setLectureWeeks(int lectureWeeks) {
		this.lectureWeeks = lectureWeeks;
	}

	public int getLectureOrdinal() {
		return lectureOrdinal;
	}

	public void setLectureOrdinal(int lectureOrdinal) {
		this.lectureOrdinal = lectureOrdinal;
	}



	
	@Override
	public String toString() {
		return "Post [lectureNumber=" + lectureNumber + ", classNumber=" + classNumber + ", lectureTitle="
				+ lectureTitle + ", lectureContent=" + lectureContent + ", lectureWeeks=" + lectureWeeks
				+ ", lectureOrdinal=" + lectureOrdinal + ", lecture_endDate=" + lecture_endDate + "]";
	}
		
}	

