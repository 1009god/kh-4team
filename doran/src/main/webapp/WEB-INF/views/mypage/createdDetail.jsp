<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    





<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>

<style>
.boxx {
border : 2px;
border-color: lightgray;
border-style: solid;
padding: 20px;
margin: 40px;
}


.line {
border: 3px;
border-color: black; 
}

.tg {
padding : 2px;
}

.tg-0lax {
padding-right : 30px;
}


</style>


<script>
    console.log(${myCreatedPjDto});
</script>

<div class="container-1400 center">
<!--  
<c:forEach var="OptionsDto" items="${OptionsDto}">
	<div>
		${OptionsDto}
	</div>
</c:forEach>
-->
<div class="container-1400 center">
<h1>주문내역</h1>
</div>
 <hr style="border:1px color= silver;" width="1400px"> 


<div class=" container-1200">

<c:forEach var="createdDetailDto" items="${createdDetailDto}">

	<c:if test="${createdDetailDto.ordersCancelDate!=null}">
		<span>취소된 주문입니다</span>
	</c:if>
	
	<div class="boxx">
	
	<table class="tg">
<thead class="left">
  <tr>
    <th class="tg-0lax">주문번호</th>
    <th class="tg-0lax">${createdDetailDto.ordersNo}</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0lax">프로젝트 옵션</td>
    <td class="tg-0lax">${createdDetailDto.optionsName}</td>
  </tr>
  <tr>
    <td class="tg-0lax">가격</td>
    <td class="tg-0lax">${createdDetailDto.optionsPrice}</td>
  </tr>
  <tr>
    <td class="tg-0lax">배송비</td>
    <td class="tg-0lax">${createdDetailDto.ordersDeliveryPay}</td>
  </tr>
  <tr>
    <td class="tg-0lax">받는 사람</td>
    <td class="tg-0lax">${createdDetailDto.addressName}</td>
  </tr>
  <tr>
    <td class="tg-0lax">연락처</td>
    <td class="tg-0lax">${createdDetailDto.addressTel}</td>
  </tr>
  <tr>
    <td class="tg-0lax">주소</td>
    <td class="tg-0lax">[${createdDetailDto.addressPost}] ${createdDetailDto.addressBasic} ${createdDetailDto.addressDetail}</td>
  </tr>
</tbody>
</table>

</div>
</c:forEach>
</div>

</div>
	



	


<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>