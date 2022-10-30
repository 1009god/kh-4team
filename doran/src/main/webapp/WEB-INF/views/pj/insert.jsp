<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:include page="/WEB-INF/views/template/header2.jsp">
			<jsp:param value="${sellerdto.sellerMemNo} 판매자 정보" name="title" />
		</jsp:include>



<html>
<body>
<form action="insert" method="post" enctype="multipart/form-data">
카테고리<input type="text" name="pjCategory"><br>
펀딩제목<input type="text" name="pjName"><br>
펀딩설명<input type="text" name="pjSummary"><br>
목표금액<input type="number" name="pjTargetMoney"><br>
시작일<input type="date" name="pjFundingStartDate"><br>
끝일<input type="date" name="pjFundingEndDate"><br>
프로젝트 마감일<input type="date" name="pjEndDate"><br>




옵션이름<input type="text" name="optionsName"><br>
옵션가격<input type="number" name="optionsPrice"><br>
옵션수량<input type="number" name="optionsStock"><br>
배달비<input type="number" name="optionsDeliveryPrice"><br>
<br>
<!-- 여기서 배너, 섬네일, 상세페이지에 속할 이미지 -->
<input type="file" name="files" >
<input type="file" name="files" >
<input type="file" name="files" multiple>
<button>전송</button>
</form>

</body>
</html>
