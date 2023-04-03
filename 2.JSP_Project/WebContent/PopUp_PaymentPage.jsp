<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.web.jdbc.PaymentData" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src = "js/PopUp_PaymentPageJs.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="css/PopUp_PaymentPageCss.css"/>
</head>
<body>

	<div id="totalDiv">
	<% if (((ArrayList<PaymentData>)request.getAttribute("Orders")).size()>0){ %>
		<div id="orderList">
		
			<div id="tableNumber">${Orders.get(0).table_no}번테이블</div>
			<br>
			<table>
				<thead class="thead-dark">
					<tr>
						<th>-</th>
						<th>메뉴명</th>
						<th>단가</th>
						<th>수량</th>
						<th>금액</th>
					</tr>
				</thead>
				<c:forEach var="order" items="${Orders}" varStatus="status">
					<tr>
						<td class="orderNo">${status.index}</td>
						<td id="orderMenuName">${order.menu_name}</td>
						<td id="orderMenuCost">${order.menu_price}</td>
						<td id="orderMenuQuantity">${order.menu_quantity }</td>
						<td id="orderMenuTotalCost">${order.menu_price*order.menu_quantity}</td>
					</tr>
				</c:forEach>
			</table>

		</div>
		<div id="payMent">
			합계 : <span id="totalCost"></span> <br> <br>
			<form action="ProjectServlet1">
				<input type="hidden" name="command" value="Payment" /> 
				<input type="hidden" name="tableNumber" value="${Orders.get(0).table_no}" />
				<input type="submit" value="결제하기" />
			</form>
		</div>
		<%}else{ %> <div id="nonData"> 계산 가능한 주문 내역이 존재하지 않습니다  <button> 돌아가기 </button> </div>  <%} %>
	</div>
</body>
</html>