<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	  
   <div id="wrapper">
      <div id="header">
         <h2>Customer Management</h2>
      </div>
   </div>
   <div id="container">
      <h3>Save Customer</h3>

	
      <form:form action="${pageContext.request.contextPath}/courses/notice/write/${classNumber}" modelAttribute="specificNotice" method="POST"> 
      	<c:if test="${specificNotice.noticeWriter!=null}">
      		<input type="hidden" name="modify" value="true"/>
      	</c:if>
      	<form:input path="noticeNumber"/>
      	<form:input path="classNumber"/>         
     	<form:input path="noticeDate"/>  
     	<form:input path="noticeViews"/>
     	<form:input path="noticeWriter"/>   
         <table>	
            <tbody>
               <tr>
                  <td><label>Post Title:</label></td>
                  <td><form:input path="noticeTitle" /></td>
               </tr>
            
               <tr>
                  <td><label>Post Content:</label></td>
                  <td><form:input path="noticeContent" /></td>
               </tr>
               <tr>
                  <td><label></label></td>
                  <td><input type="submit" value="글쓰기"/></td>
               </tr>
            </tbody>
         </table>
      </form:form>
   
      <div style="clear; both;"></div>
      <p>
         <form:form action="${pageContext.request.contextPath}/courses/notice/${classNumber}" method="POST">
         <button type="submit">목록</button></form:form>
      </p>
   </div>
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>게시판 내용수정</title>
<meta charset="UTF-8">
<script>
   function totalList(){
       
       const backForm = document.querySelector('#backForm');
       backForm.submit();
   }
   function enter(){
      
      if (event.keyCode === 13){
         const textarea = document.querySelector('textarea');
         textarea.value +="\n";
         
      }
      
   }
         
    function formSubmit(){
      const form = document.querySelector('form');
      const textarea = document.querySelector('textarea');
      
      let input = document.createElement('input');
      input.setAttribute('type', 'hidden');
      input.setAttribute('name', 'noticeContent');
      input.setAttribute('value', textarea.value.replace("\n","<br>")); 
      
      form.appendChild(input);
      form.submit();
       
    }
    
   
</script>
<style>
#total{
   width : 100%;
   height : 100%;
}
#mainBody2{
   width: 90%;
   margin: 30px auto 0 auto;
   height: 65%;
}

button{
   float: right;
}

#lectureContent{
   height: 350px;
}

.btn-secondary{
   margin-right: 10px;
}
textarea{
   width : 100%;
   height : 370px;
}

body::-webkit-scrollbar {
  display: none;
}
h3{

	    margin-left: 5px;	
}
</style>
</head>
<body>








   <!-- wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww -->
   <!-- wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww -->
   	
 <%--      <form:form action="${pageContext.request.contextPath}/courses/notice/write/${classNumber}" modelAttribute="specificNotice" method="POST"> 
      	<c:if test="${specificNotice.noticeWriter!=null}">
      		<input type="hidden" name="modify" value="true"/>
      	</c:if>
      	
      	<form:hidden path="noticeNumber"/>
      	<form:hidden path="classNumber"/>         
     	<form:hidden path="noticeDate"/>  
     	<form:hidden path="noticeViews"/>
     	<form:hidden path="noticeWriter"/>   
         <table>	
            <tbody>
               <tr>
                  <td><label>Post Title:</label></td>
                  <td><form:input path="noticeTitle" /></td>
               </tr>
            
               <tr>
                  <td><label>Post Content:</label></td>
                  <td><form:input path="noticeContent" /></td>
               </tr>
               <tr>
                  <td><label></label></td>
                  <td><input type="submit" value="글쓰기" class="save"/></td>
               </tr>
            </tbody>
         </table>
      </form:form>
   
      <div style="clear; both;"></div>
      <p>
         <form:form action="${pageContext.request.contextPath}/courses/notice/${classNumber}" method="POST">
         <button type="submit">목록</button></form:form>
      </p>
   </div> --%>
   
   
   
   
   
   <!-- wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww -->
   <!-- wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww -->
   
   
   
   
   <div id="totalDiv">
      <jsp:include page="sidebar.jsp"/>
      <jsp:include page="header.jsp"/>
      <div id="mainBody">
         <div id="mainBody2">
          	<c:if test="${specificNotice.noticeWriter eq null }">
         		<h3>게시글 작성</h3>
         	</c:if>
         	<c:if test="${specificNotice.noticeWriter != null }">
         		<h3>게시글 수정</h3>
         	</c:if>         	
            <hr>
		      <form:form action="${pageContext.request.contextPath}/courses/notice/write/${classNumber}" modelAttribute="specificNotice" method="POST"> 
		      	<c:if test="${specificNotice.noticeWriter!=null}">
		      		<input type="hidden" name="modify" value="true"/>
		      	</c:if>
		      	<form:hidden path="noticeNumber"/>
		      	<form:hidden path="classNumber"/>         
		     	<form:hidden path="noticeDate"/>  
		     	<form:hidden path="noticeViews"/>
		     	<form:hidden path="noticeWriter"/>  
      <!-- 제목 아직 -->
                  <label for="noticeTitle" class="form-label"></label>
                  <form:input path="noticeTitle" class="form-control" list="datalistOptions" id="noticeTitle"/>
               <br>
      <!-- 내용 아직 -->
               <form:textarea path="noticeContent" oninput="enter()"></form:textarea>
              
               <hr>
	  <!-- 버튼 아직 -->
               <button type="submit" class="btn btn-info" onclick="formSubmit()">등록</button>
               <button type="button" class="btn btn-secondary" onclick="totalList()">취소</button>      
            </form:form> 
            <form:form id="backForm" action="${pageContext.request.contextPath}/courses/notice/${classNumber}" method="POST">
                  
            </form:form>         
         </div>   
      </div>
   </div>      
   
   
   
</body>
</html>