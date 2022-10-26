<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="회원 목록" name="title"/>
</jsp:include>

<div class="container-700 mt-40 mb-50">
	<!-- 제목 -->
	<div class="row">
		<h2>판매자 리스트</h2>
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
					<th>판매자번호</th>
					<th>등록 날짜</th>
					<th>입금은행</th>
					<th>입금 계좌</th>
					<th>판매자 승인여부</th>
					
					
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}">
				<tr>
					<td>
						 <a href="/admin/sellerdetail?sellerMemNo=${dto.sellerMemNo}">
							${dto.sellerMemNo}
							</a>
					</td>
					<td>
							${dto.sellerRegistryDate}
					</td>
					
					<td>${dto.sellerBank}</td>	
					
					<td>${dto.sellerAccount}</td>
					
					<td>${dto.sellerCheck}</td>
			     	</tr>
				</c:forEach>
			</tbody>
			
		
			
		</table>
	</div>


<!-- 페이지 네비게이터 -->
<h3> 

<c:choose>
	<c:when test="${not vo.isFirst()}">
		<a href="sellerlist?p=${vo.firstBlock()}&${vo.parameter()}">&laquo; </a>
	</c:when>
	<c:otherwise>
		<a href="#">&laquo;</a>
	</c:otherwise>
</c:choose>

<!-- 이전을 누르면 이전 구간의 마지막 페이지로 안내 -->

<c:choose>
	<c:when test="${vo.hasPrev()}">
		<a href="sellerlist?p=${vo.prevBlock()}&${vo.parameter()}">&lt;</a>
	</c:when>
	<c:otherwise>
		<a href="#">&lt;</a>
	</c:otherwise>
</c:choose>



<c:forEach var="i"  begin="${vo.startBlock()}" end="${vo.endBlock()}" step="1">
	<a href="memlist?p=${i}&${vo.parameter()}">${i}</a>
</c:forEach>

<!-- 다음을 누르면 다음 구간의 첫 페이지로 안내 -->

<c:choose>
	<c:when test="${vo.hasNext()}">
		<a href="sellerlist?p=${vo.nextBlock()}&${vo.parameter()}">&gt;</a>
	</c:when>
	<c:otherwise>
		<a href="#">&gt;</a>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${not vo.isLast()}">
		<a href="sellerlist?p=${vo.lastBlock()}&${vo.parameter()}">&raquo; </a>
	</c:when>
	<c:otherwise>
		<a href="#">&raquo;</a>
	</c:otherwise>
</c:choose>
</h3>
</div>
	
</div>
    
<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>