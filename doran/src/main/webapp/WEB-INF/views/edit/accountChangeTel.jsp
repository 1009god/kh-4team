<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="비밀번호 변경" name="title"/>
</jsp:include>

<form method="post">
	<div class="row">
		
		<input type="hidden" name="memNo" value="${memDto.memNo}" > 
			
		<div>
			<label>전화번호</label>
			<input type="text" name="memTel" value="${memDto.memTel}" required autocomplete="off">
		</div>
		
	</div>
	
	<div class="row center">	
		<button type="submit" >수정</button>	
	</div>
	
</form>


</body>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>