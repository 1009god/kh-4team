<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="FAQ 수정" name="title"/>
</jsp:include>

<form action="faqedit" method="post">
<div class="container-500 mt-40">
	
	<div class="row left">
		<h1>FAQ 수정</h1>
	</div>
	<div class="row left">
		<input type="hidden" name="faqNo" value="${faqDto.faqNo}">
		<select class="input" name="faqType" required value="${faqDto.faqType}">
			<option value="">선택</option>
			<option>회원정보</option>
			<option>운영정책</option>
			<option>이용문의</option>
			<option>기타</option>
		</select>
	</div>
	<div class="row left">
		<input class="input" name="faqTitle"  required value="${faqDto.faqTitle}">
	</div>
	<div class="row center">
		<textarea class="input w-100" name="faqContent"  required rows="8">${faqDto.faqContent}</textarea>
	</div>
	
	
	<div class="row right">
		<a class="btn btn-neutral" href="faqlist">목록</a>
		<button class="btn btn-positive" type="submit">수정</button>
		<a class="btn btn-neutral" href="faqlist">목록</a>
	</div>
	</tbody>
	</table>
</div>
</form>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>