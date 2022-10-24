<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="프로젝트 목록" name="title"/>
</jsp:include>


<div class="container-700 mt-40 mb-50">
	<!-- 제목 -->
	<div class="row center">
		<h3>프로젝트 리스트</h3>
	</div>
	
	<!-- 목록 출력 -->
	<div class="row center">
		<table class="table table-hover table-slit">
			<thead>
				<tr>
					<th>판매자 번호 </th>
					<th>카테고리</th>
					<th>프로젝트명</th>
					<th>목표금액</th>
					<th>시작일</th>
					<th>승인상태</th>
					
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}">
				<tr>
			    	<td>${dto.pjNo}</td> 
					
					<td>${dto.pjCategory}</td>
					
					<td><a href="pjdetail?pjNo=${dto.pjNo}">
							${dto.pjName}
						</a></td>	
					
					<td>${dto.pjTargetMoney}</td>

					<td>${dto.pjFundingStartDate}</td>
					
					<td><%-- ${dto.JudgePjState} --%>심사중/승인/거절(사유)</td> 
					
					
			     	</tr>
				</c:forEach>
			</tbody>
			
			
			
		</table>
	</div>

<nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
    
<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>