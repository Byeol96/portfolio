package com.code.eclass.demo.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authorities {

	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="authority")
	private String authorities;
	
	public Authorities () {}
	
	public Authorities (String auth, String userName) {
		authorities = auth;
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getAuthorities() {
		return authorities;
	}



	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}



	@Override
	public String toString() {
		return "Authorities [userName=" + userName + ", authorities=" + authorities + "]";
	}
	
	
}
