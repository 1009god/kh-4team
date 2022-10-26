<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.border{
border:solid;
}
</style>

</head>

<body>

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
</body>
</html>