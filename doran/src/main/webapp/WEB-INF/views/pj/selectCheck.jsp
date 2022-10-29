<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="/WEB-INF/views/template/header2.jsp"></jsp:include>




<span>${OptionsDto.optionsNo}</span>
<span>${OptionsDto.optionsName}</span>
<span>${OptionsDto.optionsPrice}</span>
<span>${OptionsDto.optionsStock}</span>
<span>${OptionsDto.optionsDeliveryPrice}</span>


<a href="order?optionsNo=${OptionsDto.optionsNo}"><button>다음</button></a>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>