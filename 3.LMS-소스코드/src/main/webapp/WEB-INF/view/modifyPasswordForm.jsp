<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>


	<meta charset="utf-8">


	<link rel="stylesheet" href="css/plain-login.css">
	<link rel="stylesheet"
	     href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>비밀번호 변경
</title>
<script>
	
	let changeOK='';
	function confirmSubmit(){
		
		if(changeOK){
			const form = document.querySelector('#passwordChangeForm');
		
			form.submit();
		}
		
	}
	function confirmInputPassword(){
		
		const passwordInput = document.querySelector('#changePassword');
		const passwordInputConfirm = document.querySelector('#changePasswordConfrim');
		const changeBtn = document.querySelector('.changePW');
		
		const confirmMessage = document.querySelector('#confirmMessage');
		if(passwordInput.value == passwordInputConfirm.value){
			
			if('${userList.password}'.replace('{noop}','') != passwordInput.value){
				confirmMessage.innerText ='입력한 두 비밀번호가 동일합니다.'
				confirmMessage.style.color="blue";
				changeOK='OK';
			}else{
				confirmMessage.innerText ='이전 비밀번호와 동일합니다. 다른 비밀번호를 입력해 주세요.'
				confirmMessage.style.color="red";
				changeOK='';
				
			}
		}else{
			
			confirmMessage.innerText ='입력한 두 비밀번호가 동일하지 않습니다. 다른 비밀번호를 입력해 주세요.'
			confirmMessage.style.color="red";
			changeOK='';
		}
	}
</script>
<style>
	
	.inLabel{
		margin-left : 1px;
		margin-top: 5px;
	}
</style>
</head>
<body>
    <div>		
		<div id="loginbox">			
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title"><h4><b>비밀번호 변경</b></h4></div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<div class="form-group">
				      <form:form id ="passwordChangeForm" action="${pageContext.request.contextPath}/modifyPasswordForm/save" modelAttribute="userList" method="POST"> 
				      <form:hidden path="username"/>
				      <form:hidden path="enabled"/>			     
					    
		                        <label for="changePassword" class="form-label inLabel">새로운 비밀번호</label>
		                        <input class="form-control inPass" type="password" name="password" id="changePassword" list="datalistOptions">
							
		           				
		                        <label for="changePasswordConfrim" class="form-label inLabel">새 비밀번호 확인</label>
		                        <input type="password" class="form-control inPass" name="passwordConfirm" list="datalistOptions" id="changePasswordConfrim" oninput="confirmInputPassword()">
								<span id="confirmMessage"></span>
			             	</form:form>				
						<div style="margin-top: 10px" class="form-group">						
							<div>
								<button type="button" class="btn btn-secondary goHome"
									 onclick="window.location.href='${pageContext.request.contextPath}/'">취소</button>	
								<button type="button" class="btn btn-info changePW" onclick=" confirmSubmit()">변경</button>								 
															 
							</div>
						</div>
						<br>                 
					
					</div>
				</div>
			</div>
		</div>
	</div>	
	
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>


	<meta charset="utf-8">


	<link rel="stylesheet" href="css/plain-login.css">
	<link rel="stylesheet"
	     href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>비밀번호 변경
</title>
<script>
	let confirmSubmitMsg ='';
	function confirmSubmit(){

		
		if(confirmSubmitMsg){
			const form = document.querySelector('#passwordChangeForm');
			form.submit();
		}
		

		
	}
	function confirmInputPassword(){
		
		const passwordInput = document.querySelector('#changePassword');
		const passwordInputConfirm = document.querySelector('#changePasswordConfrim');
		const changeBtn = document.querySelector('.changePW');
		
		
		const confirmMessage = document.querySelector('#confirmMessage');
		if(passwordInput.value == passwordInputConfirm.value){
			
			if('${userList.password}'.replace('{noop}','') != passwordInput.value){
				confirmMessage.innerText ='입력한 두 비밀번호가 동일합니다.'
				confirmMessage.style.color="blue";
				confirmSubmitMsg =	'OK';
			}else{
				confirmMessage.innerText ='이전 비밀번호와 동일합니다. 다른 비밀번호를 입력해 주세요.'
				confirmMessage.style.color="red";
				confirmSubmitMsg =	'';
			}
		}else{
			
			confirmMessage.innerText ='입력한 두 비밀번호가 동일하지 않습니다. 다른 비밀번호를 입력해 주세요.'
			confirmMessage.style.color="red";
			confirmSubmitMsg =	'';
		}
	}
</script>
<style>
	
	.inLabel{
		margin-left : 1px;
		margin-top: 5px;
	}
</style>
</head>
<body>
    <div>		
		<div id="loginbox">			
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title"><h4><b>비밀번호 변경</b></h4></div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<div class="form-group">
				      <form:form id ="passwordChangeForm" action="${pageContext.request.contextPath}/modifyPasswordForm/save" modelAttribute="userList" method="POST"> 
				      <form:hidden path="username"/>
				      <form:hidden path="enabled"/>			     
					    
		                        <label for="changePassword" class="form-label inLabel">새로운 비밀번호</label>
		                        <input class="form-control inPass" type="password" name="password" id="changePassword" list="datalistOptions">
							
		           				
		                        <label for="changePasswordConfrim" class="form-label inLabel">새 비밀번호 확인</label>
		                        <input type="password" class="form-control inPass" name="passwordConfirm" list="datalistOptions" id="changePasswordConfrim" oninput="confirmInputPassword()">
								<span id="confirmMessage"></span>
			             	</form:form>				
						<div style="margin-top: 10px" class="form-group">						
							<div>
								<button type="submit" class="btn btn-info changePW" onclick="confirmSubmit()">변경</button>		
								<button type="button" class="btn btn-secondary goHome"
									 onclick="window.location.href='${pageContext.request.contextPath}/'">취소</button>														 															 
							</div>
						</div>
						<br>                 
					
					</div>
				</div>
			</div>
		</div>
	</div>	
	
</body>
</html>