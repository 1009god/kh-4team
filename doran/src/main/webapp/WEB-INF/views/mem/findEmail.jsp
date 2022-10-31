<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
		<!-- 정상 / 이상 모두 나옴 -->
		<h1>이메일 찾기</h1>
		<form action="findEmail" method="post">
				<div class="row center">
				</div>
			
				<div class="row center">
					<input type="tel" name="memTel" placeholder="핸드폰 번호" required><br><br>
				</div>
		
				<div class="row center">
					<button type="submit">확인</button>
				</div>
		
				<c:if test="${param.error != null}">
					<h5>없는 회원 정보입니다</h5>
				</c:if>
		</form>
		
		
	</div>
	
	