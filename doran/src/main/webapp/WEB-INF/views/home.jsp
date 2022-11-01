<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>

     <!-- 로딩바 라이브러리 -->
<!--     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@loadingio/loading-bar@0.1.1/dist/loading-bar.css"> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/@loadingio/loading-bar@0.1.1/dist/loading-bar.min.js"></script> -->
    

    <!--jquery를 사용하기 위하여 라이브러리 js파일을 불러온다-->
    <script src = "https://code.jquery.com/jquery-3.6.1.js"></script>
   
   	<!-- 로딩바 라이브러리 -->
	<script src="https://cdn.jsdelivr.net/gh/hiphop5782/progress-bar@latest/dist/progress-bar.min.js"></script>

    <script type="text/javascript">
    
    $(function(){
    	
    	const swiper = new Swiper('.swiper', {
            // Optional parameters
            direction: 'horizontal', //슬라이드 방향
            loop: true, //반복여부

				autoplay:{
                delay:1000, //자동재생 간격(ms)
            },

			effect:"slide", //슬라이드 기본 효과
    	
    	$("#loading-bar").progressbar({
    	
    	});
    	
// 		var list = document.querySelectorAll("#loading-bar");
// 		for(var i=0; i < list.length; i++){
// 				var bar = new ldBar(list[i]);
        
    });
    </script>
    
    
    <style>
    
    .pjImg{
    	max-width: 0%;
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
     	justify-content: space-between; 
     	flex-wrap: wrap; 
    }
    .item{
     	width : "33%"; 
    }
    .inner{
    	height : 3px;
    	width:100%;
/*         position: absolute; */
        top:0;
        left:0;
        bottom:0;
/*  		background: #0072b2;  */
            }
     .progressbar{
     	background : lightgray;
     }
    .swiper-button-prev{
    	color : gray;
    }
    .swiper-button-next{
    	color : gray;
    }
    </style>


    <div class="container-1300">

		<!-- Slider main container -->
			<div class="swiper w-50">
			  <!-- Additional required wrapper -->
			  <div class="swiper-wrapper">
			    <!-- Slides -->
			    <div class="swiper-slide"><img src="/img/burger.png"></div>
			    <div class="swiper-slide"><img src="/img/test.jpg"></div>
			    <div class="swiper-slide"><img src="/img/test1.png"></div>
			    ...
			  </div>
			  <!-- If we need pagination -->
			  <div class="swiper-pagination"></div>

			  <!-- If we need navigation buttons -->
			  <div class="swiper-button-prev"></div>
			  <div class="swiper-button-next"></div>

			  <!-- If we need scrollbar -->
			  <div class="swiper-scrollbar"></div>
			</div>
	</div>     

    <div class="row center">

    
        <div class="row center list">
	            <c:forEach var="pjDto" items="${list}">
	            
	            	<div class="row item">
	            			<div class="a">
		           		 		<img class="pjImg" src="/img/test.jpg" >
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
                        <div class="row progressbar" id="loading-bar" 
                        data-value = "${pjDto.achievementRate}"
                        data-color = #0072b2>  

                        </div>
                     </div>
	       		</c:forEach>
        </div>
</div>
      
   


<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>

