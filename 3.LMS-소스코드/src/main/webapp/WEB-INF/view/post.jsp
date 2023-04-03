<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function submitModify(frm) { 
    frm.action='${pageContext.request.contextPath}/courses/board/modifyForm/${classNumber}'; 
    frm.submit(); 
    return true; 
  } 

function commentIForm(j,commentNumber) { 
	
	let commetDiv = document.querySelector('#commetDiv'+j);
	
	commetDiv.innerHTML += '<div class="singleReply"> <input type="text" id="commentInput'+j+'" name="commentContent" placeholder="댓글을 입력하세요" />'
	+' <button class="replyInsertButton btn btn-secondary btn-sm" type="submit" onclick="javascript:commentI('+j+','+commentNumber+')">등록</button></div>';


	let replyButtons = document.querySelectorAll('.reply'+j);
	for(let i = 0; i<replyButtons.length; i++){
		
		replyButtons[i].onclick='';
	}
} 

function commentI(j,commentNumber){
	let frm = document.querySelector('#commentRUForm');
	frm.action = frm.action.replace('Delete','Write');
	
	let commentContent = document.querySelector('#commentInput'+j);
	
	let commentContentInput = document.createElement('input');
	commentContentInput.setAttribute('type', 'hidden');
	commentContentInput.setAttribute('name', 'commentContent');
	commentContentInput.setAttribute('value', commentContent.value); 
	
	frm.appendChild(commentContentInput);
	
	let previousCommentNumberInput = document.createElement('input');
	previousCommentNumberInput.setAttribute('type', 'hidden');
	previousCommentNumberInput.setAttribute('name', 'previousCommentNumber');
	previousCommentNumberInput.setAttribute('value', commentNumber);  
	
	frm.appendChild(previousCommentNumberInput);
	
	
	frm.submit();
}

function commentR(commentNumber){
	
	let frm = document.querySelector('#commentRUForm');
	
	let commentNumberInput = document.createElement('input');
	commentNumberInput.setAttribute('type', 'hidden');
	commentNumberInput.setAttribute('name', 'commentNumber');
	commentNumberInput.setAttribute('value', commentNumber); 
	console.log(commentNumber);
	frm.appendChild(commentNumberInput);
	frm.submit();
}

function commentUForm(commentNumber){
	
	let commnetUpdateDiv = document.querySelector('#comment'+commentNumber);
	commnetUpdateDiv.style.display='flex';
		
	commnetUpdateDiv.innerHTML = '<input type="text" id="commentUpdateInput'+commentNumber+'" name="commentContent" placeholder="댓글을 입력하세요" />'
	+'<button type="submit" class="replyInsertButton btn btn-secondary btn-sm" onclick="javascript:commentU('+commentNumber+')">수정</button>'
	
}

function commentU(commentNumber){
	
	let frm = document.querySelector('#commentRUForm');
	frm.action = frm.action.replace('Delete','Update');
	
	let commentNumberInput = document.createElement('input');
	commentNumberInput.setAttribute('type', 'hidden');
	commentNumberInput.setAttribute('name', 'commentNumber');
	commentNumberInput.setAttribute('value', commentNumber); 
	
	let commentContent = document.querySelector('#commentUpdateInput'+commentNumber);
	
	let commentContentInput = document.createElement('input');
	commentContentInput.setAttribute('type', 'hidden');
	commentContentInput.setAttribute('name', 'commentContent');
	commentContentInput.setAttribute('value', commentContent.value); 
	
	frm.appendChild(commentNumberInput);
	frm.appendChild(commentContentInput);
	
	frm.submit();
}
</script>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
	#replyInsertButton{
		width:48px;
	}
	#postForm{
		float:right;
		margin-right : 30px;
	}
	input[type="text"]{
		width : 100%;
		height : 100px;
		
	}
	#totalComment{
		width: 100%;
		border : 1px solid black;
		margin-top: 40px;
	}
	
	.singleComment{
		widhh : 95%;
		height : 20%;
		border : 1px solid black;
		background-color : white;
		padding:5px 5px 5px 20px;
	}
	
	.singleReply{
		height : 20%;
		background-color : #cccccc;
		border : 1px solid black;
		display:flex;
		padding:5px;	
	}

	.replyMark{
		width: 5%;
    	padding-left: 15px;
		
	}
	
	.ReplyContent{
		width : 95%;
		
	}
	
	.RUBtn{
		float:right;
	}
	
	.commentDiv{
		
	}
	#postDiv{
		width: 80%;
    	margin: 20px auto 0 auto;
	}
	#subContent{
		text-align: right;
		margin-bottom : 10px;
	}
	#mainContent{
		min-height: 200px;
		margin-bottom : 10px;
	}
	#writeCommentBtn{
		margin: 5px 6px 5px 0;
		    float: right;
	}
	.RUBtn{
		margin : 0 1px;
	}
	#commentInput{
		margin-top:5px;
	}
	.reply{
		float:none;
		margin-top : 10px;
	}
	.toListBtn{
		clear:both;
	}
	.boardBtn{
		margin-top : -30px;
	}
	button{
		font-size: 12px;
	}
	
