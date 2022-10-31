<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>


<div class="container-1400">
<h1>설정</h1>

 
 <div>
 <span><a href="/edit/profile">프로필</a></span>
 <span><a href="/edit/account">계정</a></span>
 <span><a href="/edit/address_list">배송지</a></span>
 </div>
 
 <hr style="border:1px color= silver;" width="100%">
 
 
 <form method="post" enctype="multipart/form-data">
 <!-- 이미지 첨부파일 -->
 <div>프로필 이미지 수정</div>
 <input type="file" name= files">
  
	<div>
		<label>닉네임</label>
		<input type="text" name="memNick" value="${memDto.memNick}" required autocomplete="off">
		<input type="hidden" name="memNo" value="${memDto.memNo}" > 
	</div>
	<div class="left">
	
		<button type="submit" >수정</button>
	
	</div>
</form>

</div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
