<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="내가 후원한 프로젝트" name="title"/>
</jsp:include>

<style>
.box {
border : 2px;
border-color: lightgray;
border-style: solid;
padding: 20px;
margin: 40px;
}



</style>





<div class=container-1200>

<div class=box>
	<div> 후원 정보 </div>
	
	<div>카테고리 : ${supportPjImfo.pjCategory}</div>
	<div>셀러 닉네임 : ${supportPjImfo.memNick}</div>
	<div><h1>프로젝트 이름 : ${supportPjImfo.pjName}</h1></div>
</div>

<div class=box>
	<div>주문 정보</div>
	
	<div>주문번호 : ${supportDetail.ordersNo}</div>
	<div>주문날짜 :${supportDetail.ordersDate}</div>
	<div>펀딩 마감일 : ${supportDetail.pjFundingEndDate}</div>

</div>

<div class=box>
	<div>옵션 정보</div>
	
	<div>옵션이름 : ${supportDetail.optionsName}</div>
	<div>후원금액 : ${supportDetail.optionsPrice}</div>
	<div>배송비  : ${supportDetail.optionsDeliveryPrice}</div>

</div>

<div class=box>
	<div>배송 정보</div>
	
	<div>받는사람 : ${supportDetail.addressName}</div>
	<div>연락처 : ${supportDetail.addressTel}</div>
	<div>우편번호 : ${supportDetail.addressPost}</div>
	<div>주소  : ${supportDetail.addressBasic}</div>
	<div>상세주소 : ${supportDetail.addressDetail}</div>
</div>

<div class=" center">
	<button><a href="/mypage/supported">후원 목록으로 가기</a></button>
	<button><a href="/mypage/supported/cancel?ordersNo=${supportDetail.ordersNo}">주문 취소하기</a></button>
</div>


</div>





<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>



