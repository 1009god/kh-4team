<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    





<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
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


<script>
    console.log(${myCreatedPjDto});
</script>

<div class="container-1400 center">

<c:forEach var="createdDetailDto" items="${createdDetailDto}">
<div class="box">
	<c:if test="${createdDetailDto.ordersCancelDate!=null}">
		<span>취소된 주문입니다</span>
	</c:if>
	<span>주문번호: ${createdDetailDto.ordersNo}</span>
	<span>상품명: ${createdDetailDto.optionsName}</span>
	<span>가격: ${createdDetailDto.optionsPrice}</span>
	<span>배송비: ${createdDetailDto.ordersDeliveryPay}</span>
	<span>수령인: ${createdDetailDto.addressName}</span>
	<span>연락처: ${createdDetailDto.addressTel}</span>
	<span>우편번호: ${createdDetailDto.addressPost}</span>
	<span>기본주소: ${createdDetailDto.addressBasic}</span>
	<span>상세주소: ${createdDetailDto.addressDetail}</span>
	
</div>

</c:forEach>

	
</div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>