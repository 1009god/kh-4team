<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="로그인" name="title"/>
</jsp:include>
<body>
    <div class="" style="float: left;">
      <img src="./image/logo.png" width="50%">
    </div>
    <div class=container style="float: right;">
      
	<div align="center">
		<!-- 정상 / 이상 모두 나옴 -->
		<h1>로그인</h1>
		<form action="login" method="post">
			<input type="text"  name="memEmail" placeholder="이메일" required><br><br>
			<input type="password"  name="memPw" placeholder="비밀번호" required><br><br>
			<button type="submit">로그인</button>	
		</form>
		
		<!-- 이상인 경우만 나오는 화면 -->
		<c:if test="${param.error != null}">
			<h2>입력한 정보가 맞지 않습니다</h2>
		</c:if>
		
	</div>

</body>
</html>
	<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>