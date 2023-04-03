<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의 내용수정</title>
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
      input.setAttribute('name', 'lectureContent');
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


<body>   
   <div id="totalDiv">
      <jsp:include page="sidebar.jsp"/>
      <jsp:include page="header.jsp"/>
      <div id="mainBody">
         <div id="mainBody2">
            <h3>강의 수정</h3>
            <hr>
            <form:form action="${pageContext.request.contextPath}/courses/lecture/modify/${lecture.classNumber}" modelAttribute="lecture" method="POST">
               <form:hidden path="lectureWeeks"/>
               <form:hidden path="lectureOrdinal"/>
               <form:hidden path="lectureNumber"/>
      
                  <label for="lectureTitle" class="form-label"></label>
                  <form:input path="lectureTitle" class="form-control" list="datalistOptions" id="lectureTitle"/>
               <br>
               <form:textarea path="lectureContent" oninput="enter()"></form:textarea>
              
               <hr>
               출석인정기간: <input type="date" name="date1" value="${lecture.lecture_endDate}"/>
               <button type="submit" class="btn btn-info" onclick="formSubmit()">등록</button>
               <button type="button" class="btn btn-secondary" onclick="totalList()">취소</button>      
            </form:form> 
            <form:form id="backForm" action="${pageContext.request.contextPath}/courses/lecture/${classNumber}" method="POST">
                  
            </form:form>         
         </div>   
      </div>
   </div>   

</body>
</html>