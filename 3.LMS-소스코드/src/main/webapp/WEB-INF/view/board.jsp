<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script>
	

	window.onload = function(){
		let pageNumber = ${boardList.pageNumber};
		let pageDiv = document.querySelector('#pageNumber'+pageNumber);
	
		pageDiv.onclick = null;
		
		let select = '${boardList.searchTaget}';
		let option = document.querySelectorAll("option");
		if(!select) return;
							
								
		for(let i = 0; i <option.length; i++){
									
			if(select === option[i].value){
										
				option[i].selected=true;
			}
		}
		
		
							
	}
	
	function writeFormPost(){
		
		let fm1 = document.querySelector('#writeForm');
	
	    fm1.submit();
	}
	
	

	function readPost(postNumber){
		
		let fm2 = document.querySelector('#readBoard');

	    let obj2 = document.createElement('input');
		obj2.setAttribute('type', 'hidden');
		obj2.setAttribute('name', 'boardNumber');
		obj2.setAttribute('value', postNumber);
		
	    fm2.appendChild(obj2);
	    fm2.submit();
	    
	}

 	function pageMoves(pageNumber){
	 
		 let fm3 = document.querySelector('#pageMoveForm4');
		 
		 let obj2 = document.createElement('input');
		 obj2.setAttribute('type', 'hidden');
		 obj2.setAttribute('name', 'pageNumber');
		 obj2.setAttribute('value', pageNumber);
		 
		 if('${boardList.searchTaget}'){
		 	let obj3 = document.createElement('input');
		 	obj3.setAttribute('type', 'hidden');
		 	obj3.setAttribute('name', 'searchTaget');
		 	obj3.setAttribute('value', '${boardList.searchTaget}');
		 	fm3.append(obj3);
	 	}
		 if('${boardList.searchContent}'){
		 	let obj4 = document.createElement('input');
		 	obj4.setAttribute('type', 'hidden');
		 	obj4.setAttribute('name', 'searchContent');
		 	obj4.setAttribute('value', '${boardList.searchContent}');
		 	fm3.append(obj4);
		 }
		console.log(fm3.action);
		fm3.append(obj2);
		fm3.submit();
 	}
</script>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.board-outline {
	width: 90%;
	height: 20%;
	border: 1px solid black;
	margin: 10px auto 10px auto;
}

.contentTotal {
	width: 100%;
}

.boarder-header {
	width: 90%;
	height: 20%;
	border: 1px solid black;
	padding: 9px 15px;
	margin: 12px auto;
	
}

hr {
	width: 90%;
}
.titleSpan{
	font-size : 16px;
}
.wirte-div {
	align-items: center;
	border-radius: 5px;
	padding: 9px 15px;
	font-size: 12px;
	line-height: 17px;
	background-color: rgb(21, 126, 244);
	color: rgb(255, 255, 255);
	margin-top: -2px;
	float: right;
	width: 90px;
}
.lockImg{

	width : 13px;
}

#WirteImg{
	width:13px;
}

.boarder-nonContent {
	float: right;
}

.search-div {
	width: 90%;
	margin: 0 auto;
	padding: 9px 12px;
}

.total-div {
	width: 90%;
	margin: 0 auto;
}

table {
	width: 90%;
	margin: 0 auto;
	text-align: center;
}

tr {
	height: 40px;
	border-bottom : solid 1px #cccccc;
}

#paging {
	text-align: center;
	margin: 10px auto 0 auto;
	font-size :13px;
}
.noTd{
	width : 8%;
}
.writerTd{
	width : 15%;
}
.dateTd{
	width : 20%;
}
.viewTd{
	width : 15%;
}
.pageDiv{
	font-size: 16px;
    margin: 1px 3px;
}
#boardNameSpan{
	font-size:20px;
}
#searchCountSpan{
	font-size:15px;
}
hr{
	    margin: 1rem auto;
}
#nextBtn{
	margin-top: -2px;
}

