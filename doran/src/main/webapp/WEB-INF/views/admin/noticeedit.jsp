<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="faq" name="title"/>
</jsp:include>

<form action="noticeedit" method="post">
<!-- input[type=hidden] 은 form 안에 위치해야 한다 -->
<!-- <div class="container-500 mt-40"> -->
	<div class="row left">
	<h1>게시글 보기</h1>
	</div>
<input type="hidden" name="noticeNo" value="${noticeDto.noticeNo}">
<table class="table table-border mt-50">
	<tbody>
	<tr>
			<th width="20%">제목</th>
			<td>
				<input class="input" type="text" name="noticeTitle" required value="${noticeDto.noticeTitle}">
			</td>
		</tr>
		
		
		<tr class="input" height="200" valign="top">
			<th valign="top">내용</th>
			<td>
				<textarea name="noticeContent" rows="20" cols="80" required>${noticeDto.noticeContent}</textarea>
			</td>
		</tr>
		
		<tr>
    				<th>첨부파일</th>
    				<td>
    					<input type="file" name="files" multiple></input>
    					
    				</td>
    			</tr>
	</tbody>
	<tfoot>
		<tr>
			<td align="right" colspan="2">
				<a class="btn btn-neutral" href = "noticelist">목록으로</a>
				<button class="btn btn-neutral" type="submit">수정하기</button>
			</td>
		</tr>
	</tfoot>
</table>

</div>
</form>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>