<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>

    <script src = "https://code.jquery.com/jquery-3.6.1.js"></script>
   
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
     	padding : 5px;
     }
     .white:hover{
     	color : #0072b2;
     }
     .notOngoing{
     	width : 700px;
     	height : 500px;
     	margin : 0 auto;


     }
     .funding{
    	 font-weight : bold; color : gray;
     }
     
     .category{
     	text-decoration : none;
     	color : gray;
     }
     .name{
        text-decoration : none;
     	color : black;
     }
    </style>    

       <div class="container-1200">
     

        <div class="row center list" >
	            <c:forEach var="pjDto" items="${list}" varStatus="status">
	            	<div class="row item">
	            			<div class="a">
 		           				<img width="372px" height="210px" src="http://localhost:8888/files/download/${pjDto.pjFileNo}">
	            			</div>
		                <div class="row">
<%-- 		                	<span> ${pjDto.pjNo}</span> --%>

			                <a class="category" href="list?category=${pjDto.pjCategory}">[${pjDto.pjCategory}]</a>

			               <span> <a href=#><i class="fa-solid fa-heart" style="color:#0072b2"></i></a>  ${pjDto.pjLikesNumber}</span>
		                </div>
		                
		                <div class="row">

			                <a class="name"href="pj/detail?pjNo=${pjDto.pjNo}">
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
	</div>     

      
   


        
        <!--페이지 네비게이터  -->
 	<c:choose>
 		<c:when test="${not empty list}">
		    <div class="row center">
		        <ul class="pagination on">
			        <c:choose>
			            <c:when test="${not pjListSearchVo.isFirst()}">
			                <li><a href="pj/list?p=${pjListSearchVo.firstBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&laquo;</a></li> 
			            </c:when>
			            <c:otherwise>
			                <li><a href="#">&laquo;</a></li> 
			            </c:otherwise>
			        </c:choose>
			     
		        
		        <!-- 이전을 누르면 이전 구간의 마지막 페이지로 안내 -->
		        <c:choose>
		            <c:when test="${pjListSearchVo.hasPrev()}">
			                <li><a href="pj/list?p=${pjListSearchVo.prevBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&lt;</a></li>
			            </c:when>
			            <c:otherwise>
			                <li><a href="#">&lt;</a></li> 
			            </c:otherwise>
			        </c:choose>
			        
			        <c:forEach var="i" begin="${pjListSearchVo.startBlock()}" end="${pjListSearchVo.endBlock()}" step="1">
			            <li><a href="pj/list?p=${i}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">${i}</a></li>
			        </c:forEach>
			            
			        <!-- 다음을 누르면 다음 구간의 첫 페이지로 안내 -->
			        <c:choose>
			            <c:when test="${pjListSearchVo.hasNext()}">
			                <li><a href="pj/list?p=${pjListSearchVo.nextBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&gt;</a></li>
			            </c:when>
			            <c:otherwise>
			                <li><a href="#">&gt;</a></li> 
			            </c:otherwise>
			        </c:choose>
			        
			        <c:choose>
			            <c:when test="${not pjListSearchVo.isLast()}">
			                <li><a href="pj/list?p=${pjListSearchVo.lastBlock()}&${pjListSearchVo.parameter()}&${pjListSearchVo.sortBy()}&${pjListSearchVo.categoryBy()}">&raquo;</a></li>
			            </c:when>
			            <c:otherwise>
			                <li><a href="#">&raquo;</a></li> 
			            </c:otherwise>
			        </c:choose>
			            
			       </ul>
			
			    </div>
	    </c:when>
	    <c:otherwise>
	    	<div class="notOngoing" >
	    		<div class="row center" style = "padding : 80px">
		    		<h1 style = "color : #6495ED">진행중인 펀딩이 없습니다</h1>
		    	</div>
		    	<div class="row center" >
		    		<a href="${pageContext.request.contextPath}" style="text-decoration : none; color : #0072b2;"><h2>
		    		<i class="fa-solid fa-house"></i> 홈으로</h2></a>
	    		</div>
	    	</div>
	    </c:otherwise>
	</c:choose> 
</div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>


