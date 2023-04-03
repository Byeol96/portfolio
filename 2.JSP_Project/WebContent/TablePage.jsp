<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>

<script src = "js/TablePageJs.js" charset="UTF-8"></script>
<script src = "js/TablePageJs2.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="css/TablePageCss.css"/>
</head>
<body>


	<div id="category">
		<div id="tableNumberDiv"><%=request.getParameter("tableNumber")%></div>
		<div class="categoryDiv">카테고리</div>
		<div class="categoryDiv clickDiv">단품</div>
		<div class="categoryDiv clickDiv">반반</div>
		<div class="categoryDiv clickDiv">사시미</div>
		<div class="categoryDiv clickDiv">튀김</div>
		<div class="categoryDiv clickDiv">면류</div>
	</div>
	<div id="content">
		<div class="contentDiv" id="content1">
			<div class="titleContent">단품</div>

			<c:forEach var="menu" items="${menuDatas.get(0)}">
				<div class="menuDiv" id="${menu.menuName}info">
					<img src="img/${menu.categoryId}/${menu.menuName}.jpg" /> <br>
					<br><div>${menu.menuName}</div>${menu.price}원</div>
			</c:forEach>


		</div>

		<div class="contentDiv" id="content2">
			<div class="titleContent">반반</div>
			<c:forEach var="menu" items="${menuDatas.get(1)}">
				<div class="menuDiv" id="${menu.menuName}info">
					<img src="img/${menu.categoryId}/${menu.menuName}.jpg" /><br>
					<br><div>${menu.menuName}</div>${menu.price}원</div>
			</c:forEach>

		</div>
		
		<div class="contentDiv" id="content3">
			<div class="titleContent">사시미</div>

			<c:forEach var="menu" items="${menuDatas.get(2)}">
				<div class="menuDiv" id="${menu.menuName}info">
					<img src="img/${menu.categoryId}/${menu.menuName}.jpg" /><br>
					<br><div>${menu.menuName}</div>${menu.price}원</div>
			</c:forEach>

		</div>
		<div class="contentDiv" id="content4">
			<div class="titleContent">튀김</div>
			<c:forEach var="menu" items="${menuDatas.get(3)}">
				<div class="menuDiv" id="${menu.menuName}info">
					<img src="img/${menu.categoryId}/${menu.menuName}.jpg" /><br>
					<br><div>${menu.menuName}</div>${menu.price}원</div>
			</c:forEach>
		</div>
		<div class="contentDiv" id="content5">
			<div class="titleContent">면류</div>
			<c:forEach var="menu" items="${menuDatas.get(4)}">
				<div class="menuDiv" id="${menu.menuName}info">
					<img src="img/${menu.categoryId}/${menu.menuName}.jpg" /><br>
					<br><div>${menu.menuName}</div>${menu.price}원</div>
			</c:forEach>

		</div>
	</div>
	<div style="width: 306px; height: 598px; border: solid 1px black; float: left; background-color : #202124">
	
		<div id="orderList">주문서</div>
		<div style =" background-color : white; width: 280px; height: 425px; margin: 0 auto;">
		<div id="orderContent"></div>
		<div id="totalCost">합계 : <div id="totalCostContent" style ="float:right;margin-right :15px"></div></div>
		<button id="OrderListButton">주문내역</button>
		<br>
		<button id = "OrderButton" >주문하기</button>
		</div>
	</div>
	
	
	
	


</body>
</html>