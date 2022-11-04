<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="faq" name="title"/>
</jsp:include>

    <h1>공지사항 작성</h1>
    
    <form action="noticewrite" method="post" enctype="multipart/form-data">
    	<table class="table table-border mt-50">
    		<tbody>
    		 
    			<tr>
    				<th>제목</th>
    				<td>
    					<input type="text" name="noticeTitle" required>
    				</td>
    			</tr>
    			<tr height="200" valign="top">
    				<th>내용</th>
    				<td>
    					<textarea name="noticeContent" rows="10" cols="80" required></textarea>
    				</td>
    			</tr>
    			
    			<tr>
    				<th>첨부파일</th>
    				<td>
    					<input type="file" name="files" multiple></input>
    				</td>
    			</tr>
    		</tbody>
    		<tfoot>
    			<tr>
    				<td align="right" colspan="2">
    				<a href="${pageContext.request.contextPath}/admin/noticelist">목록으로</a>
    				<button type="submit">등록하기</button>
    			</tr>
    		</tfoot>
    	</table>
    </form>
    
    
<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>