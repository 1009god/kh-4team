<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>

<div class="container-1400">
<h1>설정</h1>

 
 <div>
 <span><a href="//edit/profile">프로필</a></span>
 <span><a href="/edit/account">계정</a></span>
 <span><a href="/edit/address_list">배송지</a></span>
 </div>
 
 <hr style="border:1px color= silver;" width="100%">

 
	<div class="row">
		
		<input type="hidden" name="memNo" value="${memDto.memNo}" > 
	
		<div>
			<label>비밀번호</label>
				<!--  <input type="text" name="memPw" value="${memDto.memPw}" required autocomplete="off">-->
		</div>
		<div class="center">	
			<button type="button" ><a href="/edit/account_change_pw">수정</a></button>	
		</div>
		
		<form method="post">
		<div>
			<label>전화번호</label>
			<input type="text" name="memTel" value="${memDto.memTel}" required autocomplete="off">
		</div>
		<div class="center">	
			<button type="submit" >수정</button>	
		</div>
		</form>
		
	</div>
	
	

	
	<div>
	 회원 탈퇴
	<button type="button" ><a href="/mypage/goodbye_content">탈퇴</a></button> 
	</div>



</div>
<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>