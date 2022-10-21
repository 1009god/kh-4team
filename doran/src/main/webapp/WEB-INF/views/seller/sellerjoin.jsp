<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>셀러가입</title>
</head>
<body>
 셀러가입 페이지
 
 <form action="sellerjoin" method="post">
 <input type="hidden" name="sellerMemNo" value="${sessionScope.loginNo}"> <br>  <!--  여기에 로그인한 회원의 회원 번호를-->
 <input type="text" name="sellerBank" placeholder="등록하실 은행" required> <br>
 <input type="text" name="sellerAccount" placeholder="계좌번호" required> <br>
 <button type="submit">판매자 가입 신청</button>
 </form>
약관, 버튼 잠금 추가 예정
</body>
</html>