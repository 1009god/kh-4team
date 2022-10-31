<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>


<style>
		table, thead, tr, th, td{
			 border: 1px solid lightgray;
		}
        .table.table-border > thead > tr > th,
        .table.table-border > thead > tr > td,
        .table.table-border > tbody > tr > th,
        .table.table-border > tbody > tr > td,
        .table.table-border > tfoot > tr > th,
        .table.table-border > tfoot > tr > td {  
            border: 1px solid lightgray;
        }
        
/*         .tableA.table-border > thead > tr > th, */
/*         .tableA.table-border > thead > tr > td, */
/*         .tableA.table-border > tbody > tr > th, */
/*         .tableA.table-border > tbody > tr > td, */
/*         .tableA.table-border > tfoot > tr > th, */
/*         .tableA.table-border > tfoot > tr > td {   */
/*             border: 1px solid red; */
/*         } */
</style>

<div class="container-900 mt-40 mb-50">
	<table class= "table tableA" border="1" width="700" >
		<tbody>
			<tr>
				<th width = "25%">글번호</th>
				<td>${doranQDto.doranQNo}</td>
			</tr>
			<tr>
			
			<th width = "25%">문의유형</th>
			<c:choose>
				<c:when test="${empty doranQDto.doranQType}">
					<td>답변</td>
				</c:when>
				<c:otherwise>
					<td>[${doranQDto.doranQType}]</td>
				</c:otherwise>
			</c:choose>
			
			</tr>
			<tr>
				<th width = "25%">작성일</th>
				<td>${doranQDto.doranQWritetime}</td>
			</tr>
			<tr>
				<th width = "25%">제목</th>
				<td>${doranQDto.doranQTitle}</td>
			</tr>		
			<tr>
				<th height="200px" width = "25%">내용</th>
				<td>${doranQDto.doranQContent}</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan = "2" align = "right">
				<a href="list">목록으로</a>
			</tr>
		</tfoot>
	</table>

</div>