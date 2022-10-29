<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<table border = "1" width = "500">
	<tbody>
		<tr>
			<th width = "25%">제목</th>
			<td>${noticeDto.noticeTitle}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>
				<fmt:formatDate value="${noticeDto.noticeWriteTime}" pattern="y년 M월 d일 E요일 a h시 m분 s초"/>
			</td>
		</tr>
		<tr height="200" valign="top"">
			<th>내용</th>
			<td>
				<!-- pre 태그 엔터, 띄어쓰기, 탭 키 그대로 표시 -->
				<pre>${noticeDto.noticeContent}</pre>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2" align="right">
				<a href="write">글쓰기</a>
				<a href="edit?noticeNo=${noticeDto.noticeNo}">수정하기</a>
				<a href="delete?noticeNo=${noticeDto.noticeNo}">삭제하기</a>
				<a href="list">목록으로</a>
			</td>
		</tr>
	</tfoot>
</table>