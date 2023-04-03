<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src = "js/PopUp_UpdateJs.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="css/PopUp_UpdateCss.css"/>
</head>
<body>
	<div id="total">
		<form action="ProjectServlet1">
		<input type="hidden" name="command" value="PopUp_UpdateMenuPage_Move"/>
			<div id="fileInput">
				<br>
				<br>
				<br>
				<br>
				<br> 카테고리 : <select name="MenuCategory">
					<c:forEach var="category"
						items='<%=request.getAttribute("MenuCategory")%>'>
						<option value="${category.getNum()}">
							${category.getName()}</option>
					</c:forEach>
				</select> <br>
				<br> <input type="submit" value="메뉴 업데이트" id="submitA" />
			</div>

			<div id="inputDiv">

				<c:forEach var="menus" items='<%=request.getAttribute("Menus")%>'>
					<table id="table${menus[0].getCategoryId()}">
						<c:forEach var="menu" items="${menus}">
							<tr>
								<td>${menu.getMenuName()}</td>
								<td><input type="radio" name="updateMenuName"
									value="${menu.getMenuName()}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:forEach>
				
			</div>
		</form>
	</div>
</body>