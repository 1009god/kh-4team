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

</style>

		
<div class="container-1400">

	<div class="container-1400" >
		
		<div class="proinline">		
			 <!-- 반복문 -->
			<c:forEach var="vo" items="${profileImg}" varStatus="status">	
				<c:if test="${status.last}">			
					<img width="80px" height="80px" src="http://localhost:8888/files/download/${vo.profileImgFileNo}" id="proimg">
				</c:if>
			</c:forEach>
		</div>
		<div class="proinline">					
			<span style="padding-left:20px">${memDto.memNo} ${memDto.memNick} </span><a href="/edit/profile"><i class="fa-solid fa-gear"></i></a>				
			<div style="padding-left:20px">${memDto.memJoinDate} 회원 가입일</div>			
		</div>	
		
		
	</div>
	
	<ul class="mypage_menu">                             
     	 <li><a href="/mypage/created">올린 프로젝트</a></li>
         <li><a href="/mypage/supported">후원한 프로젝트</a></li>
    </ul> 
    
</div>

     <hr style="border:1px color= silver;" width="100%">          
                       
<div>
</div>	





만든 프로젝트






<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>




