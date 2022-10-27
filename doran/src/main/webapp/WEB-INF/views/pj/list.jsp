<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 목록</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/css/commons.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/angular-loading-bar/0.9.0/loading-bar.css"/>
    

    <!--jquery를 사용하기 위하여 라이브러리 js파일을 불러온다-->
    <script src = "https://code.jquery.com/jquery-3.6.1.js"></script>
    <!-- 로딩바 라이브러리 -->
    <script type="text/javascript" src="loading-bar.js"></script>
    <script type="text/javascript">
    
    
    /* construct manually */
    var bar1 = new ldBar(.ldBar);
    /* ldBar stored in the element */
//     var bar2 = document.getElementById('myItem1').ldBar;
    bar1.set(60);

        

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
     	justify-content: space-between; 
     	flex-wrap: wrap; 
    }
    .item{
     	width : "33%"; 
    }
    .progressbar{
    	height : 3px;
        position: relative;
        background : lightgray;
            }
    .progressbar> .inner{
        position: absolute;
        top:0;
        left:0;
        bottom:0;
		background: #0072b2;
            }
    
    </style>

    
</head>
<body>
    <div class="container-1200">
	<div class="ldBar" data-value="50"></div>
        <div class="row">
            <h1>상품 목록</h1>
        </div>

    <div class="row right">
         <!-- 검색창 -->
        <form action = "list" method = "get">
            <select name="type">
                <option value="pj_name" <c:if test="${pjListSearchVo.type=='pj_name'}">selected</c:if>>프로젝트 이름</option>
                <option value="pj_category" <c:if test="${pjListSearchVo.type=='pj_category'}">selected</c:if>>프로젝트 카테고리</option>
            </select>
                
            <input type="search" name="keyword" placeholder="검색어" required value="${pjListSearchVo.keyword}">
            <button type="submit">검색</button>
        </form>
    </div>    

    <div class="row right">
        <form action ="list" method = "get">
        
        <select name="sort" onchange="this.form.submit()"> <!-- 추후 js.43번 참고해서 하기 -->
            <option value=>정렬</option>
            <option value="pj_no">최신순</option>
            <option value="pj_likes_number">인기순</option>
            <option value="pj_funding_end_date-sysdate">마감임박순</option>
        </select> 
        </form>
    </div>   
    <div class="row center">
        <form action = "list" method ="get">
        	<button name="" value="">전체</button>
            <button name="category" value="패션/잡화">패션/잡화</button>
            <button name="category" value="뷰티">뷰티</button>
            <button name="category" value="푸드">푸드</button>
            <button name="category" value="홈/리빙">홈/리빙</button>
            <button name="category" value="테크/가전">테크/가전</button>
            <button name="category" value="기타">기타</button>

        </form>
    </div>   
    

        
        <div class="row center list">
	            <c:forEach var="pjDto" items="${list}">
	            <c:forEach var="amountCalList" items="${amountCalList}">
	            
	            	<div class="row item">
	            			<div class="a">
		           		 		<img class="pjImg" src="/img/test.jpg" >
	            			</div>
	            			
		                <div class="row">
		                	<span>${pjDto.pjNo}</span>
			                <a href="list?category=${pjDto.pjCategory}">[${pjDto.pjCategory}]</a>
			                <span> ♥ ${pjDto.pjLikesNumber}</span>
		                </div>
		                
		                <div class="row">
			                <a href="detail?pjNo=${pjDto.pjNo}">
			                ${pjDto.pjName}</a>
		                </div>
                        <div class="left">
	                          <c:choose>
		                           <c:when test="${amountCalList.pjNo==pjDto.pjNo}">
		                               		 ${amountCalList.achievementRate}%	${amountCalList.priceTotal}원
		                           </c:when>
		                           <c:otherwise>
		                                0% 	0원
		                           </c:otherwise>
	                          </c:choose>
	                   </div>
	                            
		                <div class="row">
                            <div class="progressbar ">
                                <div class="inner"></div>
                                <div>
<%--                                  <input type="number" name="percent" class="percent input" min="0" max="100" value="${amountCalList.achievementRate}"> --%>
                                </div>
                            </div>
                       </div>
	       			</div>
	       			
	       			</c:forEach>
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
</body>
</html>


