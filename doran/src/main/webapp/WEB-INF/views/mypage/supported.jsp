<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    





<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
</jsp:include>

<style>

.mypage_menu > li {
	float: left;
	line-height: 3em;             
	padding-left: 20px;
	 list-style: none;	 
}

.mypage_menu > li >a {
	color: black;
	display: block;
	text-align: center;
	text-decoration: none;
}

.mypage_menu > li > a:hover {
	 color: #0072b2;
}

#proimg {
	border-radius: 100%;
}

.proinline {
	display: inline-block;
}

.container-1200 {
	padding: 20px;
}

.profileBox {
	padding:20px;
}

.tm {
	margin: 30px;
}




</style>






	
<div class="container-1400">

	<div class="container-1400" >
			
		<table class="profileBox">
		<thead>
		  <tr>
		    <td class="" rowspan="2">
				<c:choose>
			<c:when test="${empty profileImg}">
				<img width="80px" height="80px" src="/img/NonProfile.png" id="proimg">
			</c:when>
			
			<c:otherwise>
				 <!-- 반복문 -->
				<c:forEach var="vo" items="${profileImg}" varStatus="status">	
					<c:if test="${status.last}">			
						<img width="80px" height="80px" src="http://localhost:8888/files/download/${vo.profileImgFileNo}" id="proimg">
					</c:if>
				</c:forEach>			
			</c:otherwise>
		</c:choose>						    
		    </td>
		    <td class="">
		    	<span style="padding-left:20px">${memDto.memNo} ${memDto.memNick} </span><a href="/edit/profile"><i class="fa-solid fa-gear"></i></a>
		    
		    </td>
		  </tr>
		  <tr>
		    <td class="">
		   		 <span style="padding-left:20px">${memDto.memJoinDate} 회원 가입일</span>		
		    </td>
		  </tr>
		</thead>
		</table>
	
		</div>
	
	<ul class="mypage_menu">                             
     	 <li><a href="/mypage/created">올린 프로젝트</a></li>
         <li><a href="/mypage/supported">후원한 프로젝트</a></li>
         <li><a href="#">나의 1:1 문의보기</a></li>
    </ul> 
    
</div>

            
     <hr style="border:1px color= silver;" width="1400px">     
                       
<div>

	<div>
		<c:forEach var="OrdersMemSearchDto" items="${OrdersMemSearchDto}">
        <div>
            <span>${OrdersMemSearchDto.ordersNo}</span>
            <span>${OrdersMemSearchDto.pjNo}</span>
            <span>${OrdersMemSearchDto.pjName}</span>
            <span>${OrdersMemSearchDto.optionsNo}</span>
            <span>${OrdersMemSearchDto.optionsName}</span>
            <span>${OrdersMemSearchDto.optionsPrice}</span>
            <span>${OrdersMemSearchDto.optionsDeliveryPrice}</span>
        </div>
    </c:forEach>
	</div>

</div>	
            
            <c:choose>
           		<c:when test="${empty supportList}">
	           		<div class="container-1000 center" >
	           			<img src="/img/nopj.png" width="1000px" height="800px">
	           		</div>
           		</c:when>
          		
          		<c:otherwise>
          		
	<!-- 후원 내역 list -->	
	<div class=container-800>
		<table class="table table-hover table-slit tm">
			<thead>
				<tr align="center">
					<th>주문 번호</th>
					<th>판매자</th>
					<th>카테고리</th>
					<th>프로젝트</th>												
				</tr>
			</thead>
			<tbody>
				<c:forEach var="supportVO" items="${supportList}" >	
					<tr align="center">								
							<td><a href="/mypage/supported/detail?ordersNo=${supportVO.ordersNo}">${supportVO.ordersNo}</a></td> <!-- 주문번호 -->
							<td>${supportVO.memNick}</td>			
							<td>${supportVO.pjCategory}</td>			
							<td>${supportVO.pjName}</td>							
						</tr>							
				</c:forEach>
			</tbody>
		</table>	

		</div>	
          		</c:otherwise>
           
           </c:choose>            

	



<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
