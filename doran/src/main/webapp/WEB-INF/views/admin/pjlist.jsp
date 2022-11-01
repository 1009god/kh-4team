<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="프로젝트 목록" name="title"/>
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



<div class="container-1000 container-900 mt-40 mb40">
	<!-- 제목 -->
	<div class="row">
		<h3>프로젝트 리스트</h3>
	</div>
	
	<!-- 목록 출력 -->
	<div class="row center">
		<table class="table table-hover" border="1" >
			<thead>
				<tr>
					<th width="15%">판매자</th>
					<th width="10%">카테고리</th>
					<th>프로젝트명</th>
					<th width="10%">목표금액</th>
					<th width="20%">시작일</th>
					
					
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}">
				<tr align="center">
			    	<td>${dto.memNick}</td> 
					
					<td>${dto.pjCategory}</td>
					
					<td><a href="pjdetail?pjNo=${dto.pjNo}">
							${dto.pjName}
						</a></td>	
					
					<td>${dto.pjTargetMoney}</td>

					<td>${dto.pjFundingStartDate}</td>
					
					
					
					
			     	</tr>
				</c:forEach>
			</tbody>
			
			
			
		</table>
	</div>

 <!--페이지 네비게이터  -->
    <div class="row center">
        <ul class="pagination on">
	        <c:choose>
	            <c:when test="${not pjListSearchVo.isFirst()}">
	                <li><a href="pjlist?p=${pjListSearchVo.firstBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&laquo;</a></li> 
	            </c:when>
	            <c:otherwise>
	                <li><a href="#">&laquo;</a></li> 
	            </c:otherwise>
	        </c:choose>
	     
        
        <!-- 이전을 누르면 이전 구간의 마지막 페이지로 안내 -->
        <c:choose>
            <c:when test="${pjListSearchVo.hasPrev()}">
                <li><a href="pjlist?p=${pjListSearchVo.prevBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">&lt;</a></li> 
            </c:otherwise>
        </c:choose>
        
        <c:forEach var="i" begin="${pjListSearchVo.startBlock()}" end="${pjListSearchVo.endBlock()}" step="1">
            <li><a href="pjlist?p=${i}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">${i}</a></li>
        </c:forEach>
            
        <!-- 다음을 누르면 다음 구간의 첫 페이지로 안내 -->
        <c:choose>
            <c:when test="${pjListSearchVo.hasNext()}">
                <li><a href="pjlist?p=${pjListSearchVo.nextBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">&gt;</a></li> 
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not pjListSearchVo.isLast()}">
                <li><a href="pjlist?p=${pjListSearchVo.lastBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&raquo;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">&raquo;</a></li> 
            </c:otherwise>
        </c:choose>
            
       </ul>

    </div>
    
<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>