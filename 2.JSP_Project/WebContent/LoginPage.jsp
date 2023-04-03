<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Select Login Location</title>
<link type="text/css" rel="stylesheet" href="css/LoginCss.css"/>
</head>
<body >

	<div id='tableDiv'><br><br><br><br></div> 
	<div id = 'nonTableDiv'><br><br><img src="img/Logo.jpg"/></div>
	
	
	<script>
	document.addEventListener("DOMContentLoaded", () => {
	const movePage = (event) => {
		let moveLocation;
		let targetString = event.target.textContent;
		if (targetString.includes('Table')) {
			const idx = targetString.indexOf('.') + 1;
			moveLocation = 'ProjectServlet1?command=movePage&location=Table' + targetString.substr(idx);
		} else {
			moveLocation = 'ProjectServlet1?command='+targetString+'Data';
		}
		window.location.href = moveLocation;
	}
	const table = '${table}';
	const tableList = table.split(',');
	const $table = document.createElement('table');
	const $table2 = document.createElement('table');
	$table2.id = 'table2';
	const rows = Math.ceil(tableList.length / 4.0);
	const columns=[];
	
	for(let i = 0; i <rows; i ++){
		columns.push(4);
	}
	if((tableList.length / 4.0) > rows){
		columns.push(tableList.length-4*rows);
	}
	for (let i = 0; i < rows; i++) {
		const $tr = document.createElement('tr');
		for (let j = 0; j < columns[i]; j++) {
			if(!tableList[i * 4 + j]) break;
			
			const $td = document.createElement('td');
			const $div = document.createElement('div');
			const $$div = document.createElement('div');
			$$div.innerHTML= '<b>Table<br>'+'No.'+tableList[i * 4 + j]+'</b>';
			if(i%2==0){
				$div.className = 'divTd LeftImg';
				$$div.className='innerRightDiv';
			}else {
				$div.className = 'divTd RightImg';
				$$div.className='innerLeftDiv';
			}
			
			$div.append($$div);
			$div.addEventListener('click', movePage);
			$td.append($div);
			$tr.append($td);
		}
		$table.append($tr);
	}
	const $tableDiv = document.querySelector('#tableDiv');
	$tableDiv.append($table);
	const $nonTableDiv = document.querySelector('#nonTableDiv');
	const LoginName = ['Counter', 'Kitchen'];
	for (let i = 0; i < 2; i++) {

		const $tr = document.createElement('tr');
		const $td = document.createElement('td');
		const $div = document.createElement('div');
		const $$div = document.createElement('div');
		$$div.innerHTML= '<b>'+LoginName[i];+'</b>';
		if(i%2==0){
			$div.className = 'divTd LeftImg2';
			$$div.className='innerRightDiv2';
		}else {
			$div.className = 'divTd RightImg2';
			$$div.className='innerLeftDiv2';
		}
		$div.addEventListener('click', movePage);
		$div.append($$div);
		$td.append($div);
		$tr.append($td);
		$table2.append($tr);
	}
	
	$nonTableDiv.append($table2);})
	</script>

	

</body>
</html>