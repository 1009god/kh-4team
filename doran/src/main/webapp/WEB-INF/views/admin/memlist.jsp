<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="회원 목록" name="title"/>
</jsp:include>

<div class="container-700 mt-40 mb-50">
	<!-- 제목 -->
	<div class="row">
		<h2>회원 리스트</h2>
	</div>
	
	<!-- 검색창 -->
<!-- 	<form action="list" method="get">
	<div class="row1">
		항목
		<select class="input" name="type" required>
		
			<option value="mem_email">이메일</option>
			<option value="mem_nick">닉네임</option>
			
		</select>
		
		키워드
		<input class="input" type="search" name="keyword" placeholder="검색어" required>
		
		<button class="btn btn-positive">검색</button>
	</div>
	</form> -->
	
	<!-- 목록 출력 -->
	<div class="row1">
		<table class="table table-hover table-slit">
			<thead>
				<tr>
					<th>번호</th>
					<th>닉네임</th>
					<th>이메일</th>
					<th>회원 분류</th>
					
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.memNo}</td>
					
					<td>
						<a href="/admin/detail?memNo=${dto.memNo}">
							${dto.memNick}
						</a>
					</td>
					
					<td>${dto.memEmail}</td>	
					
					<td>회원/판매자</td>
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
</div>
	
</div>
    
<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>