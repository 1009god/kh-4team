<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<h1>게시글 작성</h1>

<form action="write" method="post">
<table border = "1" width ="500">
	<tbody>
		<tr>
			<th>문의 유형</th>
			<td>
				<select class="input" name="type" required>
				<option value="doran_q_type">취소문의</option>
				<option value="doran_q_type">배송문의</option>
				<option value="doran_q_type">반품문의</option>
				<option value="doran_q_type">교환,환불문의</option>
				<option value="doran_q_type">작동오류문의</option>
				<option value="doran_q_type">기타문의</option>
			
		</select>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="doranQTitle" required>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="doranQContent" rows="10" cols="60" required></textarea>
			</td>
		</tr>
		
	</tbody>
	<tfoot>
		<tr>
			<td align="right" colspan="2">
				<button type="submit">등록하기</button>
			</td>
		</tr>
	</tfoot>
</table>
</form>