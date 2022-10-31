<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>


<!-- 후원 내역 list -->	
	<div class=container-800>
		<table class="table table-hover table-slit">
			<thead>
				<tr align="center">
					<th>프로젝트 번호</th>
					<th>카테고리</th>
					<th>프로젝트</th>												
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="likes" items="${likesList}" >	
					<tr align="center">								
							<td><a href="/pj/detail?pjNo=${likes.pjNo}">${likes.pjNo}</a></td>							
							<td>${likes.pjCategory}</td>			
							<td>${likes.pjName}</td>				
								
						</tr>							
				</c:forEach>
			</tbody>
		</table>	

		</div>	






<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>