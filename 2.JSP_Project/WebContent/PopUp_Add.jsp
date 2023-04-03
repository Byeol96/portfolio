<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<script src = "js/PopUp_AddJs.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="css/PopUp_AddCss.css"/>
</head>
<body>
<!-- method="post" enctype="multipart/form-data" -->
<form action="ProjectServlet1">
<input type="hidden" name="command" value="MenuAdd"/>
<div id="total">
	<div id ="fileInput"  >
	<img id="preview" src="https://i0.wp.com/adventure.co.kr/wp-content/uploads/2020/09/no-image.jpg" /><br>
	<input type = "file" id="file" name ="fileName" required/>
	</div>
	<div id ="inputDiv" > <br><br>
	<table>
	<tr> <td class="leftTd">메뉴명</td><td class="RightTd">: <input type="text" name ="MenuName" required/></td></tr>
	<tr> <td class="leftTd">가격</td><td class="RightTd">: <input type="text" name ="MenuCost" required/></td></tr>
	<tr> <td class="leftTd">카테고리</td ><td class="RightTd">:  <select name ="MenuCategory">
		<c:forEach var="category" items='<%=request.getAttribute("MenuCategory") %>'>
		<option value="${category.getNum()}"> ${category.getName()} </option>
		</c:forEach>
	</select></td></tr>
	</table>
	<br>
	<input type="submit" value="추가"/>
	</div>
	</div>
</form>
</body>
</html>

