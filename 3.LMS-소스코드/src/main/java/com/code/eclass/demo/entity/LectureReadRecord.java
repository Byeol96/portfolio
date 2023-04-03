package com.code.eclass.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="lectureReadRecord")
public class LectureReadRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="read_number")
	private int readNumber;
	
	
	@Column(name="username")
	private String username;
	
	@Column(name="lecture_number")
	private int lectureNumber;
	
	@Column(name="read_date")
	private LocalDateTime readDate;
	

	public LectureReadRecord (int lectureNumber,String username) {
		
		this.readDate = LocalDateTime.now();
		this.lectureNumber = lectureNumber;
		this.username = username;
	}
	

	public LectureReadRecord () {
		
		this.readDate = LocalDateTime.now();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getLectureNumber() {
		return lectureNumber;
	}

	public void setLectureNumber(int lectureNumber) {
		this.lectureNumber = lectureNumber;
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

	@Override
	public String toString() {
		return "LectureReadRecord [username=" + username + ", lectureNumber=" + lectureNumber + ", readNumber="
				+ readNumber + ", readDate=" + readDate + "]";
	}


}	

