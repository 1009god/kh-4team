<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="1대1 문의" name="title"/>
</jsp:include>

<table border="1" width="800"">
	<thead>
	<tr>
		<td align = "right" colspan="6">
			<a href="write">글쓰기</a>
		</td>
		<tr>
			<th>번호</th>
			<th width="45%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>댓글수</th>
			<th>조회수</th>
		</tr>
	</thead>


<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>