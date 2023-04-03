<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>

<script>
	window.onload=function(){
		
		let optionSelect = document.querySelector('#option'+${semesterNumber})
		
		optionSelect.selected=true;
	}
	
	function classHomeMove(classNumber){
		
		const classMoveForm = document.querySelector('#classMoveForm');
		
		classMoveForm.action += classNumber;
		classMoveForm.submit();
		
	}

</script>
<style>


	.header-content-button{
		float:right;
		margin : 0 0 0 10px;
		padding : 8px 10px;
	}
	
	p {
		height : 30px;
	}
	.main-section{
		padding : 20px;
		background-color : gray;
	}
	
	.student-course{
		background-color : white;
		padding: 10px 0px;
		margin :15px 0 0 0;
	}
	
	.student-course-title{
		font-size : 18px;
	}
	
	.student-course-subcontent{
		font-size: 14px;
	}
	
	.student-course-content-button{
		padding : 1px 15px 1px 0;
		display: inline-block;
	}
	.student-course-total-content{
		display: inline-block;
	}
	.student-course-detail-content{
		display: inline-block;
		text-align:center;
	}
	
	.student-course-total{
		display: inline-block;
	}
	.student-course-detail{
		display: inline-block;
	}
	.student-course-move{
		display: inline-block;
	}
	.moveBtn{
		margin-top: -28px;
	}
</style>


</head>
<body>
	<div>

		<form:form method="POST">
			<select name="semesterNumber">
			<c:forEach var="semester" items="${semesterInfos}" varStatus="i">
				<option id="option${i.index}" value="${i.index}">${semester}</option>
			</c:forEach>
			</select>
			
			<button type="submit">학기 선택</button>
		</form:form>
	</div>
	<div class="main-section" >
	<p> <span> 나의 과목 </span><span>${courseList.size()}</span> <button class="header-content-button"><b>모두 접기▲</b></button> <button class="header-content-button"><b>모두 펼치기▼</b></button></p>
	
	<c:forEach var="course" items="${courseList}">
		<div class="student-course"> 
			
			<div class="student-course-total">
				<div class="student-course-content-button">∨</div>
				<div class="student-course-total-content">
					<span class="student-course-title"><b>${course.className}</b></span>
					<div class="student-course-subcontent">${course.classYear}-${course.classSemester}|${course.classProfessor}교수님</div>
				</div>
			</div>
			<div class="student-course-detail">
				<div class="student-course-detail-content"> 
					<a> 0 </a> <br>
					<span>공지</span>
				</div>
				<div class="student-course-detail-content"> 
					<a> 0 </a> <br>
					<span>강의</span>
				</div>
			</div>
			<div class="student-course-move">
		
			
				<button class="btn btn-outline-secondary moveBtn" onclick="classHomeMove(${course.classNumber})"> 과목홈 바로가기 </button>
			</div>
		</div>
	</c:forEach>
	<form:form id="classMoveForm" action="${pageContext.request.contextPath}/courses/" method="POST">
	</form:form>
	</div>
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>

<script>
	window.onload=function(){
		
		let optionSelect = document.querySelector('#option'+${semesterNumber})
		
		optionSelect.selected=true;
	}
	
	function classHomeMove(classNumber){
		
		const classMoveForm = document.querySelector('#classMoveForm');
		
		classMoveForm.action += classNumber;
		classMoveForm.submit();
		
	}

