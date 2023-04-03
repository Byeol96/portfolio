<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <link href="${path}/css/main.css" rel="stylesheet"/>
    
<head>
<title>Eclass Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>
	<jsp:include page="sidebar.jsp"/>
	<jsp:include page="header.jsp"/>
	<div id="mainBody">
	<b> 최근 공지사항</b><br>
	<c:forEach var="notice" items="${recentNoticeList}">
	${notice}<br>
	</c:forEach>
	</div>

</body>
</html> --%>
<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>  
<head>
<title>Eclass Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>

	#mainBody2{
	width: 75%;
	margin: 0 auto;
	font-size: 25px;
	margin-top:100px;
	}
	
	.nd{
	float: right;
	font-size: 15px;
	}
	.clearfix{
	margin-bottom: 15px;
	}
	
	.clearfix:before, .clearfix:after {
	    display: block;
	    content: '';
	    line-height: 0;
	}
	
	.clearfix:after {
	    clear:both;
	}
	
	#mainBody2 .ni{
	float: left;
	margin: 10px 30px;
	}
	
	#mainBody2 .nm{
	float: left;
	}
	
	#mainBody2 .ndd{
	float: right;
	}		
		
</style>

<script>
	function submitPost(noticeNumber){
    let fm= document.getElementById("noticeSubmit")
    
    let obj2 = document.createElement('input');
    obj2.setAttribute('type', 'hidden');
    obj2.setAttribute('name', 'noticeNumber');
    obj2.setAttribute('value', noticeNumber);
    
    fm.appendChild(obj2);
   
    fm.submit();
}

</script>

</head>


<body>
	<jsp:include page="sidebar.jsp"/>
	<jsp:include page="header.jsp"/>
		<div >
			<div id="mainBody2">
			<b>최근 공지사항</b><hr>
				<form:form action="${pageContext.request.contextPath}/courses/notice/read/${classNumber}" id="noticeSubmit" method="POST"></form:form>
				<c:forEach var="notice" items="${recentNoticeList}">
				<div class="clearfix">
					<div class="ni">
					<img src="${path}/img/icon/mypage.jpg" width="50" height="50"><br>
					</div>
					<div class="nm">
					<b><span onclick="javascript:submitPost(${notice.noticeNumber})">${notice.noticeTitle}</span></b>
					<c:set var="nt" value="${notice.noticeContent}"/>
						<br>
						<c:choose>
							<c:when test="${nt.length() > 30}">
								<c:out value="${fn:substring(nt, 0, 30)}...">
								</c:out>
							</c:when>
							<c:otherwise>
								${nt}
							</c:otherwise>
						</c:choose>
					</div>
					<div class="ndd">	
							<span class="nd"><b>게시일시</b></span><br>
							<span class="nd">${String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tI:%1$tS", notice.noticeDate)}</span>
					</div>	
					<hr>
				</div>
				</c:forEach>
			</div>
		</div>
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>  
<head>
<title>Eclass Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>

	#mainBody2{
	width: 75%;
	margin: 0 auto;
	font-size: 15px;
	margin-top:100px;
	}
	

	
	.clearfix{
	margin-bottom: 15px;
	}
	
	.clearfix:before, .clearfix:after {
	    display: block;
	    content: '';
	    line-height: 0;
	}
	
	.clearfix:after {
	    clear:both;
	}
	
	#mainBody2 .ni{
	float: left;
	margin: 10px 30px;
	}
	
	#mainBody2 .nm{
	float: left;
	font-size: 14px;
	margin-top: 17px;
	}
	#mainBody2 .nm span{
		font-size: 14px;
		font-weight :700;
	}
	#mainBody2 .ndd{
	float: right;
	font-size: 20px;
	margin-top: 11px;
	}		
	
	.nd{
	float: right;
	font-size: 12px;
	}	
	
	.postDate{
		font-weight :700;
	}
</style>

<script>
	function submitPost(noticeNumber){
    let fm= document.getElementById("noticeSubmit")
    
    let obj2 = document.createElement('input');
    obj2.setAttribute('type', 'hidden');
    obj2.setAttribute('name', 'noticeNumber');
    obj2.setAttribute('value', noticeNumber);
    
    fm.appendChild(obj2);
   
    fm.submit();
}

</script>

</head>


<body>
	<%    
	response.setHeader("Cache-Control","no-store");    
	response.setHeader("Pragma","no-cache");    
	response.setDateHeader("Expires",0);    
	if (request.getProtocol().equals("HTTP/1.1"))  
        response.setHeader("Cache-Control", "no-cache");  
%>
	<jsp:include page="sidebar.jsp"/>
	<jsp:include page="header.jsp"/>
		<div >
			<div id="mainBody2">
			<b>최근 공지사항</b><hr>
				<form:form action="${pageContext.request.contextPath}/courses/notice/read/${classNumber}" id="noticeSubmit" method="POST"></form:form>
				<c:forEach var="notice" items="${recentNoticeList}">
				<div class="clearfix">
					<div class="ni">
					<img src="${path}/img/icon/user.jpg" width="50" height="50"><br>
					</div>
					<div class="nm" onclick="javascript:submitPost(${notice.noticeNumber})">
					<span>${notice.noticeTitle}</span>
					<c:set var="nt" value="${notice.noticeContent}"/>
						<br>
						<c:choose>
							<c:when test="${nt.length() > 30}">
								<c:out value="${fn:substring(nt, 0, 30)}...">
								</c:out>
							</c:when>
							<c:otherwise>
								${nt}
							</c:otherwise>
						</c:choose>
					</div>
					<div class="ndd">	
							<span class="nd postDate">게시일시</span><br>
							<span class="nd">${String.format("%1$tY년 %1$tm월 %1$td일 %1$tH시 %1$tI분", notice.noticeDate)}</span><br>
					</div>	
				</div>
				<hr>
				</c:forEach>
					<c:if test="${recentNoticeList.size()==0}">
						등록된 공지사항이 없습니다.
					</c:if>
				<div>
					
				</div>
			</div>
		</div>
</body>
</html>