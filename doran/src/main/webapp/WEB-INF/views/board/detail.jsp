<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="도란도란-게시글" name="title"/>
</jsp:include>

<style>
	.solid-lines {
	  border: 1px solid gray;
	}
	
	.input-list-search {
    font-size: 20px;
    padding: 0.75em;
    outline: none
    
    }
    span {
    	color: #0072b2;
    }
    
}
</style>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	$(function(){
		//목표 :
		//1. edit-btn 을 누르면 view 를 숨기고 editor 를 보여줌
		//2. cancel-btn 을 누르면 editor 를 숨기고 view 를 보여줌
		//3. 처음엔 view 만 보여준다
		$(".edit-btn").click(function(){
			$(this).parents(".view").hide();
			$(this).parents(".view").next(".editor").show();
		});
		$(".cancel-btn").click(function() {
			$(this).parents(".editor").hide();
			$(this).parents(".editor").prev(".view").show();			
		});
		//3.
		$(".editor").hide();
	});
</script>


<div class="container-800 mt-40 mb-40">
	<div class="row center">
	<table border = "1" width ="500" class="table table-border">
		<tbody>
			<tr>
				<th width = "25%">번호</th>
				<td>${boardDto.boardPostNo}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${boardDto.boardTitle}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${boardDto.memNick}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>
					<fmt:formatDate value="${boardDto.boardWriteTime}" pattern="y년 M월 d일 E요일 a h시 m분 s초"/>
				</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${boardDto.boardViewCnt}</td>
			</tr>
			<tr height="200" valign="top"">
				<th>내용</th>
				<td>
					<!-- pre 태그 엔터, 띄어쓰기, 탭 키 그대로 표시 -->
					<c:forEach var="filesDto" items="${filesList}" >		
						<img width="auto" height="auto" src="http://localhost:8888/files/download/${filesDto.filesNo}" >
					</c:forEach>
					<pre>${boardDto.boardContent}</pre>
				</td>
			</tr>
			
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2" align="right">
					<a href="write"><img src="/img/pencil.png" width="20" height="20"></a>
		
						<%--
							회원은 자신의 글만 수정/삭제 가능하도록 처리
						 --%>
						<c:set var="owner" value="${loginNo == boardDto.boardMemNo}"></c:set>
						
						<c:if test="${owner}">
							<a href="edit?boardPostNo=${boardDto.boardPostNo}"><img src="/img/edit.png" width="20" height="20"></a>
							<a href="delete?boardPostNo=${boardDto.boardPostNo}"><img src="/img/delete.png" width="20" height="20"></a>
						</c:if>
						<a href="list"><img src="/img/list.png" width="20" height="20"></a>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
<div class="row center">
<table border="1" width="500" class="table table-slit table-reply-list">

	<!-- 댓글 목록 -->
	<thead >
				<tr>
					<td class="under-lines" colspan="2" >
						총 ${replyList.size()}개의 댓글이 있습니다.
						<hr>
					</td>
				</tr>
			</thead>
	<tbody>
	<c:forEach var="replyDto" items="${replyList}">
		<!-- 사용자에게 보여주는 화면  -->
		<tr class="view">
			<td width="90%">
			<!-- 작성자 -->
			<img src="/img/smile.png" width="20" height="20">
			${replyDto.memNick}
			<c:if test="${boardDto.boardMemNo == replyDto.replyMemNo}">
			<span>(작성자)</span>
			</c:if>
			
				<pre>${replyDto.replyContent}</pre>
				
				<fmt:formatDate value="${replyDto.replyWriteTime}" pattern="yyyy-MM-dd HH:mm"/>
			</td>
			<th>
				<!-- 수정과 삭제는 현재 사용자가 남긴 댓글에만 표시 -->
				<c:if test="${loginNo == replyDto.replyMemNo}">
					<a style="display:block; margin:10px 0px;" class="edit-btn"><img src="/img/edit.png" width="20" height="20"></a>
					<a style="display:block; margin:10px 0px;" class="delete-btn" href="reply/delete?replyNo=${replyDto.replyNo}&replyBoardPostNo=${replyDto.replyBoardPostNo}"><img src="/img/delete.png" width="20" height="20"></a>
				</c:if>
			</th>
		</tr>
			<c:if test="${loginNo == replyDto.replyMemNo}">
			<!-- 수정하기 위한 화면 : 댓 작성자 본인에게만 출력-->
		<tr class="editor">
			<th colspan="2">
				<form action="reply/edit" method="post">
					<input type="hidden" name="replyNo" value="${replyDto.replyNo}">
					<input type="hidden" name="replyBoardPostNo" value="${replyDto.replyBoardPostNo}">
					<textarea name="replyContent" rows="5" cols="55" required>${replyDto.replyContent}</textarea>
					<button type="submit"><img src="/img/edit.png" width="20" height="20"></button>
					<a class="cancel-btn"><img src="/img/cancel.png" width="20" height="20"></a>
				</form>
			</th>
		</tr>
		</c:if>
		</c:forEach>
	</tbody>
</table>
	</div>
	
<div class="row center">
<c:choose>
	<c:when test="${loginNo != null}">
		<form action="reply/write" method="post">
	<input type="hidden" name="replyBoardPostNo" value="${boardDto.boardPostNo}">
	<table class="table">
		<tbody>
			<tr>
				<th class="solid-lines">
					<textarea class="input-list-search w-100 fix-size" name="replyContent" rows="5" cols="55" required placeholder="댓글 작성..."></textarea>
				</th>
				<th valign="bottom">
					<button class="btn btn-positive" type="submit">등록</button>
				</th>
			</tr>
		</tbody>
	</table>
	</form>
		
	</c:when>
	<c:otherwise>
		<table border="1" width="500">
			<tbody>
				<tr>
					<th>
						<textarea name="replyContent" rows="5" cols="55" placeholder="로그인 후 댓글 작성이 가능합니다" disabled></textarea>
					</th>
					<th>
						<button type="submit" disabled>등록</button>
					</th>
				</tr>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
</div>
</div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
