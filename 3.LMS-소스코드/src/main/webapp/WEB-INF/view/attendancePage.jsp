<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<script>
	
	window.onload = function(){
		
		for(let i = 1; i < 16; i ++){
			let weeks=document.querySelectorAll('.weeks'+i);
			
			let weeksCount = weeks.length;
			weeks[0].rowSpan = weeksCount;
			for(let weekNum = 1; weekNum<weeksCount;weekNum++){
				
				weeks[weekNum].remove();
			}
			
			for(let j = 1; j < 3; j++){
				
				let ordinals=document.querySelectorAll('.weeks'+i+'ordinal'+j);
				let ordinalsCount = ordinals.length;
				ordinals[0].rowSpan = ordinalsCount;
				
				for(let ordinalNum = 1; ordinalNum<ordinalsCount;ordinalNum++){
					
					ordinals[ordinalNum].remove();
				}
			}
			
		} 
		
	
	}
	
	
</script>
<style>
	
	table {
    width: 90%;
    margin: 20px auto;
    text-align: center;
    }
</style>
<body>
<jsp:include page="sidebar.jsp"/>
	<jsp:include page="header.jsp"/>
	<div id="mainBody">
	<table>
		<tr><th>주차</th><th>차시</th><th>강의 제목</th><th>출석</th></tr>
		
		 <c:forEach var="i" begin="1" end="15">
		
                  <c:forEach var="j" begin="1" end="2">
                  	
                  	
                        <c:choose>
                           <c:when
                              test="${lectureList.size() >= i && lectureList.get(i-1).size() >= j}">
                              <c:if test="${lectureList.get(i-1).get(j-1).size()==0}">
                               <tr> <td class="weeks${i}"> ${i}주차</td><td class="weeks${i}ordinal${j}">${j}차시</td><td>-</td><td>-</td></tr>
                          	 	</c:if>
                          	 
                              <c:forEach var="lecture"
                                 items="${lectureList.get(i-1).get(j-1)}" varStatus="z">
                                 <%-- <c:if test="${lecture!=null}"> --%>
                                 
             							<%-- <c:if test="${j==1}"> --%>
                                       <c:if test="${i<=weeks}">
                                       <tr> <td class="weeks${i}"> ${i}주차</td><td class="weeks${i}ordinal${j}">${j}차시</td><td>${lecture.lectureTitle}</td><td>${attendanceInfos.getOrDefault(lecture.lectureNumber,"결석")}</td></tr>
                                   		</c:if>
                                   		<c:if test="${i>weeks}">
                                   		<tr> <td class="weeks${i}"> ${i}주차</td><td class="weeks${i}ordinal${j}">${j}차시</td><td>-</td><td>-</td></tr>
                                   		</c:if>
                                 <%-- </c:if> --%>
                              </c:forEach>
                           </c:when>
                           <c:otherwise>
          					<tr> <td class="weeks${i}"> ${i}주차</td><td class="weeks${i}ordinal${j}">${j}차시</td><td>-</td><td>-</td></tr>
                           </c:otherwise>
                        </c:choose>
                  </c:forEach>

      </c:forEach>
	
	</table>
	</div>
</body>
</html>