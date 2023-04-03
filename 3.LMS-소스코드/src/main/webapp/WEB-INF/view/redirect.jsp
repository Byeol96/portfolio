<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	window.onload = function(){
		
		const form = document.querySelector('form');
		
		form.submit();
	}
	
	
</script>
</head>
<body>

	<form:form action="${pageContext.request.contextPath}${url}" method="POST">
	<c:forEach var="input" items="${inputs}">
	<input type="hidden" name="${input.inputName}" value="${input.inputValue}"/>
	</c:forEach>
	</form:form>
</body>
</html>