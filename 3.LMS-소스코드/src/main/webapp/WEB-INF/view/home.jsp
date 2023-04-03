<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="${path}/css/home.css" rel="stylesheet"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- path로 받았음 -->


<title>Insert title here</title>
<script>
	
	window.onload=function(){
		
		if(!'${passwordChange}') return;
		
		if(confirm('초기설정 비밀번호와 동일합니다. 비밀번호를 변경하시겠습니까?')){
			
			document.querySelector('#passwordChange').click();
		}
		
		
	}
	
	function createSubmit(auth){
		
		const form = document.querySelector('#createAccount');
		
		  let obj2 = document.createElement('input');
			obj2.setAttribute('type', 'hidden');
			obj2.setAttribute('name', 'auth');
			obj2.setAttribute('value', 'ROLE_'+auth);
			
			form.appendChild(obj2);
			form.submit();
	}
	
	
</script>
<style>
	#loginBt{
		 margin-top: -74px;
	}
	.button{
		 margin-top: 60px;
	}
</style>
</head>
<body>

    <div id="header">
        <img src="${path}/img/home/logo2.jpg" width="300" height="100%" style="margin-left: 10px;">
        <security:authorize access="isAuthenticated()">
        	<form:form action="${pageContext.request.contextPath}/logout" method="post">
        	<button id="loginBt" class="btn btn-info a" >로그아웃</button>
        	</form:form>
        </security:authorize>
    </div>
    <div id="content" class="clearfix">
        <div class="main1">    
                <div id="demo" class="carousel slide" data-ride="carousel">

                    <!-- Indicators -->
                    <ul class="carousel-indicators">
                      <li data-target="#demo" data-slide-to="0" class="active"></li>
                      <li data-target="#demo" data-slide-to="1"></li>
                      <li data-target="#demo" data-slide-to="2"></li>
                    </ul>
                    
                    <!-- The slideshow -->
                    <div class="carousel-inner">
                      <div class="carousel-item active">
                        <img src="${path}/img/home/school1.jpg" alt="Los Angeles" width="900" height="400">
                      </div>
                      <div class="carousel-item">
                        <img src="${path}/img/home/school2.jpg" alt="Chicago" width="900" height="400">
                      </div>
                      <div class="carousel-item">
                        <img src="${path}/img/home/school3.jpg" alt="New York" width="900" height="400">
                      </div>
                    </div>
                    
                    <!-- Left and right controls -->
                    <a class="carousel-control-prev" href="#demo" data-slide="prev">
                      <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#demo" data-slide="next">
                      <span class="carousel-control-next-icon"></span>
                    </a>
                  </div>           

        </div>
        <div class="login">
            <div class="button">
          
			   <security:authorize access="isAnonymous()">
			   		<button id="mainLogin" class="btn btn-danger" onclick="window.location.href='login'">통합 로그인</button>
			   </security:authorize>
			   
   <security:authorize access="hasAnyRole('PROFESSOR','STUDENT','ASSISTANT')">
   
      <button class="btn btn-info" onclick="window.location.href='myPage'"> 마이페이지 </button>
      <button class="btn btn-danger" onclick="window.location.href='modifyUserInfo'"> 회원정보수정 </button>
      <button class="btn btn-danger" onclick="window.location.href='modifyPassword'" id="passwordChange"> 비밀번호 변경 </button>
      
   </security:authorize>

   <security:authorize access="hasRole('ADMIN')">
   		<form:form action="${pageContext.request.contextPath}/admin/createMemberAccount" method="POST" id="createAccount"></form:form>
      <button class="btn btn-danger" onclick="createSubmit('STUDENT')"> 학생계정생성 </button>
      <button class="btn btn-danger" onclick="createSubmit('PROFESSOR')"> 교수계정생성 </button>
      <button class="btn btn-danger" onclick="window.location.href='${pageContext.request.contextPath}/admin/member'"> 회원관리 </button>
   </security:authorize>


            </div>
        </div>
    </div>
    <div id="main2">
        <img src="${path}/img/home/bt1.jpg" width="1200px" height="100%">
    </div>         
    <div id="main3">
        <img src="${path}/img/home/bt2.jpg" width="1200px" height="100%">
    </div>        
</body>
</html>