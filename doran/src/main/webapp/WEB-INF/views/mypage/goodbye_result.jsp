<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="회원 탈퇴" name="title"/>
</jsp:include>


<div class="container-1400 center" >

	<img src = "${pageContext.request.contextPath}/img/Doran.png" width="50%" height="500px">
	<div class="center">
		<h3>회원 탈퇴가 완료되었습니다</h3>
		<h5>그동안 DoranDoran을 이용해 주셔서 감사합니다</h5>
		<h5>더더욱 노력하고 발전하는 도란도란이 되겠습니다</h5>
	
		<button type="submit" onclick="location.href='${pageContext.request.contextPath}'">홈으로</button>
	</div>	
</div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>



