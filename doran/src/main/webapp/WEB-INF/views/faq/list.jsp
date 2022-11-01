<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 사용 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	 div {
            /* border:1px dotted gray; */
        }

        /*
            테이블 디자인
            - 테이블은 기본디자인이 너무 많아서 디자인하기 까다로운 태그 중 하나
        */
        .table {
            border-collapse: collapse;/*테두리 병합*/
            width:100%;
            font-size: 16px;
        }
        /* 
            방법 1 : .table 안에 있는 모든 th와 모든 td를 선택(후손선택자) 
            - 내부에 있는 모든 요소를 선택하므로 테이블이 중첩되는 경우 문제가 발생
        */
        .table th, 
        .table td {
            /* border: 1px solid black; */
        }

        /* 방법 2 : .table 부터 시작하는 모든 경로를 제시하여 th와 td를 선택(자식선택자) */
        .table > thead > tr > th,
        .table > thead > tr > td,
        .table > tbody > tr > th,
        .table > tbody > tr > td,
        .table > tfoot > tr > th,
        .table > tfoot > tr > td {  
            padding:0.5em;
        }

        .table.table-slit {
            border: 3px solid gray;
            border-left: none;
            border-right: none;
        }
        .table.table-slit > thead {
            border-bottom: 2px solid gray;
        }
        .table.table-slit > tfoot {
            border-top: 2px solid gray;
        }
        
        ul.pagination {
            list-style: none;
            margin: 0;
            padding: 0;
            font-size: 16px;
            text-align: center;
        }
        ul.pagination > li {
            display: inline-block;
            border: 1px solid transparent;
            padding: 0.5em;
            line-height: 1em;/*글자 표시 높이 설정 */
            min-width: 2em;
            text-align: center;
            cursor: pointer;
        }
        ul.pagination > li.on,
        ul.pagination > li:hover {
            border-color: #b2bec3;
            color: #d63031;
        }
        ul.pagination > li > a {
            display: block;
            width:100%;
            color: inherit;
            text-decoration: none;
        }

        ul.pagination.pagination-big {
            font-size: 1.25em;
        }
        ul.pagination.pagination-small {
            font-size: 0.75em;
        }
        
        .input-list-search {
    font-size: 20px;
    padding: 0.75em;
    outline: none;
    
}
</style>

<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="FAQ" name="title"/>
</jsp:include>

<!-- <div class="row center">
	<form action="list" method="get">
		<select class="input" name="type" required>
			<option>회원정보</option>
			<option>운영정책</option>
			<option>이용문의</option>
			<option>기타</option>
		</select>
		<input class="input"  name="keyword" required>
		<button class="btn btn-positive">검색</button>
	</form>
	</div> -->
	
	
	<!-- 목록 출력 -->
	<div class="container-900 mt-40 mb-40">
		<h1 align="center">FAQ</h1>
		<table class="table table-slit">
			<thead>
				<tr align="center">
					<th>번호</th>
					<th width="45%">제목</th>
					<th>유형</th>
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach var="faqDto" items="${list}">
					<tr>
						<td>${faqDto.faqNo}</td>
						<td align="left">
							<a href="detail?faqNo=${faqDto.faqNo}">
								${faqDto.faqTitle}
							</a>
						</td>
					<td align="center">${faqDto.faqType}</td>
				</tr>
				</c:forEach>
			</tbody>
	</table>
	</div>
	


<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>