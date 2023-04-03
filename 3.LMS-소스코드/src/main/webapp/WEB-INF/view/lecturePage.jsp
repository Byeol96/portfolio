<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import = "java.text.SimpleDateFormat" %>    
<%@ page import="java.time.LocalDateTime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link href="css/plain-login.css" rel="stylesheet"/> -->
<title>${content.lectureWeeks}주차${content.lectureOrdinal}차시글</title>
</head>

<script>

		function submitModify() { 
			const formA = document.querySelector('#dmForm');
			
			formA.action=formA.action.replace('delete','modifyForm'); 
			formA.submit(); 
		  } 	 
</script>
<style>
#total {
	width: 100%;
	height: 100%;
}

#mainBody2 {
	width: 90%;
	margin: 30px auto 0 auto;

	height: 65%;
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

textarea {
	width: 100%;
	height: 455px;
}

body::-webkit-scrollbar {
	display: none;
}

#attendenceDiv {
	background: gainsboro;
	margin: 5px auto;
	width: 99%;
	border-radius: 5px;
	padding: 10px;
}

input[type="submit"] {
	float: right;
}
h3{

	    margin-left: 5px;
}


</style>


</head>
<body>


	<!-- 시작일, 마감일, 현재날짜를 불러와서 비교하려고 변수를 선언했습니다. -->
	<%
	LocalDateTime localDateTime = LocalDateTime.now();
	%>

	<jsp:include page="sidebar.jsp" />
	<jsp:include page="header.jsp" />
	<div id="mainBody">
		<div id="mainBody2">
			<div id="totalDiv">
				<c:set var="i" value="${lecture.lectureWeeks}" />
				<c:set var="stDay" value="${startDay.plusDays(7*(i-1))}"></c:set>
				<c:set var="endDay" value="${lecture.lecture_endDate}"></c:set>
				<c:set value="<%=localDateTime.toLocalDate()%>" var="today" />
				<h3> ${lecture.lectureTitle} </h3>
				<hr>
				마감일 : ${endDay} 00:00:00   | 출석 인정 기간 : ${stDay} 00:00:00 ~ ${endDay}
				00:00:00

				<hr>
				<c:if
					test="${today.isAfter(endDay.minusDays(1)) or today.isBefore(stDay)}">
					<div id="attendenceDiv">출석 인정 기간이 아닙니다.</div>
				</c:if>
				<security:authentication property="principal.username"
					var="username" />
				<div id="lectureContent">
					${lecture.lectureContent}<br>
				</div>
				<hr>
				<c:if test="${username eq (checkProf)}">
					<security:authorize access="hasRole('PROFESSOR')">
						<form:form id="dmForm"
							action="${pageContext.request.contextPath}/courses/lecture/delete/${classNumber}"
							method="POST">
							<input type="hidden" name="lectureNumber"
								value="${lecture.lectureNumber}" />
							<button class="btn btn-danger" type="submit">삭제하기</button>	
							<button id="modifyButton" type="submit" class="btn btn-secondary" onclick="submitModify(this.form);">수정하기</button>
						</form:form>
					</security:authorize>
				</c:if>
					<form:form
						action="${pageContext.request.contextPath}/courses/lecture/${classNumber}"
						method="POST">
						<button type="submit" class="btn btn-secondary">목록</button>
					</form:form>
	

			</div>
		</div>
	</div>


</body>

</html>