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
			<jsp:param value="${memDto.memEmail} 회원 정보" name="title" />
		</jsp:include>

		

<div class="container-600 mt-40 mb-40">
	<div class="row left">
		<h1 style="padding-left: 20px">${memDto.memEmail}회원 정보</h1>
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
										src="download?membEmail=${memDto.memNo}" width="100"
										height="100"></td>
								</tr>
							
										<tr>
											<td><a class="btn btn-neutral btn-small" href="/admin/memlist">목록
													보기</a></td>
										</tr>
										<tr>
											<td><a class="btn btn-neutral btn-small"
												href="change?memNo=${memDto.memNo}">정보 변경</a></td>
										</tr>
										<tr>
											<td><a class="btn btn-neutral btn-small"
												href="delete?memNo=${memDto.memNo}">회원 탈퇴</a></td>
										</tr>
									
							</tbody>
						</table>
					</th>
					<td>
						<table class="table table-border">
							<tr>
								<th width="20%">아이디</th>
								<td>${dto.memEmail}</td>
							</tr>
							<tr>
								<th>번호</th>
								<td>${dto.memNo}</td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td>${dto.memNick}</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>${dto.memTel}</td>
							</tr>
							
							<tr>
								<th>가입일시</th>
								<td><fmt:formatDate value="${dto.memJoinDate}"
										pattern="y년 M월 d일 E a h시 m분 s초" /></td>
							</tr>
							
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>