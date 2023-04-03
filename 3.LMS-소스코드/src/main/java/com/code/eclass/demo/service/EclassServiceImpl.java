package com.code.eclass.demo.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.eclass.demo.dao.EclassDao;
import com.code.eclass.demo.dao.EclassDaoJDBC;
import com.code.eclass.demo.dto.BoardPage;
import com.code.eclass.demo.dto.Semester;
import com.code.eclass.demo.entity.*;

@Service
public class EclassServiceImpl implements EclassService {
	
	@Autowired
	private EclassDao eclassDao;
	
	@Autowired
	private EclassDaoJDBC eclassDaoJDBC;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	@Override
	@Transactional
	public String getRealName(String username) {
		
		return eclassDao.convertRealName(username);
	}
	@Override
	@Transactional
	public Map<String,String> convertRealName(List<ClassInfo> classInfos){
		
		Map<String,String> nameMapper = new HashMap<>();
		for(ClassInfo classInfo :classInfos) {
			
			String professorCodeName = classInfo.getClassProfessor();
			if(nameMapper.containsKey(professorCodeName)) continue;
			
			String realName = eclassDao.convertRealName(professorCodeName);
			nameMapper.put(professorCodeName, realName);
		}
		return nameMapper;
	}
	@Override
	@Transactional
	public List<ClassInfo> mySignUpInfo(String id,Semester semester) {
		
		
		return eclassDao.mySignUpInfo(id,semester);
	}
	@Override
	@Transactional
	public List<ClassInfo> mySignUpInfoForProfessor(String id, Semester semester){
		
		return eclassDao.mySignUpInfoForProfessor(id,semester);
	}
	@Override
	@Transactional
	public List<Board> boardList(int classNumber) {
		// TODO Auto-generated method stub
		/*
		 
		  1) 해당 페이지 올 때
		  
		  1. 전체 데이터의 개수
		  2. 10개
		  3. start=1 end =10
		  4. 현재 페이지 
		
		  */
		BoardPage boardPage = new BoardPage();
		boardPage.setBoardList(eclassDao.boardList(classNumber));
		return eclassDao.boardList(classNumber);
	}

	@Override
	@Transactional
	public Board board(int postNumber) {
		// TODO Auto-generated method stub
		return eclassDao.board(postNumber);
	}

	@Override
	@Transactional
	public Board readBoard(int boardNumber) {
		
		Board board = eclassDao.board(boardNumber);
		int views = board.getBoardViews()+1;
		board.setBoardViews(views);
		eclassDao.wirtePost(board);
		return board;
	}
	
	
	
	@Override
	public List<Comment> comment(int postNumber) {
		// TODO Auto-generated metahod stub
		return null;
	}

	@Override
	@Transactional
	public void deletePost(int postNumber) {
		// TODO Auto-generated method stub
		eclassDao.deletePost(postNumber);
		
	}

	@Override
	@Transactional
	public List<Board> boardList(int classNumber, String standard, String searchTarget){
		// TODO Auto-generated method stub
		
	
		return eclassDao.boardList(classNumber,standard, searchTarget);
			
		
		
		/* standard="userName";;
		List<String> usernames = new ArrayList<>();
		List<UserInfo> userInfos = eclassDao.getUserName(searchTarget);
		
		if(userInfos == null) return new ArrayList<>();
		standard = "username";
		for(UserInfo userInfo : userInfos) {
			usernames.add(userInfo.getUsername());
		}	*/
		
		/* return eclassDao.boardList(standard, usernames); */
	}

	@Override
	@Transactional
	public void wirtePost(Board post) {
		// TODO Auto-generated method stub
		eclassDao.wirtePost(post);
	}
	

	@Override
	@Transactional
	public List<List<Comment>> getComments(int boardNumber) {
		
		
		return lectureDataComment(eclassDao.getComment(boardNumber));
	}

	
	private List<List<Comment>> lectureDataComment (List<Comment> commentList){
		
		HashMap<Integer,Integer> locationMap = new HashMap<>();
		List<List<Comment>> lectureDataComment = new ArrayList<>();
		for(Comment comment: commentList) {
			
			Integer previousCommentNumber = comment.getPreviousCommentNumber();
			if(previousCommentNumber == null) {
				
				lectureDataComment.add(new ArrayList<>());
				lectureDataComment.get(lectureDataComment.size()-1).add(comment);
				locationMap.put(comment.getCommentNumber(),lectureDataComment.size()-1);
				
			}else {
				int location = locationMap.get(previousCommentNumber);
				lectureDataComment.get(location).add(comment);
				locationMap.put(comment.getCommentNumber(),location);
				locationMap.remove(location);
			}
			
			System.out.println(lectureDataComment);
		}

		return lectureDataComment;
	}
	

	@Override
	@Transactional
	public BoardPage boardList(BoardPage boardPage) {
		
		eclassDao.totalCount(boardPage);
		eclassDao.setBoardList(boardPage);
		
		boardPage.restSetting();
		System.out.println(boardPage);
		return boardPage;
	}

