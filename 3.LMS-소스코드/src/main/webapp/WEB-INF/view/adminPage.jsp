<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/plain-login.css">
	<link rel="stylesheet"
	     href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	     <script>
	     	window.onload=function(){
	     		
	     		if('${username}'){
	     			if('${userInfos}'){
	     			document.querySelector('#username').readOnly=true;
	     			document.querySelector('#searchSpan').textContent='검색할 ID(학번)'
	     			document.querySelector('#submitBtn').textContent='변경';
	     			document.querySelector('#submitBtn').style.display='none';
	     			const form = document.querySelector('form');
	     			form.action = form.action.replace('Search','ModifyForm');
	     			
	     			let obj = document.createElement('input');
	     			obj.setAttribute('type', 'hidden');
	     			obj.setAttribute('name', 'modifyUserName');
	     			obj.setAttribute('value', '0');
	     			obj.className="modifyUserName";
	     			form.appendChild(obj);
	     			
	     			}
	     		}
	     	}
	    	
	     	let selectedTd ='';
	     	let count = 0;
	     	function selectTd(thisTd){
	     		if(count==0){
	     			
	     			document.querySelector('#submitBtn').style.display='initial';
	     			count=1;
	     		}
	     		let inputData = document.querySelector('.modifyUserName');
	     		if(selectedTd){
	     			selectedTd.className = 'nonCheckTd';
	     		}
	     		thisTd.className ='checkTd';
	     		selectedTd =thisTd;	
	     		inputData.value=thisTd.querySelector('span').textContent.trim();
	     	}
	     </script>
	     <style>
	     	td{
	     		padding:3px 0;
	     	}
	     	.nonCheckTd{
	     		border : solid 1px #cccccc;
	     	}
	     	.checkTd{
	     		border : solid 3px blue;
	     	}
	     </style>
</head>
<body>
	    <div>		
		<div id="loginbox">			
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title"><h4><b>${pageHeader}</b></h4></div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<div class="form-group">
      			<form:form action="${pageContext.request.contextPath}/admin/userSearch" method="POST"> 


		

				<table>
			    <tbody>
			    <!-- 하는중 -->
			    <!-- 완료 -->
			    
			    
			    			    <!-- 하는중 -->
				   	<tr>	
					    <td>
	                        <label for="username" class="form-label" id="searchSpan">검색할 ID(학번)</label><br>
	                        <input type="text" class="form-control" value="${username}" name="username" id="username" />
						<br>   
						</td>
              		</tr>
              		<c:forEach var ="userInfo" items="${userInfos}">
              			<tr><td class="nonCheckTd" onclick="selectTd(this)">학번 : <span>${userInfo.username}	</span><br>
              					이름 : ${userInfo.name} <br>
              					생일 : ${userInfo.birth}</td></tr>
              		</c:forEach>
				</tbody>
				</table>	
						
						
						
              					    <!-- 하는중 -->										
						<div style="margin-top: 10px" class="form-group">						
							<div>
								<button id = "submitBtn"type="submit" class="btn btn-info changePW" class="save">검색</button>								 
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