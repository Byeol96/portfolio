<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link type="text/css" rel="stylesheet" href="css/orderListConfirmPageCss.css"/>

</head>
<body>
	<script>
		if ('${move}' == "yes")
			window.close();
	</script>
	<form action="ProjectServlet1">
		<input type="hidden" name="command" value="OrderDelete" />
		<div id="tableDiv">
			<table>
				<tr>
					<th>메뉴명</th>
					<th>양</th>
					<th>주문 시간</th>
					<th>주문 상태</th>
					<th>주문취소</th>
				</tr>
				<c:forEach var="order" items='<%=request.getAttribute("OrderList")%>'>
					<tr>
						<td>${order.menuName}</td>
						<td>${order.orderQuantity}</td>
						<td>${order.orderTime}</td>
						<td><c:choose>
								<c:when test="${order.orderState == 1}"> 주문 확인 전 </c:when>
								<c:when test="${order.orderState == 2}"> 음식 조리중</c:when>
								<c:otherwise> 음식 서빙중</c:otherwise>
							</c:choose></td>
						<td class="cancel"><c:choose>
								<c:when test="${order.orderState == 1}">
									<input type="checkbox" name="deleteMenus"
										value='${order.menuName},${order.orderTime}' />
								</c:when>
								<c:otherwise>취소 불가</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>

			</table>
		</div>
		<div id="submitDiv">
			<input type="hidden" name="tableNumber" value="${TableNumber}"/>
			<input type="submit" value="주문취소" />
		</div>
	</form>
</body>
</html>