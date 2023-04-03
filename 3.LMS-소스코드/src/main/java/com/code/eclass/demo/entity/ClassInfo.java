package com.code.eclass.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="classinfo")
public class ClassInfo {
	
	@Id
	@Column(name="class_number")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int classNumber;
	
	@Column(name="class_name")
	private String className;
	
	@Column(name="class_professor")
	private String classProfessor;
	
	@Column(name="class_year")
	private int classYear;
	
	@Column(name="class_semester")
	private String classSemester;
	
	
	public int getClassNumber() {
		return classNumber;
	}

	ClassInfo() {
		
	}
	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getClassProfessor() {
		return classProfessor;
	}


	public void setClassProfessor(String classProfessor) {
		this.classProfessor = classProfessor;
	}



	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}

	public String getClassSemester() {
		return classSemester;
	}


	public void setClassSemester(String classSemester) {
		this.classSemester = classSemester;
	}


	@Override
	public String toString() {
		return "ClassInfo [classNumber=" + classNumber + ", className=" + className + ", classProfessor="
				+ classProfessor + ", classYear=" + classYear + ", classSemester=" + classSemester + "]";
	}
	
	
}
