<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="회원 가입" name="title"/>
</jsp:include>

<form action="insert" method="post">
<div class="container-300 mt-40">
	<div class="row center">
		<h1>관리자 가입 정보 입력</h1>
	</div>
	
	<div class="row left">
		<label>Email</label>
		<input class="input w-100" name="adminEmail" placeholder="이메일 입력" type="text" required autocomplete="off">
	</div>	
	
	<div class="row left">
		<label>Password</label>
		<input class="input w-100" name="adminPw" placeholder="비밀번호 입력" type="password" required>
	</div>
	
	<div class="row left">
		<label>Nickname</label>
		<input class="input w-100" name="adminNick" placeholder="사용하실 관리자명을 입력해주세요!" type="text" required autocomplete="off">
	</div>
	
</div>
	<div class="row center">
		<button class="btn btn-positive w-10">가입하기</button>
	</div>

</form>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>
