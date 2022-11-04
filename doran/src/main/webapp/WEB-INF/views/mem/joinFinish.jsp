<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="회원가입 성공" name="title"/>
</jsp:include>

<body>
	<div class="row center">
	<img class="w-50" src="${pageContext.request.contextPath}/img/joinCele.png">
	<h2><a href="${pageContext.request.contextPath}/mem/login" style="color: #0072b2">- 로그인 하러 가기</a></h2>
	<h2><a href="${pageContext.request.contextPath}" style="color: #0072b2">- 도란도란 홈페이지로 가기</a></h2>
	</div>
</body>



<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
