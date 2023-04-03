package com.code.eclass.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.code.eclass.demo.dto.BoardPage;
import com.code.eclass.demo.dto.Semester;
import com.code.eclass.demo.entity.*;

public interface EclassService {
	public List<ClassInfo> mySignUpInfo(String id,Semester semester);
	
	// 게시판에서 내 해당과목의 post 불러와주기
	public List<Board> boardList(int classNumber);
	public List<Board> boardList(int classNumber, String standard, String searchTarget);
	// 게시판 제목 클릭시 해당 게시판 글 내용 불러오기
	public BoardPage boardList(BoardPage boardPage);
	public Board board(int boardNumber);
	public Board readBoard(int boardNumber);
	// 게시판 글의 댓글 내용 불러오기
	
	public List<Comment> comment(int boardNumber);
	
	public void deletePost(int postNumber);
	public void wirtePost(Board post);
	
	public void saveComment(Comment comment);
	public List<List<Comment>> getComments(int boardNumber);
	public List<Semester> getSemesterInfos(String userName);
	
	//------------------------------------------------------- 지원이형 파트
	
	// 해당 classNumber의 과목을 출력하기
	public List<List<List<Lecture>>> lectureList(int classNumber);
	
	// 해당 강의번호의 과목의 정보를 출력하기 
	public Lecture checkLecture(int lecnum);
	
	// 해당 과목의 해당 주, 차시에 컨텐츠 수정하기
	public void modifyContent(Lecture lecture);
	
	// 해당 과목의 해당 주, 차시에 컨텐츠 삭제하기
	public void deleteContent(int lectureNumber);

	// 해당 과목의 교수인지 확인하기
	public String checkProf(int classNumber);
	
	// 해당 과목의 시작일을 가져오기
	public LocalDate getStartDay(int classNumber);
	
	// 해당 과목의 해당 주, 차시에 컨텐츠 추가하기
	public void addContent(Lecture lecture);
	
	//--------------------------------------------------------영수 파트
	// 과목번호 받아 해당 과목의 전체 공지사항을 가져와주는 함수
	public List<Notice> noticeList(int classNumber);
	// 해당 공지사항의 번호를 입력받아 그 번호에 해당하는 공지사항을 삭제하는 함수
	public Notice getSpecificNotice(int noticeNumber);
	// 해당 공지사항의 번호를 입력받아 해당하는 공지사항에 대한 객체를 반환해주는 함수
	void deleteNotice(int noticeNumber);
	//해당 과목의 제목에 *** 이 포함된 공지들만 가져오기
	public List<Notice> searchNotice(int classNumber, String noticeTitle);
	
	public void saveNotice(Notice notice);

	public List<Notice> unreadList(int classNumber, String userName);

	public void deleteComment(int commentNumber);

	public void updateComment(Comment comment);

	
	
	
	//---------------
	
	public UserInfo myUserInfo(String username); // 회원정보 수정 전상황에서 기존정보 가저오기

	public void saveUserInfo(UserInfo userInfo); // 회원정보 수정한거 저장

	public Users modifyPassword(String name); // 비밀변호 변경을 위해서 Id값 가저와서 전체 User에 대한 컬럼 가저오기

	public void saveUserPassword(Users users); // 비밀번호 변경한거 save

	public Users showAllOfUsersInfo(String string); // 임시 username 가지고와서 createAccount 에 뿌려주기

	//error
	public void saveAccountList(Users users, int numberOfAccount); // username(시작값), 생성할 계정수(numberOfAccount)
	
	public List<ClassInfo> mySignUpInfoForProfessor(String id, Semester semester);
	// -----
	
	public boolean confirmPassword(String userName);
	public boolean confirmAuth(String userName);
	public List<Notice> recentNoticeList(int classNumber);

	public List<Semester> getSemesterInfosForProfessor(String userName);


	public Map<Integer, String> getAttendanceInfo(LocalDate startDate, String userName, int classNumber);
	
	//------------------------------
	
	public void saveLecureReadRecord(LectureReadRecord lectureReadRecord);
	
	public void saveNoticeReadRecord(NoticeReadRecord noticeReadRecord);
	
	public int todayWeeks(LocalDate startDate);

	public Map<String, String> convertRealName(List<ClassInfo> classInfos);

	public List<UserInfo> searchUserInfos(String userName);

	public UserInfo searchUserInfo(String userName);

	public String searchAuthority(String username);

	public void createAccount(UserInfo userInfo, String auth);

	public void updateAuth(String auth, String userName);

	public String getRealName(String username);


}
