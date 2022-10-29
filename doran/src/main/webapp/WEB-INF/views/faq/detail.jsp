<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- JSTL 사용 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="faq" name="title" />
</jsp:include>

<h1></h1>
<table class="table table-border mt-50">
	<tbody>
		<tr>
			<th width="5%">번호</th>
			<td>${faqDto.faqNo}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${faqDto.faqTitle}</td>
		</tr>
		<tr height="300" valign="top"">
			<th>내용</th>
			<td>
				<!-- pre 태그 엔터, 띄어쓰기, 탭 키 그대로 표시 --> <pre>${faqDto.faqContent}</pre>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2" align="right"><a class="btn btn-neutral"
				href="list">목록으로</a></td>
		</tr>
	</tfoot>
</table>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>