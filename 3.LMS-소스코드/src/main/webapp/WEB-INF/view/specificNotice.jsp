<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function submitModify(frm) { 
    frm.action='${pageContext.request.contextPath}/courses/notice/modifyForm/${classNumber}'; 
    frm.submit(); 
    return true; 
  } 
</script>
<style>
	.board-outline{
		width : 90%;
		height : 20%;
		border : 1px solid black;
		margin: 10px auto 10px auto;
	}
	.contentTotal{
		width: 100%;
	}

</style>
</head>
<body>
	특정 공지사항 페이지입니다.
	<div class="contentTotal">
		<form:form action="${pageContext.request.contextPath}/courses/notice/delete/${classNumber}" method="POST">
		<input type="hidden" name="noticeNumber" value="${specificNotice.noticeNumber}"/>
		<button type="submit">삭제하기</button>
		<button type="submit" onclick="return submitModify(this.form);">수정하기</button>
		</form:form>
		<div class="board-outline"> <b> ${specificNotice.noticeTitle} </b> <br>
		${specificNotice.noticeContent}</div>
		
	</div>
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<style>
	.card{
		width: 80%;
		height: 70%;
		margin: 70px auto;
	}
	.card-header{
		height: 45px;
	}
	
	.card-body{
		height: 400px;
	}

	button {
		float: right;
	}
	
	#lectureContent {
		height: 370px;
		padding: 10px;
	}
	
	.btn-secondary {
		margin-right: 10px;
	}
	
	
	
	input[type="submit"] {
		float: right;
	}
	
	.card-body #card-sub{
		text-align : right;
		font-size : 10px;
		margin-bottom: 20px;
	}
</style>



<script>
	function submitModify() { 
		const frm = document.querySelector('#dmForm');
    	frm.action= frm.action.replace('delete','modifyForm');
    	frm.submit(); 
  } 
		
	
</script>




</head>
<body>
	<c:set var="sn" value="${specificNotice}"/>
	<c:set var="path" value="${pageContext.request.contextPath}"/>
	<jsp:include page="sidebar.jsp" />
	<jsp:include page="header.jsp" />
	<div id="mainBody">
	<div class="card" >
		
	  <div class="card-header">
	  	<h5>${sn.noticeTitle}</h5>
	  </div>
	  <div class="card-body">
	  	<div id="card-sub">${sn.noticeWriter}교수님 | 게시일자 : ${sn.noticeDate}</div>
	    <div class="card-text">${sn.noticeContent}</div>
	    <div>  
	    </div>
	  </div>
	  <div class="card-footer text-muted">
			<form:form id="dmForm" action="${path}/courses/notice/delete/${classNumber}" method="POST">
				<input type="hidden" name="noticeNumber" value="${sn.noticeNumber}"/>
				<button class="btn btn-danger" type="submit">삭제하기</button>
				<button id="modifyButton" type="submit" class="btn btn-secondary" onclick="submitModify(this.form);">수정하기</button>
			</form:form>
			<form:form
				action="${path}/courses/notice/${classNumber}"
				method="POST">
				<button type="submit" class="btn btn-secondary">목록</button>
			</form:form>	
	  </div>
	</div>
	</div>
</body>						
</html>