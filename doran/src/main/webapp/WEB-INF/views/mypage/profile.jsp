<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    





<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="프로필" name="title"/>
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
	float: left;
	
}

.container-1200 {
	padding: 20px;
}

.profileBox {
	padding:20px;
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
						<img width="80px" height="80px" src="${pageContext.request.contextPath}/img/NonProfile.png" id="proimg">
					</c:when>
			
					<c:otherwise>
				 <!-- 반복문 -->
				<c:forEach var="vo" items="${profileImg}" varStatus="status">	
					<c:if test="${status.last}">			
						<img width="80px" height="80px" src="${pageContext.request.contextPath}/files/download/${vo.profileImgFileNo}" id="proimg">
					</c:if>
				</c:forEach>			
			</c:otherwise>
		</c:choose>						    
		    </td>
		    <td class="">
		    	<span style="padding-left:20px">${memDto.memNick} </span><a href="${pageContext.request.contextPath}/edit/profile"><i class="fa-solid fa-gear"></i></a>
		    
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
	</div>
	
	
	<div class= "container-1400">
		<ul class="mypage_menu">                             
	     	 <li><a href="${pageContext.request.contextPath}/mypage/created">올린 프로젝트</a></li>
	         <li><a href="${pageContext.request.contextPath}/mypage/supported">후원한 프로젝트</a></li>	         
	    </ul> 
    </div>
    


     <hr style="border:1px color= silver;" width="1400px">          
                    
<div>
</div>	
	
	
		



<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>

