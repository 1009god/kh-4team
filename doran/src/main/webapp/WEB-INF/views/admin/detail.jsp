<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.btn-small {
	padding: 0.25em !important;
}

#proimg {
	border-radius: 100%;
}
</style>
		<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
			<jsp:param value="${memDto.memNo} 회원 정보" name="title" />
		</jsp:include>

		

<div class="container-600 mt-40 mb-40">
	<div class="row left">
		<h1 style="padding-left: 20px">${memDto.memNo}회원 정보</h1>
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
									<td>									
									
									<c:choose>
									<c:when test="${empty profileImg}">
										<img width="80px" height="80px" src="${pageContext.request.contextPath}/img/NonProfile.png" id="proimg">
									</c:when>
									
									<c:otherwise>
										 <!-- 반복문 -->
										<c:forEach var="vo" items="${profileImg}" varStatus="status">	
											<c:if test="${status.last}">			
												<img width="80px" height="80px" src="${pageContext.request.contextPath}/files/download/${vo.profileImgFileNo}" id="proimg">
											</c:if>
										</c:forEach>			
									</c:otherwise>
								</c:choose>			
								
									</td>
								</tr>
							
										<tr>
											<td><a class="btn btn-neutral btn-small" href="${pageContext.request.contextPath}/admin/memlist">목록
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
								<th>비밀번호</th>
								<td>${memDto.memPw}</td>
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
							
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>