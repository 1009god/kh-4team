<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
		<!-- 정상 / 이상 모두 나옴 -->
		<h1>비밀번호 찾기</h1>
		<form action="findPw" method="post">
			<input type="text"  name="memEmail" placeholder="이메일" required><br><br>
			<input type="tel"  name="memTel" placeholder="번호" required><br><br>
			<button type="submit">찾기</button>	
		</form>
		<c:if test="${param.error != null}">
			<h5>없는 회원 정보입니다</h5>
		</c:if>
	</div>