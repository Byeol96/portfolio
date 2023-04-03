<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ page import="com.web.jdbc.PaymentData" %>
	<%@ page import="java.util.*" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script> 
	document.addEventListener("DOMContentLoaded", () => { 

		//toISOString(); - 표준시에 따라 ISO 8601 형식으로 주어진 날짜를 나타내는 문자열
		var today = new Date().toISOString().split("T")[0];
		let $dateInput = document.querySelectorAll(".dateInput");
		
		
		if(${startDate == null}){
		for(let i =0; i<$dateInput.length; i++){
			
			$dateInput[i].max = today;
			$dateInput[i].value = today;
		}
		}else{
			
			$dateInput[0].max = '${endDate}';
			$dateInput[0].value = '${startDate}';
			
			$dateInput[1].max = today;
			$dateInput[1].min = '${startDate}';
			$dateInput[1].value = '${endDate}';
		}
		
		
		const $startDate = document.querySelector('#startDate');
		const $endDate = document.querySelector('#endDate');
		
		const changeStart = () =>{	
			
			$endDate.min = event.target.value;
			
			if(new Date($endDate.value) < new Date (event.target.value)){
				
				$endDate.value = event.target.value;
			}
		} 
		
		const changeEnd = () =>{
			
			$startDate.max = event.target.value;
			
			if(new Date($startDate.value) > new Date (event.target.value)){
				
				$startDate.value = event.target.value;
			}
		} 
		
		$startDate.addEventListener('change',changeStart);
		$endDate.addEventListener('change',changeEnd);

		
		
		const $nonData = document.querySelector('#nonData');
		if(!$nonData){
		const $costDiv = document.querySelectorAll('.orderMenuTotalCost');
		let totalCost = 0;
		for(let i = 0 ; i<$costDiv.length ; i++){
			
			totalCost = totalCost + Number($costDiv[i].textContent);
		}
	
		const $totalCost = document.querySelector('#totalCost');
		$totalCost.textContent = totalCost+' 원';
		
		}else{
			const $button = document.querySelector('button');
			$button.addEventListener('click',()=>{
			window.close();
		})
		}
		

		
	})
</script>
<link type="text/css" rel="stylesheet" href="css/PopUp_CashUpCss.css"/>
</head>
<body>

	<div id="totalDiv">
		<%
		if (((ArrayList<PaymentData>) request.getAttribute("cashUpDatas")).size() > 0) {
		%>
		<div id="orderList">

			<div id="dateSelect">
				<form action="ProjectServlet1">
					<input type="hidden" name="command" value="PopUp_BetweenDateCashUp"/>
					<input type="date" class="dateInput" id="startDate"
						name="startDate" /> &nbsp&nbsp ~ &nbsp&nbsp <input type="date"
						class="dateInput" id="endDate" name="endDate" />
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="기간 선택" />
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 합계 : <span
						id="totalCost"></span>
				</form>
			</div>

			<table>

				<tr>
					<th>-</th>
					<th>메뉴명</th>
					<th>단가</th>
					<th>수량</th>
					<th>금액</th>
				</tr>
				<c:forEach var="order" items="${cashUpDatas}" varStatus="status">
					<tr class="dataTr" id="row${status.index}">
						<td class="orderNo">${status.index+1}</td>
						<td class="orderMenuName">${order.menu_name}</td>
						<td class="orderMenuCost">${order.menu_price}</td>
						<td class="orderMenuQuantity">${order.menu_quantity }</td>
						<td class="orderMenuTotalCost">${order.menu_price*order.menu_quantity}</td>
					</tr>
				</c:forEach>
			</table>

		</div>

		<%
		} else {
		%>
		<div id="nonData">
		<form action="ProjectServlet1">
					<input type="hidden" name="command" value="PopUp_BetweenDateCashUp"/>
					<input type="date" class="dateInput" id="startDate"
						name="startDate" /> &nbsp&nbsp ~ &nbsp&nbsp <input type="date"
						class="dateInput" id="endDate" name="endDate" />
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="기간 선택" />
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 합계 : <span
						id="totalCost"></span>
				</form><br>
			거래내역이 존재하지 않습니다.
			<button>돌아가기</button>
		</div>
		<%} %>
	</div>
</body>
</html>