</script>
<style>


	.header-content-button{
		float:right;
		margin-left: 10px;



	}
	
	#selectSemester{
		    margin: 20px auto 10px auto;
		        width: 90%;
	}
	
	p {
		height : 30px;
	}
	.main-section{
		padding : 20px;
		background-color : #cccccc;
		width: 90%;
		margin: 35px auto;
	}
	
	.student-course{
		background-color : white;
		padding: 10px 0px;
		margin :15px 0 0 0;
	}
	
	.student-course-title{
		font-size : 18px;
	}
	
	.student-course-subcontent{
		font-size: 14px;
	}
	
	.student-course-content-button{
		padding : 1px 15px 1px 0;
		display: inline-block;
	}
	.student-course-total-content{
		display: inline-block;
	}
	.student-course-detail-content{
		display: inline-block;
		text-align:center;
	}
	
	.student-course-total{
		display: inline-block;
	}
	.student-course-detail{
		margin-right: 200px;
		float: right;
	}
	
	.moveBtn{
		margin-top: -28px;
	}
	
	#info{
		width: 90%;	
		margin: 0 auto;
	}
	
	#notice{
		float: right;
		width: 33%;
		margin-right: 5px;
	}
	
	#lecture{
		float: right;
		width: 33%;
		margin-right: 5px;
	}
	#lecture1{
		float: right;
		width: 33%;
		margin-right: 5px;
	}	
	
	#body{
		margin-top: 25px;
	}
	
	#header1{
	    background:white;   /*배경색*/
	    margin: 30px auto 0 auto;
	    width: 92%;
	    height: 100px;
	    color: black;
	}   	
	.btn-info {
	    width: 90%;
	    height: 50px;
	    margin: 20px;
	}
	
	.btn-info{
		width: 80%;
		height: 30px;	
	}
	
	.button{
	    display: flex;  /* 1. 부모먼저 flex해주기 */
	    flex-direction: column;
	    margin: 10px;
	    padding: 10px;
	    justify-content: space-between;
	}
	
	#loginBt{
		border-radius: 45px;
		width: 150px;
		height: 50px;
		border: rgb(56, 62, 90) 2px solid;
		background: rgb(248, 246, 246);
		color: black;
		float: right;
		margin: 20px 20px 0 0;
	}
	
		#loginBt2{
		border-radius: 45px;
		width: 150px;
		height: 50px;
		border: rgb(56, 62, 90) 2px solid;
		background: #009BCB;
		color: black;
		float: right;
		margin: -50px 10px 0 0;
	}

	#subInfo{
		width: 90%;
		margin: 30px auto;
	}

	#myLecture{
		float:left;
	
	}
	.btn-secondary{
		margin-bottom: 5px;
	}
	.ContentSpan{
		margin : 0 10px;
	}
	#select{
	    text-align: center;
		width : 160px;
	}
</style>
<script>
function showHideAll(){
	   const btnName = document.getElementById('bt_oc');
	   let shape;	    
	    if(btnName.innerHTML.includes('열기') ){
	       $('.contentInfo').show();
	       shape ="▲ ";
	       btnName.innerHTML = "▲ 모든 메뉴 닫기";
	   }else{
	       $('.contentInfo').hide();
	       btnName.innerHTML = "▼ 모든 메뉴 열기";
	       shape ="▼ ";
	   }
	   
	    for(let i = 1; i < 16; i++){
	       document.getElementById('bt_week'+i).innerHTML = shape+i;
	    }
	}
	function logout(){
		
		
		const logoutForm = document.querySelector('#logout');
		logoutForm.submit();
		
	}

</script>




</head>
<body>
<div id="body">
	<form:form id="logout" action="${pageContext.request.contextPath}/logout" method="post"></form:form>
 <security:authentication property = "principal.username" var="username"/>
    <div id="header1">
        <img src="${path}/img/home/logo2.jpg" width="300" height="100%" style="margin-left: 10px;">
        <security:authorize access="isAuthenticated()">
        	<button id="loginBt" class="btn btn-info" onclick="logout()">로그아웃</button>
        </security:authorize>
    </div>
   
    

	<div id="selectSemester">
		<form:form method="POST">
			<select id="select" name="semesterNumber" style="width:130px; height:40px;">
			<c:forEach var="semester" items="${semesterInfos}" varStatus="i">
				<option id="option${i.index}" value="${i.index}">${semester}</option>
			</c:forEach>
			</select>
			
			<button type="submit" class="btn btn-secondary">학기 선택</button>
		</form:form>
	</div>

	<br>
		<div id="subInfo">
			<span id="myLecture">나의 과목 ${courseList.size()}</span>



			<br>
		</div>
		<div class="main-section" >
			<c:forEach var="course" items="${courseList}">
		<div class="student-course"> 
			
			<div class="student-course-total">
			<!-- 일단 여기 버튼 생략했음 -->
				<div class="student-course-content-button"></div>
				<div class="student-course-total-content">
					<span class="student-course-title"><b>${course.className}</b></span>
					<div class="student-course-subcontent"><span class="ContentSpan">${course.classYear}-${course.classSemester}</span>|<span class="ContentSpan">${nameMapper.get(course.classProfessor)}교수님</span></div>
				</div>
			</div>
			<div class="student-course-detail">
				
			</div>
			<div>
				<button class="btn btn-outline-secondary moveBtn" onclick="classHomeMove(${course.classNumber})" id="loginBt2"> 과목홈 바로가기 </button>
			</div>
		</div>
	</c:forEach>
	<form:form id="classMoveForm" action="${pageContext.request.contextPath}/courses/" method="POST">
	</form:form>
	</div>
</div>	
</body>
</html>