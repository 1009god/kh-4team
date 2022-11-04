<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="오류발생" name="title"/>
</jsp:include>

<body>
	<div class="row center">
	<p>
		<img src="${pageContext.request.contextPath}/img/500.gif">
	</p>
	</div>
</body>


<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>