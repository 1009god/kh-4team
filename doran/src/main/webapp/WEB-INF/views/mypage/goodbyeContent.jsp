<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="회원 탈퇴" name="title"/>
</jsp:include>

<div class="container-1400">

<h1>회원 탈퇴</h1>
<h2>서비스 이용에 불편함이 있으신가요?</h2>
<h4>불편한 사항이 있다면 언제든 도란도란에 알려주세요</h4>
<h3>탈퇴 전, 유의사항을 확인해 주시기 바랍니다.</h3>
<h5>
	<div>- 후원 및 결제</div>
	<div>- 이미 결제된 후원은 취소되지 않습니다</div>
	<div>-결제 완료 후 탈퇴하더라도, 선물 전달이 완료될 때까지 창작자가 배송 정보를 열람할 수 있습니다</div>
	<div>- 아직 선물을 받지 못했다면, 선물 전달 과정에서 불이익이 발생할 수 있습니다. 배송정보를 변경할 수 없으며 전달에 관한</div>
	<div>커뮤니티 공지, 문의 내용 등을 확인할 수 없습니다.</div>
	<div>- 관련 법령에 따라 후원 및 후원취소에 관한 기록, 결제 및 선물 전달에 관한 기록은 5년 동안 보관됩니다.</div>
	
	<div>-프로젝트</div>
	<div>-작성중, 제출, 반려 및 승인된 프로젝트는 모두 삭제되고 공개예정, 펀딩 진행중인 그 프로젝트는 모두 중단됩니다.</div>
	<div>- 펀딩이 종료된 프로젝트는 삭제되지 않습니다.</div>
	<div>- 탈퇴하더라도, 이미 펀딩이 종료된 프로젝트에 관한 창작자의 의무와 책임 조항은 그 효력을 유지합니다.</div>
</h5>

<input type="checkbox"> 탈퇴 유의사항을 확인했습니다
<button type="submit" onclick="location.href='/mypage/goodbye'">다음</button>

</div>


<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
