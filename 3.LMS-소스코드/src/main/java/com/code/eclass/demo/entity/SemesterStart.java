package com.code.eclass.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="semesterStart")
public class SemesterStart {
	
	@Column(name="class_year")
	private int classYear;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="semester")
	private String semester;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="start_number")
	private int startNumber;


	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	@Override
	public String toString() {
		return "SemesterStar [classYear=" + classYear + ", startDate=" + startDate + ", semester=" + semester
				+ ", startNumber=" + startNumber + "]";
	}
	
	
}
