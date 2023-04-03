
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/plain-login.css">
	<link rel="stylesheet"
	     href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
</style>

</head>


<body>
	  
    <div>		
		<div id="loginbox">			
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title"><h4><b>회원정보 수정</b></h4></div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<div class="form-group">
      <form:form action="${pageContext.request.contextPath}/admin/modifyUserInfo" modelAttribute="userInfo" method="POST"> 
      <form:hidden path="username"/>
      <form:hidden path="etc" />

		

				<table>
			    <tbody>
			    <!-- 하는중 -->
			    <!-- 완료 -->
			    
			    	
			    			    <!-- 하는중 -->
				   	<tr>	
					    <td>
	                        <label for="major" class="form-label">전공</label>
	                        <form:input type="text" class="form-control" path="major"/>
						<br>
						</td>
              		</tr>
              	
              					    <!-- 하는중 -->			    
				    <tr>
					    <td>
                        <label for="email" class="form-label">Email</label>
                        <form:input path="email" class="form-control" id="email" list="datalistOptions"/>
						<br>
						</td>
					</tr>
				   	<tr>
			   					    <!-- 하는중 -->
              		<tr>							
					    <td>
                        <label for="name" class="form-label">이름</label>
                        <form:input type="text" class="form-control" path="name" id="name" readOnly='true'/>
						<br>
						</td>
              		</tr>
              					    <!-- 하는중 -->
              		<tr>				
					    <td>
                        <label for="birth" class="form-label">생일</label>
                        <input type="text" class="form-control" value="${userInfo.birth}" name="birthDay" id="birth" readOnly='true'	/>
						<br>
						</td>
              		</tr>
              		<c:if test='${auth eq "ROLE_STUDENT"}'>
              		<tr>				
					    <td>
                        <label for="auth" class="form-label">직책변경</label>
                        <input type="radio" name="auth" value="ROLE_ASSISTANT"> 조교	
                        <%-- <input type="text" class="form-control" name="auth" value="${auth}" id="auth"/> --%>
						<br>
						</td>
              		</tr>
              		</c:if>
              		
              		
				</tbody>
				</table>	
						
						
							
              					    <!-- 하는중 -->										
						<div style="margin-top: 10px" class="form-group">						
							<div>
								<button type="submit" class="btn btn-info changePW" class="save">변경</button>								 
								<button type="button" class="btn btn-secondary goHome"
								 onclick="window.location.href='${pageContext.request.contextPath}/'">취소</button>								 
							</div>
						</div>        
         </form:form>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>
