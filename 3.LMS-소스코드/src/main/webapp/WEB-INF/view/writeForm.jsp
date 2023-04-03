<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	 function formSubmit(){
			const form = document.querySelector('#writeForm');
			const textarea = document.querySelector('textarea');
			
			let input2 = document.createElement('input');
			input2.setAttribute('type', 'hidden');
			input2.setAttribute('name', 'boardContent');
			input2.setAttribute('value', textarea.value.replaceAll("\n","<br>")); 
			
			form.appendChild(input2);
			form.submit();
			 
		 }
	 
	 function movePostForm(boardNumber){
		 
		 const form = document.querySelector('#movePostForm');
		 if(boardNumber==0) {
			 form.action=form.action.replace('/read','');
		 }
		 form.submit();
	 }
		
</script>
<script>
	window.onload = function(){
		
		if(${post.boardPrivate == true}){
		document.getElementById('checkbox').checked=true;
		}
		const textarea = document.querySelector('textarea');
		textarea.innerHTML = textarea.innerHTML.replaceAll('&lt;br&gt;','\r\n');
	}
</script>
<style>
	#postDiv{
		width: 80%;
    	margin: 20px auto 0 auto;
	}
	#titleB{
		font-size : 20px;
	}
	#subTitleSpan{
		font-size : 15px;
	}
	button{
		float: right;
		margint-top : 15px;
	}
	textarea{
	width : 100%;
	height : 300px;
	}
	.CUBtn{
		margin: 0 3px;
	}
</style>
</head>
<body>

	<jsp:include page="sidebar.jsp" />
	<jsp:include page="header.jsp" />
	<div id="mainBody">
		<div id="postDiv">
			<b id="titleB">${title}</b> <span id="subTitleSpan"> Q&amp;A 게시판</span>

			<hr>
			<security:authentication property="principal" var="principal" />
			<form:form 
				action="${pageContext.request.contextPath}/courses/board/write/${post.classNumber}"
				modelAttribute="post" id="writeForm" method="POST">
				<c:if test="${post.boardNumber!=0}">
					<form:hidden path="boardNumber" />
					<form:hidden path="userName" />
					<input type="hidden" name="modify" value="true" />
					<form:hidden path="boardViews" />
				</c:if>
				<c:if test="${post.boardNumber==0}">
					<input type="hidden" name="userName" value="${principal.username}" />
				</c:if>
				<label for="lectureTitle" class="form-label">글 제목</label>
		        <form:input class="form-control" path="boardTitle" placeholder="제목 입력" list="datalistOptions" />
				<hr>

				<div>내용</div>
				<textarea>${post.boardContent}</textarea>
				<hr>
				
				<input id="checkbox" type="checkbox" name="boardPrivate"
					value="true" />비밀글 설정 (게시판 목록에 자물쇠로 표시되고 교수/조교와 작성자만 열람할 수 있습니다.)
				<hr>
			</form:form>
			<button class="btn btn-secondary btn-sm CUBtn" onclick="javascript:formSubmit()">등록</button>
			<button class="btn btn-secondary btn-sm CUBtn"
				onclick="javascript:movePostForm(${post.boardNumber})">취소</button>
			
			
			<form:form id="movePostForm" action="${pageContext.request.contextPath}/courses/board/read/${post.classNumber}" method="POST">
			<input type="hidden" name="boardNumber" value="${post.boardNumber}"/>
			</form:form>
			
		</div>
	</div>


</body>

</html>