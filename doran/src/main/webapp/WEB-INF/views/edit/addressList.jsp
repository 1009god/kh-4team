<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>



<style>
.border{
border:solid;
}
</style>


<div class="container-1400">

<h1>설정</h1>

 
 <div>
 <span><a href="//edit/profile">프로필</a></span>
 <span><a href="/edit/account">계정</a></span>
 <span><a href="/edit/address_list">배송지</a></span>
 </div>
 
 <hr style="border:1px color= silver;" width="100%">




<div>
등록된 배송지<button><a href="http://localhost:8888/edit/address_plus">+추가</a></button>

</div>

<c:forEach var= "dto" items = "${list}"> <!-- 컨트롤러에서 넘어온 list -->

<div class="border">
<button><a href="address_delete?addressNo=${dto.addressNo}">삭제</a></button>
<button><a href="address_change?addressNo=${dto.addressNo}">수정</a></button>
${dto.addressNo}
	<div>
		${dto.addressName}
	</div>
	<div>
		${dto.addressPost}
		${dto.addressBasic}
		${dto.addressDetail}
	</div>
	<div>
		${dto.addressTel}
	</div>
</div>

</c:forEach>

</div>


<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
