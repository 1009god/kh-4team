<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="장바구니" name="title"/>
</jsp:include>



    <c:forEach var="OrdersMemSearchDto" items="${OrdersMemSearchDto}">
        <div>
            <span>${OrdersMemSearchDto.ordersNo}</span>
            <span>${OrdersMemSearchDto.pjNo}</span>
            <span>${OrdersMemSearchDto.pjName}</span>
            <span>${OrdersMemSearchDto.optionsNo}</span>
            <span>${OrdersMemSearchDto.optionsName}</span>
            <span>${OrdersMemSearchDto.optionsPrice}</span>
            <span>${OrdersMemSearchDto.optionsDeliveryPrice}</span>
        </div>
    </c:forEach>


    <!--푸터-->
    <jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>