	@Override
	@Transactional
	public void saveComment(Comment comment) {
		// TODO Auto-generated method stub
		System.out.println("넣기전 확인 : " +comment);
		eclassDao.saveComment(comment);
	}
	
	@Override
	@Transactional
	public void deleteComment(int commentNumber) {
		// TODO Auto-generated method stub
		eclassDao.deleteComment(commentNumber);
	}

	@Override
	@Transactional
	public void updateComment(Comment comment) {
		// TODO Auto-generated method stub
		String commentContent = comment.getCommentContent();
		int commentNumber = comment.getCommentNumber();
		
		eclassDao.updateComment(commentNumber,commentContent);
	}

	
	//-----------------------------------------------지원이형 파트 
	
	// 해당 classNumber의 과목을 출력하기
	
	@Override
	@Transactional
	public List<List<List<Lecture>>> lectureList(int classNumber){
		
		return lectureDataConstruct(eclassDao.lectureList(classNumber));
	}
	
	private List<List<List<Lecture>>> lectureDataConstruct (List<Lecture> lectureList){
		
		List<List<List<Lecture>>> structuredList = new ArrayList<List<List<Lecture>>>();
		
		for(int i = 0; i < lectureList.get(0).getLectureWeeks(); i ++) {

			List<List<Lecture>> innerList = new ArrayList<List<Lecture>>();
			
			for(int j =0; j <2; j++) {
				
				if(i == lectureList.get(0).getLectureWeeks()-1 && j == lectureList.get(0).getLectureOrdinal() ) break;
				
				innerList.add(new ArrayList<Lecture>());
			}
			structuredList.add(innerList);
		}	
		
		for(Lecture lecture : lectureList) {

			structuredList.get(lecture.getLectureWeeks()-1).get(lecture.getLectureOrdinal()-1).add(lecture);
		}
		return structuredList;
		
	}
	
	// 해당 강의번호의 과목의 정보를 출력하기
	@Override
	@Transactional
	public Lecture checkLecture(int lecnum) {
		return eclassDao.checkLecture(lecnum);

	}

	// 해당 과목의 해당 주, 차시에 컨텐츠 수정하기
	@Override
	@Transactional
	public void modifyContent(Lecture lecture) {
		eclassDao.modifyContent(lecture);
	}
	
	
	// 해당 과목의 해당 주, 차시에 컨텐츠 삭제하기
	@Override
	@Transactional
	public void deleteContent(int lectureNumber) {
		eclassDao.deleteContent(lectureNumber);
	}
	
	// 해당 과목의 교수인지 확인하기
	@Override
	@Transactional
	public String checkProf(int classNumber) {
		return eclassDao.checkProf(classNumber);
	}
	
	// 해당 과목의 시작일을 가져오기
	@Override
	@Transactional
	public LocalDate getStartDay(int classNumber) {
		return eclassDao.getStartDay(classNumber);
	}
	

	// 해당 과목의 해당 주, 차시에 컨텐츠 추가하기
	@Override
	@Transactional
	public void addContent(Lecture lecture) {
		eclassDao.addContent(lecture);
	}
	
	//------------------------------------------------ 영수 파트
	
	// 해당 과목페이지에서 과목번호를 입력받아 해당 과목의 공지사항을 출력해주는 함수	
	@Override
	@Transactional
	public List<Notice> noticeList(int classNumber){
		
		return eclassDao.noticeList(classNumber);
	}
	
	// 해당 공지사항의 번호를 입력받아 해당하는 공지사항에 대한 객체를 반환해주는 함수
	@Override
	@Transactional
	public Notice getSpecificNotice(int noticeNumber) {
		// TODO Auto-generated method stub
		return eclassDao.getSpecificNotice(noticeNumber);
	}
	// 해당 공지사항의 번호를 입력받아 해당하는 공지사항에 대한 객체를 반환해주는 함수
	@Override
	@Transactional
	public void deleteNotice(int noticeNumber) {
		eclassDao.deleteNotice(noticeNumber);
		
	}
	//해당 과목의 제목에 *** 이 포함된 공지들만 가져오기
	@Override
	@Transactional
	public List<Notice> searchNotice(int classNumber, String noticeTitle) {
		// TODO Auto-generated method stub
		return eclassDao.searchNotice(classNumber, noticeTitle);
	}
	
	@Override
	@Transactional
	public void saveNotice(Notice notice) {
		// TODO Auto-generated method stub
		eclassDao.saveNotice(notice);
	}

