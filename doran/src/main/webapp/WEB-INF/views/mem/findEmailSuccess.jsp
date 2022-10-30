<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h2> 회원님의 이메일</h2>
<div align="center">
		<!-- 정상 / 이상 모두 나옴 -->
		<h1>이메일 찾기</h1>
		<form action="findEmail" method="post">
			<input type="tel"  name="memTel" placeholder="이메일" required><br><br>
			<button type="submit">찾기</button>	
			<h1>${memDto.memEmail} 입니다</h1>
		</form>
		

		
	</div>