#preBtn{
	margin-top: -2px;
}
</style>
</head>
<body>

	<jsp:include page="sidebar.jsp"/>
	<jsp:include page="header.jsp"/>
	<div id="mainBody">
	<security:authentication property="principal" var="p" />

	<div class="total-div">
		<div class="boarder-header">
			<span id="boardNameSpan"> <b> Q&amp;A 게시판${c} </b></span>
			<div class="wirte-div" onclick="javascript:writeFormPost()">
				<form:form action="${pageContext.request.contextPath}/courses/board/writeForm/${classNumber}" method="POST" id="writeForm" ></form:form>
				<img src="${pageContext.request.contextPath}/img/Write.png" id="WirteImg"/> 글쓰기
			</div>
		</div>
		<div class="search-div">
			<span id="searchCountSpan"> 검색결과 ${boardList.searchCount} 개</span>
			<div class="boarder-nonContent">
				<form:form action="${pageContext.request.contextPath}/courses/board/search/${classNumber}" method="POST">
					<select name="searchTaget">
						<option value="boardTitle">제목</option>
						<option value="boardContent">내용</option>
						<option value="name">이름</option>
						<option value="userName">학번</option>
					</select>

					<input type="text" name="searchContent"
						value="${boardList.searchContent}" placeholder="검색어 입력" />
					<input type="submit" />

				</form:form>
			</div>
		</div>
		<hr>
		<div class="contentTotal">
			
			<table>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일시</th>
					<th>조회수</th>
				</tr>

				<c:if test="${empty boardList.boardList}">
					<td colspan="5">등록된 게시물이 없습니다.</td>
				</c:if>
				<c:if test="${boardList.boardList.size()!=0}">
					<form:form action="${pageContext.request.contextPath}/courses/board/read/${classNumber}" 
						id="readBoard" method="POST"></form:form>
					<c:forEach var="board" items="${boardList.boardList}" varStatus="num">
						<tr>
							<td class="noTd">${(boardList.pageNumber-1)*10+num.count}</td>
							<c:choose>
								<c:when test="${board.boardPrivate}">
									<security:authorize
										access="hasAnyRole('PROFESSOR','ASSISTANT')">
										<td onClick="javascript:readPost(${board.boardNumber})"> <img class="lockImg" src="${pageContext.request.contextPath}/img/Lock.png"/>
									</security:authorize>

									<security:authorize access="hasRole('STUDENT')">
										<c:choose>
											<c:when test="${p.username.equals(board.userName)}">
												<td onClick="javascript:readPost(${board.boardNumber})"> <img class="lockImg" src="${pageContext.request.contextPath}/img/Lock.png"/>
											</c:when>
											<c:otherwise>
												<td> <img class="lockImg" src="${pageContext.request.contextPath}/img/Lock.png"/>
											</c:otherwise>
										</c:choose>
									</security:authorize>
								</c:when>
								<c:otherwise>
									<td onClick="javascript:readPost(${board.boardNumber})">
								</c:otherwise>
							</c:choose>
							<span class="titleSpan" >${board.boardTitle}</span>
							</td>
							<td class="wirterTd">${board.userName}</td>
							<td class="dateTd">${String.format("%tm-%td %tR", board.boardDate,board.boardDate,board.boardDate)}</td>
							<td class="viewTd">${board.boardViews}</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>

			<div id="paging">
				<form:form action="${pageContext.request.contextPath}/courses/board/search/${classNumber}" id="pageMoveForm4"  method="post"></form:form>
				<c:if test="${boardList.previous}">
					<button type="button" id="preBtn" class="btn btn-secondary btn-sm" onClick="pageMoves(${boardList.start-1})" >이전</button>
				</c:if>

				<c:forEach var="pageNumber" begin="${boardList.start}"
					end="${boardList.end}">
					<span class="pageDiv" id="pageNumber${pageNumber}"
						onClick="pageMoves(${pageNumber})"> ${pageNumber}
					</span>
				</c:forEach>
				<c:if test="${boardList.next}">
					<button type="button" id="nextBtn" class="btn btn-secondary btn-sm" onClick="pageMoves(${boardList.end+1})" >다음</button>
					
				</c:if>
				
			</div>
		</div>
	</div>
	</div>
	
	
</body>

</html>