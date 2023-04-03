<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <link href="${path}/css/main.css" rel="stylesheet"/>
<head>
<title>Eclass Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<script>
	function pageMove(location){
		const form = document.querySelector('form');
		
		form.action='${pageContext.request.contextPath}/courses/'+location+'/${classNumber}';
		
		form.submit();
	}
	
	function otherPageMove(){
		const form = document.querySelector('form');
		
		form.action='${pageContext.request.contextPath}/myPage';
		
		form.submit();
	}
	
</script>
<style>
	span{
		font-size :10px;
	}
</style>
<body>
	<form:form method="POST"></form:form>
    <div id="side1" class="sidebar">
      <div id="side2">  
        
          <div class="sideIcon">
            <a onclick="otherPageMove()">
              
              <i class="fas fa-server"></i>
              <img src="${path}/img/icon/mypage.jpg" width="40" height="40"><br>
              <span>마이페이지</span>
 
            </a>
          </div>
          <div class="sideIcon">
            <a onclick="pageMove('notice')">
              
              <i class="fas fa-server"></i>
              <img src="${path}/img/icon/notice.jpg" width="40" height="40"><br>
              <span>공지사항</span>
            
            </a>
          </div>
          <div class="sideIcon">
            <a onclick="pageMove('lecture')">
              
              <i class="fas fa-concierge-bell"></i>
              <img src="${path}/img/icon/lecture.jpg" width="40" height="40"><br>
              <span>강의콘텐츠</span>
              
            </a>
          </div>
          <div class="sideIcon">
            <a onclick="pageMove('board')">
              
              <i class="fas fa-calendar-alt"></i>
              <img src="${path}/img/icon/board.jpg" width="40" height="40"><br>
              <span>게시판</span>
           
            </a>
          </div>
          <div class="sideIcon">
            <a href="#">
              
              <i class="fas fa-cog"></i>
              <img src="${path}/img/icon/attendence.jpg" width="40" height="40"><br>
              <span>출결 현황</span>
            
            </a>
          </div>
       
      </div>
    </div>

</body>

</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <link href="${path}/css/main.css" rel="stylesheet"/>
<head>
<title>Eclass Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<script>
	function pageMove(location){
		const form = document.querySelector('form');
		
		form.action='${pageContext.request.contextPath}/courses/'+location+'/${classNumber}';
		
		form.submit();
	}
	
	function otherPageMove(){
		const form = document.querySelector('form');
		
		form.action='${pageContext.request.contextPath}/myPage';
		
		form.submit();
	}
	function classHomeMove(classNumber){
		const form = document.querySelector('form');
		
		form.action='${pageContext.request.contextPath}/courses/'+classNumber;
		
		form.submit();
	}
	
</script>
<style>
	span{
		font-size :15px;
	}
</style>
<body>
	<form:form method="POST"></form:form>
    <div id="side1" class="sidebar">
      <div id="side2">  
        
          <div class="sideIcon">
            <a onclick="otherPageMove()">
              
              <i class="fas fa-server"></i>
              <img src="${path}/img/icon/mypage.jpg" width="40" height="40"><br>
              <span>마이페이지</span>
 
            </a>
          </div>
          
          <div class="sideIcon">
            <a onclick="classHomeMove(${classNumber})">
              
              <i class="fas fa-server"></i>
              <img src="${path}/img/icon/home2.jpg" width="40" height="40"><br>
              <span>홈</span>
            </a>
          </div>          
          
          <div class="sideIcon">
            <a onclick="pageMove('notice')">
              
              <i class="fas fa-server"></i>
              <img src="${path}/img/icon/notice.jpg" width="40" height="40"><br>
              <span>공지사항</span>
            
            </a>
          </div>
          <div class="sideIcon">
            <a onclick="pageMove('lecture')">
              
              <i class="fas fa-concierge-bell"></i>
              <img src="${path}/img/icon/lecture.jpg" width="40" height="40"><br>
              <span>강의콘텐츠</span>
              
            </a>
          </div>
          <div class="sideIcon">
            <a onclick="pageMove('board')">
              
              <i class="fas fa-calendar-alt"></i>
              <img src="${path}/img/icon/board.jpg" width="40" height="40"><br>
              <span>게시판</span>
           
            </a>
          </div>
          <div class="sideIcon">
            <a onclick="pageMove('attendance')">
              
              <i class="fas fa-cog"></i>
              <img src="${path}/img/icon/attendence.jpg" width="40" height="40"><br>
              <span>출결 현황</span>
            
            </a>
          </div>
       
      </div>
    </div>

</body>

</html>