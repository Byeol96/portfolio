package com.code.eclass.demo.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.eclass.demo.dto.BoardPage;
import com.code.eclass.demo.dto.Semester;
import com.code.eclass.demo.entity.*;


@Repository
public class EclassDaoImpl implements EclassDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// boardList 함수 2개는 전부 Q&A의 
	/* Q&A 게시판 들어올때 전부 뽑아오는 메서드 */ 
	@Override
	public List<Board> boardList(int classNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Board> theQuery = 
				currentSession.createQuery("from Board where classNumber =:classNumber",Board.class);
		theQuery.setParameter("classNumber", classNumber);
		theQuery.setMaxResults(10);
		theQuery.setFirstResult(0);
		List<Board> board = theQuery.getResultList();
		
		return board;
	}
	
	@Override
	public void setBoardList(BoardPage boardPage) {
		Session currentSession = sessionFactory.getCurrentSession();
		boolean condition = (boardPage.getSearchContent().length()!=0);
		StringBuilder sql = new StringBuilder("from Board where classNumber =:classNumber");
		if(condition) {
			if(boardPage.getSearchTaget().equals("name")) {
				sql.append( " AND userName In (select username from UserInfo where name LIKE '%").append(boardPage.getSearchContent()).append("%')");
				
			}else {
				sql.append(" AND ").append(boardPage.getSearchTaget()).append(" LIKE '%").append(boardPage.getSearchContent()).append("%'");
		
			}
		}
		
		sql.append(" ORDER BY boardDate desc");
		Query<Board> query = currentSession.createQuery(sql.toString(),Board.class);
		query.setParameter("classNumber", boardPage.getClassNumber());
		query.setMaxResults(10);
		query.setFirstResult((boardPage.getPageNumber()-1)*10);
		boardPage.setBoardList(query.getResultList());		
	}
	
	@Override
	public void totalCount(BoardPage boardPage) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		boolean condition = (boardPage.getSearchContent().length()!=0);
		Query<Long> query; 
		StringBuilder sql = new StringBuilder("select count(*) from Board where classNumber =:classNumber");
		
		if(condition) {
			
			if(boardPage.getSearchTaget().equals("name")) {
				sql.append( " AND userName In (select username from UserInfo where name LIKE '%").append(boardPage.getSearchContent()).append("%')");
				
			}else {
				sql.append(" AND ").append(boardPage.getSearchTaget()).append(" LIKE '%").append(boardPage.getSearchContent()).append("%'");
		
			}

		}
		System.out.println(sql);
		query = currentSession.createQuery(sql.toString(),Long.class);
		
		query.setParameter("classNumber", boardPage.getClassNumber());
		int totalCount = query.getSingleResult().intValue();
		
		boardPage.setSearchCount(totalCount);
	}

	
	@Override
	public List<Board> boardList(int classNumber,String standard, String searchTarget) {
		// TODO Auto-generated method stub
		
		Session currentSession = sessionFactory.getCurrentSession();
		StringBuilder sql = new StringBuilder("from Board where classNumber =:classNumber AND ");
		if(standard.equals("name")) { 
			sql.append( "userName In (select username from UserInfo where name LIKE :searchTarget) order by boardDate DESC");
		}else {
			sql.append(standard).append(" LIKE :searchTarget order by boardDate DESC");
		}
		Query<Board> theQuery = currentSession.createQuery(sql.toString(),Board.class);
		theQuery.setParameter("searchTarget", "%"+searchTarget+"%");
		theQuery.setParameter("classNumber", classNumber);
		theQuery.setMaxResults(10);
		theQuery.setFirstResult(1);
		

		return theQuery.getResultList();
	
	}
	
	
	@Override
	public Board board(int boardNumber) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Board> theQuery = 
				currentSession.createQuery("from Board where boardNumber =:boardNumber",Board.class);
		theQuery.setParameter("boardNumber", boardNumber);
		Board board = theQuery.getSingleResult();

		return board;
	}
	
	@Override
	public void wirtePost(Board board) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(board);
	}

	@Override
	public void deletePost(int boardNumber) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		Query theQuery = 
				currentSession.createQuery("delete from Board where boardNumber=:boardNumber");
		theQuery.setParameter("boardNumber", boardNumber);
		
		theQuery.executeUpdate();	
	}	
	
	@Override
	public List<ClassInfo> mySignUpInfoForProfessor(String id,Semester semester) {
	

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ClassInfo> theQuery = 
				currentSession.createQuery(
						"from ClassInfo as c where classProfessor =:classProfessor"
						+ " AND "
						+ "c.classYear=:year AND c.classSemester = :semester", ClassInfo.class);
		theQuery.setParameter("classProfessor",id);
		theQuery.setParameter("year",semester.getYear());
		theQuery.setParameter("semester",semester.getSemester());
		
		List<ClassInfo> classes = theQuery.getResultList();	
		return classes;

	}
	
	@Override
	public List<ClassInfo> mySignUpInfo(String id,Semester semester) {
	

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ClassInfo> theQuery = 
				currentSession.createQuery(
						"from ClassInfo as c where c.classNumber IN "
						+ "(SELECT classNumber from SignupInfo as s where s.userName=:userName) AND "
						+ "c.classYear=:year AND c.classSemester = :semester", ClassInfo.class);
		theQuery.setParameter("userName",id);
		theQuery.setParameter("year",semester.getYear());
		theQuery.setParameter("semester",semester.getSemester());
		
		List<ClassInfo> classes = theQuery.getResultList();	
		return classes;

	}
	
	@Override
	public void deleteComment(int commentNumber) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = 
				currentSession.createQuery("delete from Comment where commentNumber=:commentNumber");
		theQuery.setParameter("commentNumber", commentNumber);
		
		theQuery.executeUpdate();	
	}
	
	

	@Override
	public void updateComment(int commentNumber, String commentContent) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = 
				currentSession.createQuery("update Comment set commentContent=:commentContent where commentNumber=:commentNumber");
		theQuery.setParameter("commentNumber", commentNumber);
		theQuery.setParameter("commentContent", commentContent);
		
		theQuery.executeUpdate();	
	}
	

	@Override
	public List<Comment> comment(int postNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> getUserName(String name) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<UserInfo> theQuery = currentSession.createQuery("from UserInfo as u where name LIKE :name",UserInfo.class);
		theQuery.setParameter("name", "%"+name+"%");
		
		return theQuery.getResultList();
	}
	
	@Override
	public List<UserInfo> getUserInfos(String username) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<UserInfo> theQuery = currentSession.createQuery("from UserInfo where username LIKE :username",UserInfo.class);
		theQuery.setParameter("username", "%"+username+"%");
		
		return theQuery.getResultList();
	}
	
	
	@Override
	public List<Comment> getComment(int boardNumber) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Comment> theQuery = currentSession.createQuery("from Comment where postNumber =:boardNumber",Comment.class);
		theQuery.setParameter("boardNumber", boardNumber);
		return theQuery.getResultList();
	}
	
	//--------------------------------------------------------------- 지원이형 파트
	
	// 해당 classNumber의 과목을 출력해주는 함수 (weeks와 ordinal의 내림차순으로)
	@Override
	public List<Lecture> lectureList(int classNumber){
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Lecture> theQuery = 
				currentSession.createQuery("from Lecture where classNumber =:classNumber order by lectureWeeks desc , lectureOrdinal desc"
						,Lecture.class);

		theQuery.setParameter("classNumber", classNumber);
		return theQuery.getResultList();
	}	
	
	// lecture 테이블에서 해당 강의번호의 내용 출력하는 함수
	@Override
	public Lecture checkLecture(int lecnum) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Lecture> theQuery = currentSession.createQuery("from Lecture where lectureNumber =:lectureNumber ",Lecture.class);
		theQuery.setParameter("lectureNumber", lecnum);
		Lecture lecture = theQuery.getSingleResult(); 
		return lecture;
		
	}
	
	// 해당 강의번호의 강의컨텐츠를 수정하는 함수

	// 해당 강의번호의 강의컨텐츠를 삭제하는 함수
	@Override
	public void deleteContent(int lectureNumber) {
		System.out.println("출력테스트::::::::" + lectureNumber);
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("DELETE from Lecture where lectureNumber =:lectureNumber");
		theQuery.setParameter("lectureNumber", lectureNumber);
		theQuery.executeUpdate();	
	}
	
	// 강의콘텐츠의 수정, 삭제의 권한을 확인하기 위한 해당 과목의 교수인지 확인하는 함수
	@Override
	public String checkProf(int classNumber) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<String> theQuery = currentSession.createQuery("SELECT classProfessor FROM ClassInfo where classNumber in "
				+ "(select classNumber from Lecture where classNumber =: classNumber)", String.class);
		theQuery.setParameter("classNumber", classNumber);
		
		String classInfo = theQuery.getSingleResult(); 
		System.out.println("교수 이름 출력테스트::::::::" + classInfo);
		
		return classInfo;
	}
	
	// 해당 classNumber의 시작일을 가져와주는 함수
	@Override
	public LocalDate getStartDay(int classNumber) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<LocalDate> theQuery = currentSession.createQuery("select startDate from SemesterStart where ( classYear, semester) "
				+ "= (select classYear, classSemester from ClassInfo where classNumber =: classNumber)", LocalDate.class);
		theQuery.setParameter("classNumber", classNumber);
		LocalDate startDay = theQuery.getSingleResult();
		System.out.println("시작일 ::: " + startDay);
		return startDay;
	}

	@Override
	// 해당 강의번호의 강의컨텐츠를 추가하는 함수
	public void addContent(Lecture lecture) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(lecture);
	}
	
	//----------------------------------------------------- 영수 파트
	
	// 해당 과목페이지에서 과목번호를 입력받아 해당 과목의 공지사항을 출력해주는 함수
	@Override
	public List<Notice> noticeList(int classNumber) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Notice> theQuery = currentSession.createQuery("from Notice where classNumber=:classNumber order by noticeDate desc", Notice.class);
		theQuery.setParameter("classNumber", classNumber);
		return theQuery.getResultList();
	}

	public List<Notice> recentNoticeList(int classNumber) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Notice> theQuery = currentSession.createQuery("from Notice where classNumber=:classNumber order by noticeDate desc", Notice.class);
		theQuery.setParameter("classNumber", classNumber);
		theQuery.setMaxResults(5);
		return theQuery.getResultList();
	}
	// 해당 공지사항의 번호를 입력받아 해당하는 공지사항에 대한 객체를 반환해주는 함수
	@Override
	public Notice getSpecificNotice(int noticeNumber) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Notice> theQuery = currentSession.createQuery("from Notice where noticeNumber =:noticeNumber", Notice.class);
		theQuery.setParameter("noticeNumber", noticeNumber);

		return theQuery.getSingleResult();
	}
	
	// 해당 공지사항의 번호를 입력받아 그 번호에 해당하는 공지사항을 삭제하는 함수
	@Override
	public void deleteNotice(int noticeNumber) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("DELETE FROM Notice where noticeNumber=:noticeNumber");
		query.setParameter("noticeNumber",noticeNumber);
		query.executeUpdate();
	}


	//해당 과목의 제목에 *** 이 포함된 공지들만 가져오기
	@Override
	public List<Notice> searchNotice(int classNumber, String noticeTitle) {
		Session currentSession = sessionFactory.getCurrentSession();
		noticeTitle = noticeTitle.trim().replace(" ","");
		
		Query<Notice> theQuery = null;
		
		if (noticeTitle != null && noticeTitle.length() > 0) { // 빈값일때도 리스트 전체 출력할때
			theQuery = currentSession.createQuery("from Notice WHERE classNumber =:classNumber AND lower(noticeTitle) LIKE :noticeTitle", Notice.class); // post_category = notice 인거 까지 어떻게 추가함?
			theQuery.setParameter("noticeTitle", "%"+ noticeTitle.toLowerCase() +"%");
		}
		else {
			
			theQuery = currentSession.createQuery("from Notice WHERE classNumber =:classNumber", Notice.class);
		}
		theQuery.setParameter("classNumber", classNumber);
		
		return theQuery.getResultList();
	}

	@Override
	public void saveComment(Comment comment) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(comment);
		
	}
	
	@Override
	public void saveNotice(Notice notice) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(notice);
	}

	
	@Override
	public List<Notice> unreadList(int classNumber, String userName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Notice> theQuery = 
				currentSession.createQuery(
						"from Notice as n where n.classNumber=:classNumber "
						+ "AND NOT n.noticeNumber IN "
						+ "(SELECT nrr.noticeNumber from NoticeReadRecord as nrr where nrr.userName=:userName)", Notice.class);
		theQuery.setParameter("classNumber", classNumber);
		theQuery.setParameter("userName", userName);
		
		
		return theQuery.getResultList();
	}
	

	@Override
	public List<ClassInfo> getSemesterForProfessor(String userName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<ClassInfo> theQuery = currentSession.createQuery(
				"FROM ClassInfo where classProfessor =:userName", ClassInfo.class); //  group by classYear, classSemester
		theQuery.setParameter("userName", userName);
		
		return theQuery.getResultList();
	}
	
	@Override
	public List<ClassInfo> getSemester(String userName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<ClassInfo> theQuery = currentSession.createQuery(
				"FROM ClassInfo where classNumber in (Select classNumber FROM SignupInfo where userName=:userName)", ClassInfo.class); //  group by classYear, classSemester
		theQuery.setParameter("userName", userName);
		
		//theQuery.setParameter("userName", userName);

		List <ClassInfo> classInfoList = theQuery.getResultList();
		System.out.println("classInfoList : "+classInfoList);
		return classInfoList;
	}

	//----------------영수 추가 파트
	@Override
	public UserInfo myUserInfo(String username) { // userInfo 전체를 가지고 오기 위해서
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<UserInfo> theQuery = currentSession.createQuery("from UserInfo where userName=:userName", UserInfo.class);
		theQuery.setParameter("userName", username);
				
		return theQuery.getSingleResult();
	}

	@Override
	public void saveUserInfo(UserInfo userInfo) { // 회원정보 수정 최종 저장
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(userInfo);
	}

	@Override
	public Users modifyPassword(String username) { // user 리스트 가저올려고 
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Users> theQuery = currentSession.createQuery("from Users where username=:username", Users.class);
		theQuery.setParameter("username", username);
		
		return theQuery.getSingleResult();
	}

	@Override
	public void saveUserPassword(Users users) { // 최종 user에 있는 비번 변경완료
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(users);
	}

	@Override
	public Users showAllOfUserInfo(String username) { // 임시로 username 넣어준다음 createAccount 에서 username 에 첫 시작 이름 적고 100개 하면 20160001 ~ 20160100 표현할려고
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Users> theQuery = currentSession.createQuery("from Users where username=:username", Users.class);
		theQuery.setParameter("username", username);
		
		return theQuery.getSingleResult();
	}

	//error
	@Override
	public void saveAccountList(Users users) { // 생성된 계정 들어오면 다 넣어주는곳
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(users);
		
	}
	
	//

	@Override
	public String confirmPassword(String userName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<String> theQuery = currentSession.createQuery("select password from Users where username=:username", String.class);
		theQuery.setParameter("username", userName);
		String password = theQuery.getSingleResult();
		
		System.out.println("password : "+password);
		
		// if(password.equals)
		return password;
	}

	@Override
	public String confirmAuth(String userName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<String> theQuery = currentSession.createQuery("select authorities from Authorities where userName=:userName", String.class);
		theQuery.setParameter("userName", userName);
		return theQuery.getSingleResult();
	}

	@Override
	   public void modifyContent(Lecture lecture) {
	      Session currentSession = sessionFactory.getCurrentSession();
	      Query theQuery = currentSession.createQuery("UPDATE Lecture set lectureTitle=: lectureTitle, lectureContent =: lectureContent, lecture_endDate =: lecture_endDate where lectureNumber =:lectureNumber");
	      theQuery.setParameter("lectureTitle", lecture.getLectureTitle());
	      theQuery.setParameter("lectureContent", lecture.getLectureContent());
	      theQuery.setParameter("lectureNumber", lecture.getLectureNumber());
	      theQuery.setParameter("lecture_endDate", lecture.getLecture_endDate());
	      theQuery.executeUpdate();
	   }
	
	
	//-------------------------- 공지 및 강의의 readData 관련 함수 
	
	//1. save
	
	
	@Override
	public void saveLecureReadRecord(LectureReadRecord lectureReadRecord) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(lectureReadRecord);
	}

	@Override
	public void saveNoticeReadRecord(NoticeReadRecord noticeReadRecord) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(noticeReadRecord);
		
	}

	@Override
	public void getLecureReadRecord(LectureReadRecord lectureReadRecord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String convertRealName(String professorCodeName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<String> theQuery = currentSession.createQuery("select name from UserInfo where username =:username",String.class);
		theQuery.setParameter("username", professorCodeName);
		return theQuery.getSingleResult();
	}

	@Override
	public UserInfo getUserInfo(String userName) {
		// TODO Auto-generated method stub
		System.out.println("검색할 userName 1 : "+userName);
		Session currentSession = sessionFactory.getCurrentSession();
		Query<UserInfo> theQuery = currentSession.createQuery("from UserInfo where username =:username",UserInfo.class);
		theQuery.setParameter("username", userName);
		return theQuery.getSingleResult();
	};
	@Override
	public String searchAuthority(String username) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<String> theQuery = currentSession.createQuery("select authorities from Authorities where userName =:username",String.class);
		theQuery.setParameter("username", username);
		return theQuery.getSingleResult();
	}

	@Override
	public void saveOrUpdateAuth(Authorities authority) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(authority);
	};
	@Override
	public void saveUser(Users users) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(users);	
	}
}
