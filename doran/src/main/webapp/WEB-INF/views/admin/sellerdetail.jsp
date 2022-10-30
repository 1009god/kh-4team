<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.btn-small {
	padding: 0.25em !important;
}
.btn-color{
	color : red;
}
.adminbtn-color{
	color : green;
}

#proimg {
	border-radius: 100%;
}



</style>
		<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
			<jsp:param value="${sellerdto.sellerMemNo} 판매자 정보" name="title" />
		</jsp:include>

		

<div class="container-700">
	<div class="row left">
		<h1 style="padding-left: 20px">NO.${sellerDto.sellerMemNo}회원 판매자 정보</h1>
	</div>

	<div class="center">
		<table class="table">
			<tbody>
				<!-- 프로필 이미지를 출력 -->
				<tr>
					<th width="10%" valign="top" class="center">
						<table class="table">
							<tbody class="left">
								<tr>
									<c:forEach var="vo" items="${profileImg}" varStatus="status">	
										<c:if test="${status.last}">			
											<img width="80px" height="80px" src="http://localhost:8888/files/download/${vo.profileImgFileNo}" id="proimg">
										</c:if>
									</c:forEach>
								</tr>
							
										<tr>
											<td><a class="btn btn-neutral btn-small" href="/admin/sellerlist">목록 보기</a></td>
										</tr>
										<tr>
											<td><a class="btn btn-neutral btn-small"
												href="change?memNo=${sellerDto.sellerMemNo}">정보 변경</a></td>
										</tr>


								<c:choose>
									<c:when test="${sellerDto.sellerCheck eq '승인'}">
										<tr>
											<td><a class="btn btn-neutral btn-small"
												href="revoke?sellerMemNo=${sellerDto.sellerMemNo}">판매자 취소</a></td>
										</tr>
									</c:when>
									<c:otherwise>
										<td><a class="btn btn-neutral btn-small"
												href="agree?sellerMemNo=${sellerDto.sellerMemNo}">판매자 승인</a></td>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</th>
					<div>
					<table class="table table-border">
							<tr>
								<th width="15%">아이디</th>
								<td>${sellerDto.memEmail}</td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td>${sellerDto.memNick}</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>${sellerDto.memTel}</td>
							</tr>
							
							<tr>
								<th>가입일시</th>
								<td><fmt:formatDate value="${sellerDto.memJoinDate}"
										pattern="y년 M월 d일 E a h시 m분 s초" /></td>
							</tr>
							
						</table>
						
						<style>
						.table table-border >tr > td {
							width=600px;
						} 
						</style>
						
						
						
						<table class="table table-border">
							<tr>
								<th width="15%">판매자 번호</th>
								<td>${sellerDto.sellerMemNo}</td>
							</tr>
							<tr>
								<th>은행</th>
								<td>${sellerDto.sellerBank}</td>
							</tr>
							<tr>
								<th>계좌</th>
								<td>${sellerDto.sellerAccount}</td>
							</tr>
							<tr>
								<th>판매자 여부</th>
								<td>${sellerDto.sellerCheck}</td>
							</tr>
							
							<tr>
								<th>판매자 신청 날짜</th>
								<td><fmt:formatDate value="${sellerDto.sellerRegistryDate}"
										pattern="y년 M월 d일 E a h시 m분 s초" /></td>
							</tr>
							<tr>
								<th>판매자 신청 첨부파일</th>
									<td>
										<c:forEach var="sellerfileListVo" items="${sellerfileList}">
											<li><a href = "http://localhost:8888/files/download/${sellerfileListVo.sfFileNo}">
													${sellerfileListVo.filesUploadName}(${sellerfileListVo.filesSize} bytes) 
													-
													[${sellerfileListVo.filesType}]
													</a>					
											</li>	
										</c:forEach>
									</td>
							</tr>
							
						</table>
						
						
						
					</div>
				</tr>
			</tbody>
		</table>
	</div>