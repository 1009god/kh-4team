<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header3.jsp">
	<jsp:param value="이메일 찾기" name="title" />
</jsp:include>

<style>
	.solid-lines {
	  border: 1px solid gray;
	}
	
	.w-30 {
    width:30%;
	}
	
	.mt-200 { margin-top: 200px;}
	.p-80 {padding:80;}
</style>

<div class="container-300 mt-200 solid-lines p-80 w-30">
		<!-- 정상 / 이상 모두 나옴 -->
		<h1 align="center">이메일 찾기</h1>
		<form action="findEmail" method="post">
	
				<div class="row center">
					<input class="input w-100 solid-lines mt-30"  type="tel" name="memTel" placeholder="핸드폰 번호" required>
				</div>
		
				<div class="row center">
					<button class="btn btn-positive w-100" type="submit">이메일 찾기</button>
				</div>
		
				<c:if test="${param.error != null}">
					<h5>없는 회원 정보입니다</h5>
				</c:if>
		</form>
	</div>
	
	