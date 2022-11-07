<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="공지사항" name="title"/>
</jsp:include>

<div class="container-800 mt-40 mb-40">
	<div class="row center">
<table border = "1" width = "500" class="table table-border">
	<tbody>
		<tr>
			<th width = "25%">제목</th>
			<td>${noticeDto.noticeTitle}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>
				<fmt:formatDate value="${noticeDto.noticeWriteTime}" pattern="y년 M월 d일 E요일 a h시 m분 s초"/>
			</td>
		</tr>
		<tr height="200" valign="top"">
			<th>내용</th>
			<td>
				<!-- pre 태그 엔터, 띄어쓰기, 탭 키 그대로 표시 -->
				<c:forEach var="filesDto" items="${filesList}" >		
					<img width="auto" height="auto" src="${pageContext.request.contextPath}/files/download/${filesDto.filesNo}" >
				</c:forEach>
				
				<pre>${noticeDto.noticeContent}</pre>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2" align="right">
				<a class="btn btn-neutral" href="list">목록으로</a>
			</td>
		</tr>
	</tfoot>
</table>
</div>
</div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>