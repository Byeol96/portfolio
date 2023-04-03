<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	
	<title>Login Page</title>
	<meta charset="utf-8">


	<link rel="stylesheet" href="css/plain-login.css">
	<link rel="stylesheet"
	     href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
    <div>		
		<div id="loginbox">			
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title"><h3>통합 LOGIN</h3></div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST" class="form-horizontal">
					    <div class="form-group">
					        <div class="col-xs-15">
					            <div>
									<c:if test='${param.error != null}'>   
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											아이디나 비밀번호가 일치하지 않습니다.
										</div>
									</c:if>
	
									<c:if test='${param.logout != null}'> 	            
                                        <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                            로그아웃 되셨습니다.
                                        </div>
								    </c:if>
					            </div>
					        </div>
					    </div>
						<!-- User name -->
                        <label for="inputId" class="form-label">아이디</label>
                        <input class="form-control" name="username" list="datalistOptions" id="inputId" placeholder="직번/학번을 입력하세요">
                        
                        <label for="inutPassword" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" name="password" list="datalistOptions" id="inutPassword" placeholder="비밀번호를 입력하세요">


						<div style="margin-top: 10px" class="form-group">						
							<div>
								<button type="submit" class="btn btn-info">로그인</button>
							</div>
						</div>                       
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>