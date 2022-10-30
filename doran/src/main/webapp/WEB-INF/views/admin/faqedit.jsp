<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="FAQ 수정" name="title" />
</jsp:include>

<form action="faqedit" method="post">

	<div class="row left">
		<h1>FAQ 수정</h1>
	</div>

	<input type="hidden" name="faqNo" value="${faqDto.faqNo}">
	<table class="table table-border mt-50">
		<tbody>
			<tr>
				<th><select class="input" name="faqType" required
					value="${faqDto.faqType}">
						<option value="">선택</option>
						<option>회원정보</option>
						<option>운영정책</option>
						<option>이용문의</option>
						<option>기타</option>
				</select></th>
			</tr>
			<tr>
				<th width="20%">제목</th>
				<td><input class="input" name="faqTitle" required
					value="${faqDto.faqTitle}"></td>
			<tr>

			<tr class="input" height="200" valign="top">
			<th valign="top">내용</th>
			<td>
				<textarea name="faqContent" rows="20" cols="80" required>${faqDto.faqContent}</textarea>
			</td>
		</tr>
		</tbody>


		<tfoot>
			<tr>
				<td align="right" colspan="2"><a class="btn btn-neutral"
					href="faqlist">목록</a>
					<button class="btn btn-positive" type="submit">수정</button></td>
			</tr>
		</tfoot>
	</table>
	</div>
</form>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>