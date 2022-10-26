<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.btn-small {
	padding: 0.25em !important;
}
</style>
		<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
			<jsp:param value="${memdto.memNo} 판매자 정보" name="title" />
		</jsp:include>

		

<div class="container-600 mt-40 mb-40">
	<div class="row left">
		<h1 style="padding-left: 20px">${sellerDto.sellerMemNo}판매자 정보</h1>
	</div>

	<div class="row center">
		<table class="table">
			<tbody>
				<!-- 프로필 이미지를 출력 -->
				<tr>
					<th width="25%" valign="top" class="center">
						<table class="table">
							<tbody class="center">
								<tr>
									<td><img class="image image-circle image-shadow"
										src="download?memNo=${memDto.memNo}" width="100"
										height="100"></td>
								</tr>
							
										<tr>
											<td><a class="btn btn-neutral btn-small" href="/admin/sellerlist">목록
													보기</a></td>
										</tr>
										<tr>
											<td><a class="btn btn-neutral btn-small"
												href="change?memNo=${sellerDto.sellerMemNo}">정보 변경</a></td>
										</tr>
										<tr>
											<td><a class="btn btn-neutral btn-small"
												href="delete?memNo=${sellerDto.sellerMemNo}">판매자 박탈</a></td>
										</tr>
									
							</tbody>
						</table>
					</th>
					<td>
						<%-- <table class="table table-border">
							<tr>
								<th width="20%">아이디</th>
								<td>${memDto.memEmail}</td>
							</tr>
							<tr>
								<th>번호</th>
								<td>${memDto.memNo}</td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td>${memDto.memNick}</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>${memDto.memTel}</td>
							</tr>
							
							<tr>
								<th>가입일시</th>
								<td><fmt:formatDate value="${memDto.memJoinDate}"
										pattern="y년 M월 d일 E a h시 m분 s초" /></td>
							</tr>
							
						</table> --%>
						
						<table class="table table-border">
							<tr>
								<th width="20%">닉네임</th>
								<td>${memDto.memEmail}</td>
							</tr>
							<tr>
								<th>은행</th>
								<td>${dto.sellerBank}</td>
							</tr>
							<tr>
								<th>계좌</th>
								<td>${dto.sellerAccount}</td>
							</tr>
							<tr>
								<th>판매자 여부</th>
								<td>${dto.sellerCheck}</td>
							</tr>
							
							<tr>
								<th>판매자 신청 날짜</th>
								<td><fmt:formatDate value="${dto.registryDate}"
										pattern="y년 M월 d일 E a h시 m분 s초" /></td>
							</tr>
							
						</table>
						
						
						
					</td>
				</tr>
			</tbody>
		</table>
	</div>