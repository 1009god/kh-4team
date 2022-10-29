<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <h1>공지사항 작성</h1>
    
    <form action="write" method="post" enctype="multipart/form-data">
    	<table border="1" width="500">
    		<tbody>
    			<tr>
    				<th>제목</th>
    				<td>
    					<input type="text" name="noticeTitle" required>
    				</td>
    			</tr>
    			<tr>
    				<th>내용</th>
    				<td>
    					<textarea name="noticeContent" rows="10" cols="60" required></textarea>
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
    				<a href="/notice/list">목록으로</a>
    				<button type="submit">등록하기</button>
    			</tr>
    		</tfoot>
    	</table>
    </form>