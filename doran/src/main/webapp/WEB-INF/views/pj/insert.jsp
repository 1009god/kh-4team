<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
</head>
<body>
<div class="mt-50 center">
<h1>상품 등록 페이지</h1>
</div>
<form action="insert" method="post" enctype="multipart/form-data">
<div class="row">
<input type="hidden" name="pjSellerMemNo" value="${sessionScope.sellerNo}">
</div>
<div class="row">
카테고리  <input type="text" name="pjCategory">
</div>
<div class="row">
상품이름 <input type="text" name="pjName">
</div>
<div class="row">
요약글  <input type="text" name="pjSummary">
</div>
<div class="row">
목표금액 <input type="text" name="pjTargetMoney">
</div>
<div class="row">
펀딩시작일<input type="date" name="pjFundingStartDate">
</div>
<div class="row">
끝일<input type="date" name="pjFundingEndDate">
</div>
<div class="row">
프로젝트 마감일<input type="date" name="pjEndDate">
</div>
프로젝트 첨부파일<input type="file" name="files" multiple accept=".jpg,.png">
<button type="submit">전송</button>
</form>
</body>
</html>