	@Override
	@Transactional
	public List<Notice> unreadList(int classNumber, String userName) {
		return eclassDao.unreadList(classNumber, userName);
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@Transactional
	public List<Semester> getSemesterInfosForProfessor(String userName) {
		// TODO Auto-generated method stub
		List<Semester> semesterList = Semester.semesterDataTypeConverter(eclassDao.getSemesterForProfessor(userName));
		Collections.sort(semesterList,Collections.reverseOrder());
		return semesterList;
	}

	@Override
	@Transactional
	public List<Semester> getSemesterInfos(String userName) {
		// TODO Auto-generated method stub
	
		List<Semester> semesterList = Semester.semesterDataTypeConverter(eclassDao.getSemester(userName));/*eclassDao.getSemester(userName);*/
		Collections.sort(semesterList,Collections.reverseOrder());
		return semesterList;
	}

	//-------------------------------------------------------------------------
	@Override
	@Transactional
	public UserInfo myUserInfo(String username) { // 회원정보 눌렀을때 리스트 나오게끔
		// TODO Auto-generated method stub
		return eclassDao.myUserInfo(username);
	}

	@Override
	@Transactional
	public void saveUserInfo(UserInfo userInfo) { // 회원정보 최종 수정 저장
		// TODO Auto-generated method stub
		eclassDao.saveUserInfo(userInfo);
	}
	
	@Override
	@Transactional
	public Users modifyPassword(String username) { // 유저 비밀번호 리스트 가저오는곳
		// TODO Auto-generated method stub
		return eclassDao.modifyPassword(username);
	}

	@Override
	@Transactional
	public void saveUserPassword(Users users) { // 유저 비밀번호 최종 수정 저장
		// TODO Auto-generated method stub
		//users.setPassword(passwordEncoder.encode(users.getPassword()));
		eclassDao.saveUserPassword(users);
		
	}

	@Override
	@Transactional
	public Users showAllOfUsersInfo(String username) { // 계정 생성을 위한 준비 코드
		// TODO Auto-generated method stub
		return eclassDao.showAllOfUserInfo(username);
	}

	@Override
	@Transactional
	//error
	public void saveAccountList(Users users, int numberOfAccount) { // 계정생성 for문 돌릴려고 numberOfAccount = 반복문돌릴 계정수
		// TODO Auto-generated method stub
		
		for(int i = 0; i < numberOfAccount; i++) {
			eclassDao.saveAccountList(users);
		}
		
	}
	// @Transactional 필수// @Transactional 필수

	@Override
	@Transactional
	public boolean confirmPassword(String userName) {
		// TODO Auto-generated method stub
		
		String password = eclassDao.confirmPassword(userName).replace("{noop}","");
		
		System.out.println("비교할 password : "+password);
		if(password.equals("1111")) return true;
		
		return false;
	}

	
	@Override
	@Transactional
	public boolean confirmAuth(String userName) {
		// TODO Auto-generated method stub
		String authority = eclassDao.confirmAuth(userName);
		return authority.equals("ROLE_PROFESSOR");
	}
	
	@Override	
	@Transactional
	public List<Notice> recentNoticeList(int classNumber){
		
		return eclassDao.recentNoticeList(classNumber);
	}
	
	public int todayWeeks(LocalDate startDate) {
		
		LocalDate today = LocalDate.now();
				
		int betweenWeeks = (int)ChronoUnit.WEEKS.between(startDate,today)+1;
		
		return betweenWeeks > 15 ? 15 : betweenWeeks;

	}
	
	@Override
	@Transactional
	public Map<Integer, String> getAttendanceInfo(LocalDate startDate, String userName,int classNumber) {
		// TODO Auto-generated method stub
		return eclassDaoJDBC.getAttendanceInfos(userName, startDate,classNumber);
	}
	@Override
	@Transactional
	public void saveLecureReadRecord(LectureReadRecord lectureReadRecord) {
		
		eclassDao.saveLecureReadRecord(lectureReadRecord);
		
	}
	@Override
	@Transactional
	public void saveNoticeReadRecord(NoticeReadRecord noticeReadRecord) {
		// TODO Auto-generated method stub
		eclassDao.saveNoticeReadRecord(noticeReadRecord);
		
	}
	@Override
	@Transactional
	public List<UserInfo> searchUserInfos(String userName){
		System.out.println("검색할 userName : "+userName);
		List<UserInfo> userInfos = eclassDao.getUserInfos(userName);
		System.out.println("data : "+userInfos);
		return userInfos;
	}
	
	@Override
	@Transactional
	public UserInfo searchUserInfo(String userName){
		System.out.println("검색할 userName : "+userName);
		UserInfo userInfo = eclassDao.getUserInfo(userName);
		System.out.println("data : "+userInfo);
		return userInfo;
	}
	@Override
	@Transactional
	public String searchAuthority(String username) {
		
		return eclassDao.searchAuthority(username);
	}
	@Override
	@Transactional
	public void createAccount(UserInfo userInfo, String auth) {
		// TODO Auto-generated method stub
	
			String userName = userInfo.getUsername();
		// 1. user save
			eclassDao.saveUser(new Users(userName));
		// 2. userInfo save
			eclassDao.saveUserInfo(userInfo);
		// 3. auth save
			eclassDao.saveOrUpdateAuth(new Authorities(auth,userName));
	
	
	};
	
	@Override
	@Transactional
	public void updateAuth (String auth,String userName) {
		
		eclassDao.saveOrUpdateAuth(new Authorities(auth,userName));
	}
	//public int nowWeeks 
}
