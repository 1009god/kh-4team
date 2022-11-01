<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- JSTL 사용 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="faq" name="title" />
</jsp:include>

<h1></h1>
<table class="table table-border mt-50">
	<tbody>
		<tr>
			<th width="10%">번호</th>
			<td>${faqDto.faqNo}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${faqDto.faqTitle}</td>
		</tr>
		<tr height="200" valign="top">
			<th>내용</th>
			<td>
				<!-- pre 태그 엔터, 띄어쓰기, 탭 키 그대로 표시 --> <pre>${faqDto.faqContent}</pre>
			</td>
		</tr>
	</tbody>
</table>
		<div class="row right">
			<a class="btn btn-neutral"
				href="faqlist">목록으로</a>
				
				<a class="btn btn-neutral"
				href="faqedit?faqNo=${faqDto.faqNo}">수정하기</a>
				
				<a class="btn btn-neutral"
				 href="faqdelete?faqNo=${faqDto.faqNo}">삭제하기</a>
		</div>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>