</style>
</head>
<body>
	<jsp:include page="sidebar.jsp"/>
	<jsp:include page="header.jsp"/>
	<div id="mainBody">
		<div id="postDiv">
		<security:authentication property="principal" var="principal" />
		<security:authentication property="principal.username" var="username" />

		<div class="post-title"><h3>${post.boardTitle}</h3></div>
		<hr>
		<div class="post-content">
			<div id="subContent">
				<span><b>작성자:</b>${post.userName} </span> <span><b>등록일시:</b>${post.boardDate}</span>
				<span><b>조회수:</b>${post.boardViews}</span>
			</div>
			<div id="mainContent">${post.boardContent}</div>
			<div>
				
				<form:form action="${pageContext.request.contextPath}/courses/board/${classNumber}" method="POST">
				<button id="toListBtn" class="btn btn-secondary btn-sm">목록</button>
				</form:form>
			
				<c:if test="${principal.username.equals(post.userName)}">
					<form:form
						action="${pageContext.request.contextPath}/courses/board/delete/${classNumber}"
						id="postForm" method="POST">
						<input type="hidden" name="boardNumber"
							value="${post.boardNumber}" />
						<button class="btn btn-danger btn-sm RUBtn boardBtn" type="submit">삭제</button>
						<button class="btn btn-secondary btn-sm RUBtn boardBtn" type="submit" onclick="return submitModify(this.form);">
							수정</button>
					</form:form>
				</c:if>
				<c:if test="${!principal.username.equals(post.userName)}">
					<security:authorize access="hasRole('PROFESSOR')">
						<form:form
							action="${pageContext.request.contextPath}/courses/board/delete/${classNumber}"
							id="postForm" method="POST">
							<input type="hidden" name="boardNumber"
								value="${post.boardNumber}" />
							<button class="btn btn-danger btn-sm RUBtn boardBtn" type="submit">삭제</button>
							<button class="btn btn-secondary btn-sm RUBtn boardBtn" type="submit" onclick="return submitModify(this.form);">
								수정</button>
						</form:form>
					</security:authorize>
				</c:if>
			</div>
		</div>
		<hr>
		댓글<br>

		<form:form
			action="${pageContext.request.contextPath}/courses/board/commentWrite/${classNumber}"
			method="POST">
			<input type="hidden" name="postNumber" value="${post.boardNumber}" />
			<input id="commentInput" type="text" name="commentContent" placeholder="댓글을 입력하세요" />
			<button id="writeCommentBtn" class="btn btn-secondary btn-sm" type="submit">
				등록</button>
		
		</form:form>
		
		<div id="totalComment">
			<form:form action="${pageContext.request.contextPath}/courses/board/commentDelete/${classNumber}" method="POST" id="commentRUForm">
				<input type="hidden" name="postNumber" value="${post.boardNumber}" />
			</form:form>
			<c:forEach var="commentList" items="${commentCollections}"
				varStatus="j">

				<div id="commetDiv${j.count}">
					<c:forEach var="comment" items="${commentList}" varStatus="i">

						<c:if test="${i.count==1}">
							<div class="singleComment" id="comment${comment.commentNumber}">
							<div class="CommentHeader">
								<span> 작성자 : ${comment.userName} <c:if test="${comment.userName eq post.userName}">(작성자)</c:if></span>
								<c:if test="${username eq comment.userName}">
									<button class="btn btn-danger btn-sm RUBtn" onclick="commentR(${comment.commentNumber})">삭제</button>
									<button class="btn btn-secondary btn-sm RUBtn" onclick="commentUForm(${comment.commentNumber})">수정</button>
								</c:if>
								<c:if test="${username != comment.userName}">
									<security:authorize access="hasRole('PROFESSOR')">
									<button class="btn btn-danger btn-sm RUBtn" onclick="commentR(${comment.commentNumber})">삭제</button>
									<button class="btn btn-secondary btn-sm RUBtn" onclick="commentUForm(${comment.commentNumber})">수정</button>
									</security:authorize>
								</c:if>
								</div>
								<div class="CommentBottom">
								<br> <span class="commentContentSpan">${comment.commentContent} </span><br>
								<button class="reply btn btn-secondary btn-sm" onclick="commentIForm(${j.count},${commentList.get(commentList.size()-1).commentNumber})">답글</button>
								</div>
							</div>
						</c:if>

						<c:if test="${i.count!=1}">
							<div class="singleReply">
							<div class="replyMark">
								
							</div>
							<div class="ReplyContent" id="comment${comment.commentNumber}">
								<div class="ReplyHeader">
								<span> 작성자 : ${comment.userName} <c:if test="${comment.userName eq post.userName}">(작성자)</c:if></span>
								<c:if test="${username eq comment.userName}">
									<button class="btn btn-danger btn-sm RUBtn" onclick="commentR(${comment.commentNumber})">삭제</button>
									<button class="btn btn-secondary btn-sm RUBtn" onclick="commentUForm(${comment.commentNumber})">수정</button>
								</c:if>
								<c:if test="${username != comment.userName}">
									<security:authorize access="hasRole('PROFESSOR')">
									<button class="btn btn-danger btn-sm RUBtn" onclick="commentR(${comment.commentNumber})">삭제</button>
									<button class="btn btn-secondary btn-sm RUBtn" onclick="commentUForm(${comment.commentNumber})">수정</button>
									</security:authorize>
								</c:if>
								</div>
								<div class="ReplyBottom">
								<br> <span class="commentContentSpan">${comment.commentContent} </span><br>
								<button class="reply btn btn-secondary btn-sm" onclick="commentIForm(${j.count},${commentList.get(commentList.size()-1).commentNumber})"> 답글</button>
								</div>
							</div>
							</div>
						</c:if>

					</c:forEach>
				</div>
			</c:forEach>
	
		</div>
		</div>
</div>
	
</body>
</html>