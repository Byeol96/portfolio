<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="com.web.jdbc.MenuData" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<script src = "js/PopUp_UpdateMenuPageJs.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="css/PopUp_UpdateMenuPageCss.css"/>
</head>

<body>
<script>
Console.log('${move}');
if ('${move}' == "yes")
	window.close();
	</script>
<form action="ProjectServlet1">
<input type="hidden" name="command" value="MenuUpdate"/>
<% MenuData menu = (MenuData)request.getAttribute("UpdateMenu");%>
<div id="total">
	<div id ="fileInput"  >
	<img id="preview" src='img/${UpdateMenu.getCategoryId()}/${UpdateMenu.getMenuName()}.jpg' /><br>
	<input type = "file" id="file"/>
	</div>
	<div id ="inputDiv" > <br><br>
	<table>
	<tr> <td class="leftTd">메뉴명</td><td class="RightTd">: <%= menu.getMenuName()%></td></tr>
	<tr> <td class="leftTd">가격</td><td class="RightTd">: <input type="text" name ="MenuCost" value="<%= menu.getPrice()%>"required/></td></tr>
	<tr> <td class="leftTd">카테고리</td ><td class="RightTd">: 
			<c:forEach var="category" items='<%=request.getAttribute("MenuCategory") %>'>
			<c:if test="${UpdateMenu.getCategoryId()== category.getNum()}">
			 ${category.getName()}
		 	</c:if>
			</c:forEach></td></tr>
	</table>
	<br>
	
	<input type="hidden" name="MenuName" value="<%= menu.getMenuName()%>"/>
	<input type="submit" value="업데이트"/>
	</div>
	</div> 
</form>
</body>
</html>