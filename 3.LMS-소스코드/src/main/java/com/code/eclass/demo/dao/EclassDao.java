package com.code.eclass.demo.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.code.eclass.demo.dto.BoardPage;
import com.code.eclass.demo.dto.Semester;
import com.code.eclass.demo.entity.Authorities;
import com.code.eclass.demo.entity.Board;
import com.code.eclass.demo.entity.ClassInfo;
import com.code.eclass.demo.entity.Comment;
import com.code.eclass.demo.entity.Lecture;
import com.code.eclass.demo.entity.LectureReadRecord;
import com.code.eclass.demo.entity.Notice;
import com.code.eclass.demo.entity.NoticeReadRecord;
import com.code.eclass.demo.entity.UserInfo;
import com.code.eclass.demo.entity.Users;

public interface EclassDao {
	
	/*
	<영수>
	3. 수강 목록 부분에서 버튼 클릭시 그 해당과목의 과목번호를 가지고 페이지 이동 ( 1 )
	4. 공지 클릭할시 이동하는 페이지 (모든 공지 가지고 와서 보여주기 ) ( 4 )
	<지원>
	1 홈화면은 꾸미기만 하면됨 + 로그인 화면 ( 0 )
	5. 화면 표현해주기 ( 4 )
	<한별>
	2. 홈화면에서 내 수강목록이 보이는 페이지 ( 2 )
	6. 게시판 
	*/
	
	// id에 해당하는 사람의 수강정보리스트를 불러오는 친구
	public List<ClassInfo> mySignUpInfo(String id,Semester semester);
	
	
	//게시글 중에 특정 조건을 만족하는 친구 불러와주기
	public List<Board> boardList(int classNumber, String standard, String searchTarget);
	// 게시판에서 내 해당과목의 post 불러와주기
	public List<Board> boardList(int classNumber);
	// 게시판에 해
	public void setBoardList(BoardPage boardPage);
	// 게시판 제목 클릭시 해당 게시판 글 내용 불러오기
	public Board board(int postNumber);
	
	// 글쓰기 or 글 업데이트 하기
	public void wirtePost(Board board);
	
	public void deletePost(int boardNumber);
	
	public void totalCount(BoardPage boardPage);
	
	public List<Comment> getComment(int boardNumber);
	
	public void saveComment(Comment comment);
	//-------------------------------------
	
	// 게시판 글의 댓글 내용 불러오기
	public List<Comment> comment(int postNumber);
	
	
	
	
	

	
	public List<UserInfo> getUserName(String name);

	
	public List<ClassInfo> getSemester (String userName);
	// public List<Post> boardList(String standard, List<String> usernames);
	
	//----------------------------------------------- 지원이형 파트
	
	// 해당 classNumber의 과목을 출력하기
	public List<Lecture> lectureList(int classNumber);
	
	// 해당 강의번호의 과목의 정보를 출력하기 
	public Lecture checkLecture(int lecnum);
	

	
	// 해당 과목의 해당 주, 차시에 컨텐츠 삭제하기
	public void deleteContent(int lectureNumber);
	
	// 해당 과목의 교수인지 확인하기
	public String checkProf(int classNumber);
	
	// 해당 과목의 시작일을 가져오기
	public LocalDate getStartDay(int classNumber);
	
	// 해당 과목의 해당 주, 차시에 컨텐츠 추가하기
	public void addContent(Lecture lecture);
	
	//-----------------------------------------영수 파트
	
	
	// 해당 과목페이지에서 과목번호를 입력받아 해당 과목의 공지사항을 출력해주는 함수
	List<Notice> noticeList(int classNumber);
	
	// 해당 공지사항의 번호를 입력받아 해당하는 공지사항에 대한 객체를 반환해주는 함수
	public Notice getSpecificNotice(int noticeNumber);

	// 해당 공지사항의 번호를 입력받아 그 번호에 해당하는 공지사항을 삭제하는 함수
	void deleteNotice(int noticeNumber);

	//해당 과목의 제목에 *** 이 포함된 공지들만 가져오기
	public List<Notice> searchNotice(int classNumber, String noticeTitle);
	
	//
	public void saveNotice(Notice notice);


	public List<Notice> unreadList(int classNumber, String userName);


	public void deleteComment(int commentNumber);


	public void updateComment(int commentNumber, String commentContent);
	
	
	
	//----------영수가 추가한 파트
	
	
	public UserInfo myUserInfo(String username);


	public void saveUserInfo(UserInfo userInfo);
		


	public Users modifyPassword(String username); // 비번 1


	public void saveUserPassword(Users users); // 비번 2


	public Users showAllOfUserInfo(String username); // 계성생성 1


	//error
	public void saveAccountList(Users users); // 계정생성 2
	
	//------------------
	
	// 학번(username) 으로부터 user의 패스워드를 알아내는 방법
	
	public String confirmPassword(String userName);
	
	public String confirmAuth(String userName);
	
	public List<Notice> recentNoticeList(int classNumber);


	public List<ClassInfo> getSemesterForProfessor(String userName);


	public List<ClassInfo> mySignUpInfoForProfessor(String id, Semester semester);


	public void modifyContent(Lecture lecture);
	
	
	//-------------------------- 공지 및 강의의 readData 관련 함수 
	
	//1. save
	
	public void saveLecureReadRecord(LectureReadRecord lectureReadRecord);
	
	public void saveNoticeReadRecord(NoticeReadRecord noticeReadRecord);
	
	
	//2. get
	
	public void getLecureReadRecord(LectureReadRecord lectureReadRecord);


	public String convertRealName(String professorCodeName);


	public List<UserInfo> getUserInfos(String username);


	public UserInfo getUserInfo(String userName);


	public String searchAuthority(String username);


	public void saveOrUpdateAuth(Authorities authority);


	public void saveUser(Users users);
	
	
}
