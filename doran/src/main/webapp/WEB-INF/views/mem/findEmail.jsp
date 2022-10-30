<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
		<!-- 정상 / 이상 모두 나옴 -->
		<h1>이메일 찾기</h1>
		<form action="findEmail" method="post">
			<input type="tel"  name="memTel" placeholder="번호" required><br><br>
			<button type="submit">찾기</button>	
		</form>
		
		<!-- 이상인 경우만 나오는 화면 -->
		<c:if test="${param.error != null}">
			<h2>입력한 정보가 맞지 않습니다</h2>
		</c:if>
		
	</div>