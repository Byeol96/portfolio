<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ErrorPage</title>
<script>
	document.addEventListener('click',()=>{
		window.location.href = 'ProjectServlet1; return false;';
	})
</script>
<style>
	img{
		width : 80%;
		height : 80%;
		margin : 0 auto 0 auto;
	}
	div{
		text-align : center;
		font-size : 25px;
	}
</style>
</head>
<body>
	<div>
	<img src="img/ErrorPage.png"/><br>
	 ȭ���� Ŭ���Ͻø� ù ȭ������ ���ư��ϴ�.</div>
</body>
</html>