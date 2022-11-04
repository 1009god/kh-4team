<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- JSTL 사용 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="FAQ" name="title" />
</jsp:include>
<style>
	.line{
		 line-height: 2rem;
	}
	.a{
		color : black;
	}
</style>

<div class="container-900 mt-40 mb-40">

<table border = "1" width = "100%" class="table table-border mt-50">
	<tbody>
		<tr>
			<th width="10%">번호</th>
			<td>${faqDto.faqNo}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${faqDto.faqTitle}</td>
		</tr>
		<tr height="200px" valign="top">
			<th>내용</th>
			<td class= "line">
				${faqDto.faqContent}
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
</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>