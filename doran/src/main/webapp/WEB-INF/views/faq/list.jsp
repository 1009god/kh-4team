<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 사용 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="FAQ" name="title"/>
</jsp:include>

<!-- <div class="row center">
	<form action="list" method="get">
		<select class="input" name="type" required>
			<option>회원정보</option>
			<option>운영정책</option>
			<option>이용문의</option>
			<option>기타</option>
		</select>
		<input class="input"  name="keyword" required>
		<button class="btn btn-positive">검색</button>
	</form>
	</div> -->
	
	
	<!-- 목록 출력 -->
	<div class="container-900 mt-40 mb-40">
		<h1 align="center">FAQ</h1>
		<table class="table table-slit">
			<thead>
				<tr align="center">
					<th>번호</th>
					<th width="45%">제목</th>
					<th>유형</th>
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach var="faqDto" items="${list}">
					<tr>
						<td>${faqDto.faqNo}</td>
						<td align="left">
							<a href="detail?faqNo=${faqDto.faqNo}">
								${faqDto.faqTitle}
							</a>
						</td>
					<td align="center">${faqDto.faqType}</td>
				</tr>
				</c:forEach>
			</tbody>
	</table>
	</div>
	


<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>