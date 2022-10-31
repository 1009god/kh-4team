<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
<jsp:param value="판매자 등록" name="title"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="reset.css">
    <link rel="stylesheet" type="text/css" href="commons.css">
    
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">


</script>
<div class="container-600">

<div class="mt-50">
<h2>판매자 회원신청<h2>
</div>

<div class="row">
 <form action="sellerjoin" method="post" enctype="multipart/form-data">
 
 <div class="row">
 <input type="hidden" name="sellerMemNo" value="${sessionScope.loginNo}" class="input">  <!--  여기에 로그인한 회원의 회원 번호를-->
 </div>
 
 <div class="row">
 <input type="text" name="sellerBank" placeholder="등록하실 은행" required class="input" autocomplete="off"> 
 </div>
 
  <div class="row">
 <input type="text" name="sellerAccount" placeholder="계좌번호" required class="input" autocomplete="off">
  </div>
<div class="row">
첨부파일(사업자등록)<input type="file" name="files" multiple accept=".jpg,.png">
</div>
<div class="mt-50">
<textarea rows="6" class="input w-100 fix-size q1-input" readonly>제 1 조 (목적)
이 약관은 도란도란(이하 "사이트"라 합니다)에서 제공하는 인터넷서비스(이하 "서비스"라 합니다)의 이용 조건 및 절차에 관한 기본적인 사항을 규정함을 목적으로 합니다.

 

제 2 조 (약관의 효력 및 변경)
① 이 약관은 서비스 화면이나 기타의 방법으로 이용고객에게 공지함으로써 효력을 발생합니다.
② 사이트는 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제1항과 같은 방법으로 공지 또는 통지함으로써 효력을 발생합니다.

 

제 3 조 (용어의 정의)
이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
① 회원 : 사이트와 서비스 이용계약을 체결하거나 이용자 아이디(ID)를 부여받은 개인 또는 단체를 말합니다.
② 신청자 : 회원가입을 신청하는 개인 또는 단체를 말합니다.
③ 아이디(ID) : 회원의 식별과 서비스 이용을 위하여 회원이 정하고 사이트가 승인하는 문자와 숫자의 조합을 말합니다.
④ 비밀번호 : 회원이 부여 받은 아이디(ID)와 일치된 회원임을 확인하고, 회원 자신의 비밀을 보호하기 위하여 회원이 정한 문자와 숫자의 조합을 말합니다.
⑤ 해지 : 사이트 또는 회원이 서비스 이용계약을 취소하는 것을 말합니다.</textarea>
</div>
<div class="row">
<label>
<input type="checkbox" class="ckbox">확인했습니다
</label>
</div>
 <div class="mt-50 right">
 <button type="submit" class="btn btn-neutral agree" >판매자 가입 신청</button>
 </div>
 </form>
</div>
</div>


<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>