<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page import = "java.util.Date"%>
<%@ page import = "java.text.DateFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page import="java.time.LocalDateTime" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link href="css/lecture.css" rel="stylesheet"/> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<title>Lecture contents</title>
</head>
<script>
function readContent(lecNum){
   
   let fm = document.querySelector('#lecContent');

    let obj1 = document.createElement('input');
   obj1.setAttribute('type', 'hidden');
   obj1.setAttribute('name', 'lectureNumber');
   obj1.setAttribute('value', lecNum);
   
    fm.appendChild(obj1);
    fm.submit();

}
function goToThatNum(bt_num){
   
   const btnName = document.getElementById('bt_week'+bt_num);
   if($('#contentInfo'+bt_num).css('display') == 'none'){
       $('#contentInfo'+bt_num).show();
       btnName.innerHTML = "▲ " + bt_num;
   }
   
   btnName.scrollIntoView();
    if(bt_num != 15) {
      window.scrollBy( 0, -230 );
   } 
}
function showHideAll(){
   const btnName = document.getElementById('bt_oc');
   let shape;

    if(btnName.innerHTML.includes('열기') ){
       $('.contentInfo').show();
       shape ="▲ ";
       btnName.innerHTML = "▲ 모든 메뉴 닫기";
   }else{
       $('.contentInfo').hide();
       btnName.innerHTML = "▼ 모든 메뉴 열기";
       shape ="▼ ";
   }
   
    for(let i = 1; i < 16; i++){
       document.getElementById('bt_week'+i).innerHTML = shape+i;
    }
}

function showHide(i){
   const btnName2 = document.getElementById('bt_week'+i);
    if($('#contentInfo'+i).css('display') == 'none'){
    $('#contentInfo'+i).show();
    btnName2.innerHTML = "▲ " + i;
   }else{
    $('#contentInfo'+i).hide();
    btnName2.innerHTML = "▼ " + i;
   }
}

window.onload = function(){
	if(${weeks}>0){
			console.log(${weeks});
		goToThatNum(${weeks});
	
	}
}

</script>



<style>

#lectureHeader {
    height: 150px;
    top: 70px;
    position: sticky;
    background: white;
    padding:20px;
}

#content{

  padding: 0 30px 0 60px;
} 

 .contentInfo {
     display: none;
} 


.time{
   float: right;
}

.weekname{
	font-size:20;
	font-weight: initial;
}

.ord{
   	margin-left: 60px;
   	font-weight: 500;
	font-size:17px;
}

.topButton {
    color: #212529;
    background: rgb(69, 199, 188); 
    border-color: #5bc2c2
}

.body{
   padding:20px;
   overflow: hidden;
}

li{

    margin-top:1px;
}

.contentInfo .addLectureContent{
   float: right;
   position: relative;
   top: -7px;
   font-size: 13px;
   margin-top: 3px;
   z-index : -1;
}


.lecDiv{
	border-bottom : solid 1px #cccccc;
   margin-left: 10px;
   margin-bottom: 5px;
   height : 30px;
}
.leftSpan{
	float:left;
	font-size: 15px;
}
.rightSpan{
	float:right;
	font-size: 15px;
}
</style>

<body>

   <jsp:include page="sidebar.jsp"/>
   <jsp:include page="header.jsp"/>
   <div id="mainBody">
   <div id="lectureBody">
   <div id="lectureHeader">
   <form:form action="${pageContext.request.contextPath}/courses/lecture/read/${classNumber}" id="lecContent" method="POST">
   </form:form>

   <!-- 해당 번호의 주차로 스크롤을 이동하고 내용을 표시하는 버튼 -->
   <c:forEach var="bt_num" begin="1" end="15">
      <button class="btn btn-info topButton" id="bt${bt_num}"
         onclick="javascript:goToThatNum(${bt_num})">${bt_num}</button>
   </c:forEach>
   <p>
   <br>
   <!-- 모든 항목의 내용을 펼쳐서 확인할 수 있는 버튼-->
   <button id="bt_oc" class="btn btn-secondary" onclick="showHideAll()">▼ 모든 메뉴 열기</button>
   <br>
   <hr>
   </div>   
      <div class="body">
      <security:authentication property="principal.username" var="username" />
      <c:forEach var="i" begin="1" end="15">
         <div id="content${i}">
            <button id="bt_week${i}" class="btn btn-secondary" onclick="javascript:showHide(${i})">▼ ${ i}</button>
               <b>${i}주차</b>
               <span class="time">
                  <c:set var="stDay" value="${startDay.plusDays(7*(i-1))}"></c:set>
                  ${stDay}
               </span>
   
   
               <hr>
               <div id="contentInfo${i}" class="contentInfo">
                  <c:forEach var="j" begin="1" end="2">
                     <form:form action="${pageContext.request.contextPath}/courses/lecture/addForm/${classNumber}" method="POST">
                        <%--    <c:if test="${username eq (checkProf)}">    --%>
                        <input type="hidden" name="lectureWeeks" value="${i}" />
                        <input type="hidden" name="lectureOrdinal" value="${j}" />
                        <span class="ord">${j}차시</span>
                        <button  class="btn btn-secondary addLectureContent" type="submit">추가하기</button><hr>
                        <%-- </c:if> --%>
                     </form:form>
                     <div id="content">
                        <c:choose>
                           <c:when
                              test="${lectureList.size() >= i && lectureList.get(i-1).size() >= j}">
                              <c:if test="${lectureList.get(i-1).get(j-1).size()==0}">
                           ${i} 주차  ${j} 차시 등록된 강의가 없습니다.
                              
                           </c:if>
                              <c:forEach var="lecture"
                                 items="${lectureList.get(i-1).get(j-1)}">
                                 <c:if test="${lecture!=null}">
                                 
                                 	
                                    <div class="lecDiv" onClick="javascript:readContent(${lecture.lectureNumber});">
                                       
                                       <span class="leftSpan">${lecture.lectureTitle}</span>
                                       <span class="rightSpan">${lecture.lecture_endDate}</span>
                                        
                                    </div>
                                 </c:if>
                              </c:forEach>
                           </c:when>
                           <c:otherwise>
                           ${i} 주차  ${j} 차시 등록된 강의가 없습니다. 
                           <c:if test="${username eq (checkProf)}">
                                 <input type="hidden" name="lectureWeeks" value="${i}" />
                                 <input type="hidden" name="lectureOrdinal" value="${j}" />
                                 <button id="addLectureContent" class="btn btn-secondary" type="submit">추가하기</button>
                              </c:if>
                           </c:otherwise>
                        </c:choose>
                     </div>
                     <hr>
                  </c:forEach>
               </div>
            </div>
      </c:forEach>
      <br></br>
      </div>
   </div>
   </div>
</body>
</html>