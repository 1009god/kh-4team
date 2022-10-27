<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp">
<jsp:param value="펀딩 등록" name="title"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="reset.css">
<link rel="stylesheet" type="text/css" href="commons.css">
    
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">


</script>

<div class="container-600">
<div class="mt-50 center">
<h1>상품 등록 페이지</h1>
</div>
<form action="insert" method="post" enctype="multipart/form-data">

	<div class="row">
		<input type="hidden" name="pjSellerMemNo" value="${sessionScope.sellerNo}">
	</div>
	
	<div class="row">
		<select name="pjCategory">
			<option value="">선택</option>
			<option>패션/잡화</option>
			<option>뷰티</option>
			<option>푸드</option>
			<option>홈/리빙</option>
			<option>테크/가전</option>
			<option>기타</option>
		</select>
	</div>
	
	<div class="row">
	<label class="row">
	펀딩 제목<input class="input" type="text" name="pjName" placeholder="제목을 설정해주세요">
	</label>
	</div>

	<div class="row">
	<input class="input" type="text" name="pjSummary" placeholder="상품 설명을 간결하게 적어주세요">
	</div>
<div class="row">
목표금액<input class="input" type="text" name="pjTargetMoney">
</div>
<div class="row">
펀딩시작일<input class="input" type="date" name="pjFundingStartDate">
</div>
<div class="row">
끝일<input class="input" type="date" name="pjFundingEndDate">
</div>
<div class="row">
프로젝트 마감일<input class="input" type="date" name="pjEndDate">
</div>
프로젝트 첨부파일<input type="file" name="files" multiple accept=".jpg,.png">
<button type="submit">전송</button>
</form>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>