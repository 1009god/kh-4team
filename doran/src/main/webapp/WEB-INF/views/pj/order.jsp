<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:set var="OptionsDto" items="${OptionsDto}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>결제</h1>

<span>${OptionsDto.optionsName}</span>
<span></span>
<span></span>


<form action="order?optionsNo=${OptionsDto.optionsNo}" method="post">
<input>
<input>
<input>
<button type="submit">주문하기</button>

</form>

</body>
</html>