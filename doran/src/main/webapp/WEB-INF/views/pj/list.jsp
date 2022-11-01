<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>

   	<!-- 로딩바 라이브러리 -->
	<script src="https://cdn.jsdelivr.net/gh/hiphop5782/progress-bar@latest/dist/progress-bar.min.js"></script>
	
    <script type="text/javascript">
    
    $(function(){
    	
    	$(".loading-bar").progressbar({
    	
    	});
    });
        

    </script>
    
    
    <style>
    
    .pjImg{
    	max-width: 100%;
    }

    .a{
   		transition : all 0.2s linear;
  		width : 372px;  
    	margin : 0px auto;
     	overflow : hidden; 
    	
    }
    .a:hover{
    	transform : scale(1.05);
    }
    
    .list{
     	display : flex; 
      	justify-content: flex-start;  
     	flex-wrap: wrap; 
    }
     .item{ 
     	padding : 13px;
     } 
    
     .progressbar{
     	background : lightgray;
     }
     
     .white{
     	background : white;
     	border : 2px white;
     	font-size : 20px;
     	font-weight : bold;
     	padding : 5px;
     }
     .white:hover{
     	color : #0072b2;
     }
    
    </style>

    <div class="container-1200">
   
    <div class="row center mt-30 mb-30">
        <form action = "list" method ="get">
        	<button class="white ms-30 me-30" name="sort" value="prelaunching">펀딩예정</button>
            <button class="white ms-30 me-30" name="sort" value="ongoing">펀딩중</button>
            <button class="white ms-30 me-30" name="sort" value="finishing">펀딩마감</button>
        </form>
    </div>   
    <hr>
    
    
    <div class="row right">
        <form action ="list" method = "get">
        
        <select name="sort" onchange="this.form.submit()"> <!-- 추후 js.43번 참고해서 하기 -->
            <option value=>정렬</option>
            <option value="pj_no" <c:if test="${pjListSearchVo.sort=='pj_no'}">selected</c:if>>최신순</option>
            <option value="pj_likes_number" <c:if test="${pjListSearchVo.sort=='pj_likes_number'}">selected</c:if>>인기순</option>
            <option value="pj_funding_end_date-sysdate" <c:if test="${pjListSearchVo.sort=='pj_funding_end_date-sysdate'}">selected</c:if>>마감임박순</option>
        </select> 
        </form>
    </div>   
    
    <div class="row center mt-20">
        <form action = "list" method ="get">
        
        	<button class="white ms-10 me-10" name="" value="">
        	<i class="fa-sharp fa-solid fa-border-all"></i> 전체</button>
        	<i class="fa-solid fa-fork-knife"></i>
            <button class="white ms-10 me-10" name="category" value="패션/잡화">
            <i class="fa-solid fa-shirt"></i> 패션/잡화</button>
            <button class="white ms-10 me-10" name="category" value="뷰티">
          	<i class="fa-solid fa-face-smile"></i> 뷰티</button>
            <button class="white ms-10 me-10" name="category" value="푸드">
            <i class="fa-solid fa-utensils"></i> 푸드</button>
            <button class="white ms-10 me-10" name="category" value="홈/리빙">
            <i class="fa-solid fa-house"></i> 홈/리빙</button>
            <button class="white ms-10 me-10" name="category" value="테크/가전">
            <i class="fa-solid fa-laptop"></i> 테크/가전</button>
            <button class="white ms-10 me-10" name="category" value="기타">
            <i class="fa-solid fa-guitar"></i> 기타</button>

        </form>
    </div>
    
        <div class="row center list" >
	            <c:forEach var="pjDto" items="${list}">
	            
	            	<div class="row item">
	            			<div class="a">
<!-- 	            			<img class="pjImg" src="/img/test.jpg" > -->
							<c:set var = pj_file_pj_no value="${PjFilePjNo}">
								<c:if test="pj_file_pj_no==${pjDto.pjNo}"></c:if>
		            			<c:forEach var="PjFileList" items="${PjFileList}">
			           		 		<img class="pjImg" width = "372px" src="http://localhost:8888/files/download/${PjFileList.pjFileNo}" >
		            			</c:forEach>
	            			</c:set>
	            			</div>
	            			
		                <div class="row">
		                	<span> ${pjDto.pjNo}</span>
			                <a href="list?category=${pjDto.pjCategory}">[${pjDto.pjCategory}]</a>
			                <span> ♥ ${pjDto.pjLikesNumber}</span>
		                </div>
		                
		                <div class="row">
			                <a href="detail?pjNo=${pjDto.pjNo}">
			                ${pjDto.pjName}</a>
                                	
		                </div>
		                <div class="left">${pjDto.achievementRate}% ${pjDto.nvl}원 
		                </div> 
                        <div 
                        class="row progressbar loading-bar"
                        data-value = "${pjDto.achievementRate}%"
                        data-color = #0072b2>  
                        </div>
                     </div>
	       		</c:forEach>
        </div>

        
        <!--페이지 네비게이터  -->
    
    <div class="row center">
        <ul class="pagination on">
	        <c:choose>
	            <c:when test="${not pjListSearchVo.isFirst()}">
	                <li><a href="list?p=${pjListSearchVo.firstBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&laquo;</a></li> 
	            </c:when>
	            <c:otherwise>
	                <li><a href="#">&laquo;</a></li> 
	            </c:otherwise>
	        </c:choose>
	     
        
        <!-- 이전을 누르면 이전 구간의 마지막 페이지로 안내 -->
        <c:choose>
            <c:when test="${pjListSearchVo.hasPrev()}">
                <li><a href="list?p=${pjListSearchVo.prevBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">&lt;</a></li> 
            </c:otherwise>
        </c:choose>
        
        <c:forEach var="i" begin="${pjListSearchVo.startBlock()}" end="${pjListSearchVo.endBlock()}" step="1">
            <li><a href="list?p=${i}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">${i}</a></li>
        </c:forEach>
            
        <!-- 다음을 누르면 다음 구간의 첫 페이지로 안내 -->
        <c:choose>
            <c:when test="${pjListSearchVo.hasNext()}">
                <li><a href="list?p=${pjListSearchVo.nextBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">&gt;</a></li> 
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not pjListSearchVo.isLast()}">
                <li><a href="list?p=${pjListSearchVo.lastBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&raquo;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">&raquo;</a></li> 
            </c:otherwise>
        </c:choose>
            
       </ul>

    </div>

</div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>



