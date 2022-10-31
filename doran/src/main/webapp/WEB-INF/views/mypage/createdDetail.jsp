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


<c:forEach var="createdDetailDto" items="${createdDetailDto}">
<div class="box">
	<span>주문번호: ${createdDetailDto.ordersNo}</span>
	<span>상품명: ${createdDetailDto.ordersName}</span>
</div>

</c:forEach>

	


<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>