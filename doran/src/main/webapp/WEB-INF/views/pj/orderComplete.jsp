<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="주문완료" name="title" />
</jsp:include>

<style>
     .notOngoing{
     	width : 700px;
     	height : 500px;
     	margin : 0 auto;

     }
</style>


	<div class="center notOngoing">
		<div style = "padding : 80px">
		    <h2>프로젝트 후원 신청이 완료되었습니다</h2>
		</div>
		<div class="row" style = "color : #6495ED">
		    <h3><a href="http://localhost:8888/mypage/profile" style = "color : #0072b2"><i class="fa-solid fa-face-smile"></i> 마이페이지로</a></h3>
		</div>
		<div class="row" >
		    <h3 ><a href="http://localhost:8888/" style = "color : #0072b2"><i class="fa-solid fa-house"></i> 홈으로</a></h3>
		</div>
	</div>


    <jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>