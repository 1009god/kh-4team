<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="수정 오류" name="title"/>
</jsp:include>

<div align="center">
	<h1>존재하지 않는 번호입니다</h1>
	<h2><a href="/admin/memlist">회원 리스트 목록</a></h2>
	<h2><a href="/admin/pjlist">프로젝트 리스트 목록</a></h2>
	<h2><a href="/admin/sellerlist">판매자 리스트 목록</a></h2>
</div>

<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>