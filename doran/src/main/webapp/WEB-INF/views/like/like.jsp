<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="나의 좋아요" name="title"/>
</jsp:include>

<div class="container-1400" style="height:600px">
<div class="center">
<h1>좋아요 목록</h1>
</div>
<!-- 좋아요 내역 list -->	
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
							<td><a href="${pageContext.request.contextPath}/pj/detail?pjNo=${likes.pjNo}">${likes.pjNo}</a></td>							

							<td>${likes.pjCategory}</td>			
							<td>${likes.pjName}</td>				
								
						</tr>							
				</c:forEach>
			</tbody>
		</table>	

		</div>	
</div>





<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
