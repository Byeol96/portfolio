package com.code.eclass.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_number")
	private int commentNumber;
	
	@Column(name="post_number")
	private int postNumber;
	
	@Column(name="comment_content")
	private String commentContent;
	
	@Column(name="create_time")
	private LocalDateTime createTime;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="previous_comment_number")
	private Integer previousCommentNumber;
	


	public Comment() {
		
		createTime=LocalDateTime.now();
	}




	public Integer getPreviousCommentNumber() {
		return previousCommentNumber;
	}




	public void setPreviousCommentNumber(Integer previousCommentNumber) {
		this.previousCommentNumber = previousCommentNumber;
	}




	public int getCommentNumber() {
		return commentNumber;
	}



	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}



	public int getPostNumber() {
		return postNumber;
	}



	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}



	public String getCommentContent() {
		return commentContent;
	}



	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}



	public LocalDateTime getCreateTime() {
		return createTime;
	}



	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}




	@Override
	public String toString() {
		return "Comment [commentNumber=" + commentNumber + ", postNumber=" + postNumber + ", commentContent="
				+ commentContent + ", createTime=" + createTime + ", userName=" + userName + ", previousCommentNumber="
				+ previousCommentNumber + "]";
	}



	
	
}
