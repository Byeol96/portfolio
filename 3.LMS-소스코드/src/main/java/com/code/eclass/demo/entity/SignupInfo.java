package com.code.eclass.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="signupinfo")
public class SignupInfo {
	
	
	@Column(name="username")
	private String userName;
	
	@Column(name="class_number")
	private int classNumber;
	
	@Id
	@Column(name="sign_number")
	private int signNumber;
	public String getUsername() {
		return userName;
	}


	public void setUsername(String username) {
		this.userName = username;
	}


	public int getClassNumber() {
		return classNumber;
	}


	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}


	@Override
	public String toString() {
		return "SignupInfo [username=" + userName + ", classNumber=" + classNumber + "]";
	}
	
	
}
