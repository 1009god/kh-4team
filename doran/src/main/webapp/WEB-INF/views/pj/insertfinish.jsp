<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="${sellerdto.sellerMemNo} 프로젝트 등록 완료" name="title" />
</jsp:include>


<body>
	<div class="row center">
	<p>
		<img src="${pageContext.request.contextPath}/img/pjSuccess.png" width="800px" >
	</p>
	</div>
</body>

<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>



