<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "js/KitchenJs.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="css/KitchenCss.css"/>

</head>
<body>

	<div id="Order">

		<p align="center">
			<b> Orders Awaiting Confirmation</b>
		</p>
		<table>

			<tr class="headerTr">
				<th class='col1'>Table Number</th>
				<th class='col2'>Menu</th>
				<th class='col3'>Quantity</th>
				<th class='col4'>Button</th>
			</tr>
			<c:forEach var="confirm" items="${orderConfirm}">
				<tr>
					<td>${confirm.tableNumber}</td>
					<td>${confirm.menuName}</td>
					<td>${confirm.orderQuantity}</td>
					<td><input type="button" value="Cook" 
               onclick="window.location.href='ProjectServlet1?
               command=updateState&OrderNumber=${confirm.orderNumber}'; return false;"
               class="update-State"/></td>
			</c:forEach>
		</table>
	</div>

	<div id="Ready">

		<p align="center">
			<b> Orders Being Cooked</b>
		</p>
		<table>

			<tr class="headerTr">
				<th class='col1'>Table Number</th>
				<th class='col2'>Menu</th>
				<th class='col3'>Quantity</th>
				<th class='col4'>Button</th>
			</tr>
			<c:forEach var="cooking" items="${beingCooked}">
				<tr>
					<td>${cooking.tableNumber}</td>
					<td>${cooking.menuName}</td>
					<td>${cooking.orderQuantity}</td>
					<td><input type="button" value="Complete" 
               onclick="window.location.href='ProjectServlet1?command=updateState&OrderNumber=${cooking.orderNumber}'; return false;"
               class="update-State"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>



</body>
</html>