<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="com.web.jdbc.Order"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<!-- <script src = "js/CouterJs.js" charset="UTF-8"></script> -->
<script>

$(document).ready(function(){
		let $DataTd = document.querySelectorAll(".DataTd");
		let tableNumbers = new Array();
		for(let i =0 ; i <$DataTd.length ; i++){
			tableNumbers.push(Number($DataTd[i].childNodes[1].textContent.replace('\n','').trim()));	
		}
		console.log(tableNumbers);
		
		$('.DataTd').on('click',function(event){
			
			if(IngFunction) return;
			
			console.log(event.target);
			let checkTd = event.target;
			let tdIndex = checkTd.cellIndex;
			let trIndex = checkTd.parentElement.rowIndex; 
			while(![0,1,2,3].includes(trIndex)){
				checkTd = checkTd.parentElement;
				console.log(checkTd);
				tdIndex = checkTd.cellIndex;
				trIndex = checkTd.parentElement.rowIndex;
			}
			
			let tableNumberDiv = checkTd.childNodes[1].textContent
			let tableNumber = tableNumberDiv.substr(tableNumberDiv.lastIndexOf('\t')+1);
			console.log(tableNumber);
			window.open('ProjectServlet1?command=PayMentPage&tableNumber='+tableNumber,'popName',
           'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
			
			
		})
		let addClick = 0;
		let IngFunction = '';
		$('#addTable').click(function(){
			
			if(IngFunction !== 'addTable' && IngFunction) return;
			if(++addClick%2 == 0) {
				
				IngFunction = '';
				$('.NonDataTd').off('click');
				$('.DataTd').css('opacity',1);
				$('.NonDataTd').css('opacity',0);
				$('.NonDataTd').off('mouseenter');
				$('.NonDataTd').off('mouseleave');
				return;
			}
			
			
			IngFunction = 'addTable';
			$('.DataTd').css('opacity',0.5);
			$('.NonDataTd').css('opacity',1);
			
			$('.NonDataTd').mouseenter(function(target){
				
				$(this).css('background-image','url(img/check.png)').css('background-size','107px 140px');
			})
			$('.NonDataTd').mouseleave(function(target){
				
				$(this).css('background-image','');
			})
			
			$('.NonDataTd').on('click',function(event){
				
				let tableNumber = prompt("테이블 번호를 입력해 주세요");
				let checkTd = event.target;
				
				let tdIndex = checkTd.cellIndex;
				let trIndex = checkTd.parentElement.rowIndex;
				window.location.href='ProjectServlet1?command=TableAdd&tableNum='+tableNumber+'&tdIndex='+tdIndex+'&trIndex='+trIndex; 
			})

		})
		
		let deleteClick = 0;
		
		
		$('#deleteTable').click(function(){
			
			if(IngFunction !== 'deleteTable' && IngFunction) return;
			if(++deleteClick%2 == 0) {
				
				IngFunction = '';
				$('.DataTd').off('click');
				$('.DataTd').off('mouseenter');
				$('.DataTd').off('mouseleave');
				return;
			}
			
			IngFunction = 'deleteTable';
			$('.DataTd').mouseenter(function(target){
				
				$(this).css('background-image','url(img/xx.png)').css('background-size','107px 140px');
			})
			
			$('.DataTd').mouseleave(function(target){
				
				$(this).css('background-image','');
			})
			
			$('.DataTd').on('click',function(event){
				
			
			let checkTd = event.target;
			let tdIndex = checkTd.cellIndex;
			let trIndex = checkTd.parentElement.rowIndex; 
			while(!tdIndex){
				checkTd = checkTd.parentElement;
				tdIndex = checkTd.cellIndex;
				trIndex = checkTd.parentElement.rowIndex;
			}
			window.location.href='ProjectServlet1?command=TableDelete&tdIndex='+tdIndex+'&trIndex='+trIndex;
			})
			
			
		})
		
		let changeClick = 0;
		
		$('#moveTable').click(function(){
			
			if(IngFunction !== 'moveTable' && IngFunction) return;
			if(++changeClick%2 == 0) {
				
				IngFunction = '';
				$('.DataTd').off('click');
				$('.NonDataTd').off('click');
				$('.DataTd').off('mouseenter');
				$('.DataTd').off('mouseleave');
				return;
			}
			
			$('.DataTd').mouseenter(function(target){
				
				$(this).css('background-image','url(img/check.png)').css('background-size','107px 140px');
			})
			
			$('.DataTd').mouseleave(function(target){
				
				$(this).css('background-image','');
			})
			
			IngFunction = 'moveTable';
			let DataTdClick = 0;
			
			let bcheckTd;
			let btdIndex;
			let btrIndex;
			
			
			$('.DataTd').on('click',function(event){
				
				/* if(++DataTdClick %2 === 0){
					$(this).css('background-image','');
					$('.NonDataTd').off('click');
					return
				} */
				$('.DataTd').off('mouseenter');
				$('.DataTd').off('mouseleave');
				
				$('.DataTd').css('opacity',0.5);
				$('.NonDataTd').css('opacity',1);
				
				bcheckTd = event.target;
				btdIndex = bcheckTd.cellIndex;
				btrIndex = bcheckTd.parentElement.rowIndex;
				while(!btdIndex){	
					bcheckTd = bcheckTd.parentElement;
					btdIndex = bcheckTd.cellIndex;
					btrIndex = bcheckTd.parentElement.rowIndex;
				}
				
				$('.NonDataTd').mouseenter(function(target){
					
					$(this).css('background-image','url(img/check.png)').css('background-size','107px 140px');
				})
				$('.NonDataTd').mouseleave(function(target){
					
					$(this).css('background-image','');
				})
			
				$('.NonDataTd').on('click',function(event){
				
					let checkTd = event.target;		
					let tdIndex = checkTd.cellIndex;
					let trIndex = checkTd.parentElement.rowIndex;
					
					window.location.href=
						'ProjectServlet1?command=TableMove&tdIndex='+tdIndex+'&trIndex='+trIndex+
							'&btdIndex='+btdIndex+'&btrIndex='+btrIndex; 
				})
			
			})
	
		})
		
		let changeTable = 0;
		$('#changeTable').click(function(){
			
			if(IngFunction !== 'changeTable' && IngFunction) return;
			if(++changeTable%2 == 0) {
				
				IngFunction = '';
				$('.DataTd').off('click');
				$('.DataTd').off('mouseenter');
				$('.DataTd').off('mouseleave');
				return;
			}
			
			IngFunction = 'changeTable';
			$('.DataTd').mouseenter(function(target){
				
				$(this).css('background-image','url(img/check.png)').css('background-size','107px 140px');
			})
			
			$('.DataTd').mouseleave(function(target){
				
				$(this).css('background-image','');
			})
			
			$('.DataTd').click(function(event){
				let tableNum	
				while(true){
					tableNum = Number(prompt('바꾸실 테이블 번호를 입력하세요'));
					
					if(!Number.isInteger(tableNum)){
						alert('정수가 아닙니다. 다시입력해주세요');
						continue;
					}
					if(tableNumbers.includes(tableNum)) {
						alert('이미 존재하는 테이블 번호입니다 다시입력해주세요');
						continue;	
					}
					if(tableNum>32){
						alert('테이블 번호는 32를 넘을 수 없습니다. 다시입력해주세요');
						continue;
					}
					break;
				}
				if(tableNum == 0){
					IngFunction = '';
					$('.DataTd').off('click');
					$('.DataTd').off('mouseenter');
					$('.DataTd').off('mouseleave');
					$(this).css('background-image','');
					++changeTable;
					return;
				}
				let checkTd = event.target;
				let tdIndex = checkTd.cellIndex;
				let trIndex = checkTd.parentElement.rowIndex;
				if(!tdIndex){
					checkTd = checkTd.parentElement;
					tdIndex = checkTd.cellIndex;
					trIndex = checkTd.parentElement.rowIndex;
				}
				console.log('tdIndex='+tdIndex+'&trIndex='+trIndex+'&tableNum='+tableNum);
				// window.location.href='ProjectServlet1?command=TableChange&tdIndex='+tdIndex+'&trIndex='+trIndex+'&tableNum='+tableNum;
			})
			console.log('?');
		})
		
		$('#addMenu').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_AddMenu_Move','popName',
           'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
		
		$('#deleteMenu').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_DeleteMenu_Move','popName',
           'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
		$('#updateMenu').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_UpdateMenu_Move','popName',
           'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
		$('#soldOutMenu').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_SoldOutMenu_Move','popName',
           'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
		$('#cashUp').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_CashUp','popName',
           'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
	})

</script>
<link type="text/css" rel="stylesheet" href="css/CounterCss.css"/>

</head>
<body>
	<div id="tableDiv">
		<table>

			<%
			int[][] location = (int[][]) request.getAttribute("Location");
			ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("Order");
			
			for (int i = 0; i < 4; i++) {
			%>
			<tr>
				<%
				for (int j = 0; j < 8; j++) {
				%>

				<%
					int number = location[i][j];
					if (number != 0) { 
					%>
				<td class="DataTd">
					
					<div class="tableNumberDiv">
						<%=number%></div>
					<hr>
					<div class="contentDiv">
					<div class="tableOrderDiv">
						
						<%
						for (int m = 0; m < orders.size(); m++) {
							if (orders.get(m).getTableNumber() == number) {
						%><%=orders.get(m).getMenuName()%> <br>
						<%  }
						} %>
					</div>
					<div class="tableOrderQuantityDiv">
						<%
						
						for (int k = 0; k < orders.size(); k++) {
							if (orders.get(k).getTableNumber() == number) {
						%><%=orders.get(k).getOrderQuantity()%>
						<br>
						<%  }
						}
						%></div> </div>
					<%}else { %>
				
				            <td class="NonDataTd"></td>
				    <%} %>

				<%}%>
			</tr>
			<%}%>


		</table>
	
	</div>
	<div id="functionDiv">
	<div id="functions">
		<div class="function" id="addMenu">메뉴 추가</div>
		<!-- <div class="function" id="soldOutMenu">메뉴 품절</div> -->
		<div class="function" id="updateMenu">메뉴 수정</div>
		<div class="function" id="deleteMenu">메뉴 삭제</div>
		<div class="function" id="cashUp">매출 확인</div>
		<div class="function" id ="addTable">테이블 추가</div>
		<div class="function" id ="deleteTable">테이블 삭제</div>
		<div class="function" id ="changeTable">테이블 변경</div>
		<div class="function" id ="moveTable">테이블 이동</div>
		</div>
	</div>


</body>
</html>