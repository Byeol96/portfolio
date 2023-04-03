package com.code.eclass.demo.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userinfo")
public class UserInfo {
	
	@Id
	@Column(name="username")
	private String username;

	@Column(name="email")
	private String email;
	
	@Column(name="major")
	private String major;
	
	@Column(name="etc")
	private String etc;
	
	@Column(name="name")
	private String name;
	
	@Column(name="birth")
	private LocalDate birth;
	
	public UserInfo() {
		
		etc ="";
	}
	
	public UserInfo(String username) {
		
		this.username = username;
	}
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getEtc() {
		return etc;
	}


	public void setEtc(String etc) {
		this.etc = etc;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getBirth() {
		return birth;
	}


	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public void setBirthDay(String birth) {
		this.birth = LocalDate.parse(birth, DateTimeFormatter.ISO_DATE);
	}
	
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", email=" + email + ", major=" + major + ", etc=" + etc + ", name="
				+ name + ", birth=" + birth + "]";
	}
	
	
}
