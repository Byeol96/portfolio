<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDateTime" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 내용추가</title>
</head>

<script>
	function totalList(){
		 
		 const backForm = document.querySelector('#backForm');
		 backForm.submit();
	}
	
	 function formSubmit(){
		const form = document.querySelector('#AddForm');
		const textarea = document.querySelector('textarea');
		
		let input2 = document.createElement('input');
		input2.setAttribute('type', 'hidden');
		input2.setAttribute('name', 'lectureContent');
		input2.setAttribute('value', textarea.value.replaceAll("\n","<br>")); 
		
		form.appendChild(input2);
		form.submit();
		 
	 }
	 
	
</script>
<style>
#total{
	width : 100%;
	height : 100%;
}
#mainBody2{
	width: 90%;
	margin: 30px auto 0 auto;

	height: 65%;
}

button{
	float: right;
}

#lectureContent{
	height: 350px;
}

.btn-secondary{
	margin-right: 10px;
}
textarea{
	width : 100%;
	height : 370px;
}

body::-webkit-scrollbar {
  display: none;
}
h3{

	    margin-left: 5px;
}
#totalMain{
	width : 80%;
}
</style>

<body>
<div id="totalDiv">
	<jsp:include page="sidebar.jsp"/>
	<jsp:include page="header.jsp"/>
	<div id="mainBody">
		<div id="mainBody2">
		
			<h3>강의 작성</h3>
			<hr>
			<form:form id="AddForm" action="${pageContext.request.contextPath}/courses/lecture/add/${lecture.classNumber}" modelAttribute="lecture" method="POST">
				<form:hidden path="lectureWeeks"/>
				<form:hidden path="lectureOrdinal"/>
				<form:hidden path="lectureNumber"/>
	
		         <label for="lectureTitle" class="form-label"></label>
		         <input class="form-control" name="lectureTitle" list="datalistOptions" id="lectureTitle">
				<br>
				<textarea></textarea>
				<hr>
				출석인정기간: <input type="date" name="lectureDate" value="${lecture.lecture_endDate}"/>
				<button type="submit" class="btn btn-info" onclick="formSubmit()">등록</button>
				<button type="button" class="btn btn-secondary" onclick="totalList()">취소</button>		
			</form:form> 
			<form:form id="backForm" action="${pageContext.request.contextPath}/courses/lecture/${classNumber}" method="GET">
					
			</form:form>	
			</div>		
		</div>	
	</div>

</body>
</html>