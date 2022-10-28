<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<span>${OptionsDto.optionsNo}</span>
<span>${OptionsDto.optionsName}</span>
<span>${OptionsDto.optionsPrice}</span>
<span>${OptionsDto.optionsStock}</span>
<span>${OptionsDto.optionsDeliveryPrice}</span>


<a href="order?optionsNo=${OptionsDto.optionsNo}"><button>다음</button></a>
</body>
</html>