<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	
	<jsp:param value="1대1문의" name="title"/>
</jsp:include>
<style>

        .table > thead > tr > th,
        .table > thead > tr > td,
        .table > tbody > tr > th,
        .table > tbody > tr > td,
        .table > tfoot > tr > th,
        .table > tfoot > tr > td {  
            border : 1px solid lightgray;
        }
	
</style>


<div class="container-800 mt-40 mb-50">
<table class="table" border="1"  >
	<thead>
	<tr>
<!-- 		<td align = "right" colspan="6"> -->
<!-- 			<a href="write">글쓰기</a> -->
<!-- 		</td> -->
		<tr>
			<th>글번호</th>
			<th>문의유형</th>
			<th width="45%">제목</th>
			<th>회원번호</th>
			<th>작성일</th>
<!-- 			<th>처리상태</th> -->
<!-- 			<th>그룹</th> -->
<!-- 			<th>부모글</th> -->
<!-- 			<th>차수</th> -->
		</tr>
	</thead>
	<tbody align = "center">
		<c:forEach var="doranQDto" items="${list}">
		<tr>
			<!-- 차수만큼 띄어쓰기 -->
			
			<td >${doranQDto.doranQNo}</td>
			
			<c:choose>
				<c:when test="${empty doranQDto.doranQType}">
					<td>답변</td>
				</c:when>
				<c:otherwise>
					<td>[${doranQDto.doranQType}]</td>
				</c:otherwise>
			</c:choose>
		
			<td align ="left">
			<c:forEach var="i" begin="1" end="${doranQDto.doranQDepth}" step="1">
				&nbsp;&nbsp;
			</c:forEach>
			<a href="detail?doranQNo=${doranQDto.doranQNo}">${doranQDto.doranQTitle}</a>
			</td>
			
			<c:choose>
				<c:when test="${doranQDto.doranQMemNo==0}">
					<td></td>
				</c:when>
				<c:otherwise>
					<td>${doranQDto.doranQMemNo}</td>
				</c:otherwise>
			</c:choose>
			
			
			<td>${doranQDto.doranQWritetime}</td>
			
<%-- 			<c:choose> --%>
<%-- 				<c:when test="${doranQDto.doranQMemNo==0}"> --%>
<!-- 					<td></td> -->
<%-- 				</c:when> --%>
<%-- 				<c:otherwise> --%>
<%-- 					<td>${doranQDto.doranQProcessing}</td> --%>
<%-- 				</c:otherwise> --%>
<%-- 			</c:choose> --%>
			
			
<%-- 			<td>${doranQDto.doranQGroup}</td> --%>
<%-- 			<td>${doranQDto.doranQParent}</td> --%>
<%-- 			<td>${doranQDto.doranQDepth}</td> --%>
		</tr>
		</c:forEach>
	</tbody>
</table>

        <!--페이지 네비게이터  -->
    
    <div class="row center">
        <ul class="pagination on">
	        <c:choose>
	            <c:when test="${not doranQListSearchVo.isFirst()}">
	                <li><a href="list?p=${doranQListSearchVo.firstBlock()}">&laquo;</a></li> 
	            </c:when>
	            <c:otherwise>
	                <li><a href="#">&laquo;</a></li> 
	            </c:otherwise>
	        </c:choose>
	     
        
        <!-- 이전을 누르면 이전 구간의 마지막 페이지로 안내 -->
        <c:choose>
            <c:when test="${doranQListSearchVo.hasPrev()}">
                <li><a href="list?p=${doranQListSearchVo.prevBlock()}">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">&lt;</a></li> 
            </c:otherwise>
        </c:choose>
        
        <c:forEach var="i" begin="${doranQListSearchVo.startBlock()}" end="${doranQListSearchVo.endBlock()}" step="1">
            <li><a href="list?p=${i}">${i}</a></li>
        </c:forEach>
            
        <!-- 다음을 누르면 다음 구간의 첫 페이지로 안내 -->
        <c:choose>
            <c:when test="${doranQListSearchVo.hasNext()}">
                <li><a href="list?p=${doranQListSearchVo.nextBlock()}">&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">&gt;</a></li> 
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not doranQListSearchVo.isLast()}">
                <li><a href="list?p=${doranQListSearchVo.lastBlock()}">&raquo;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">&raquo;</a></li> 
            </c:otherwise>
        </c:choose>
            
       </ul>

    </div>
</div>
<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>