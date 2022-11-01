<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    





<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="내가 올린 프로젝트" name="title"/>
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

.aa {
color: #0072b2;
text-decoration: none;
}

.pick {
color: #0072b2;
}


</style>


<script>
    console.log(${myCreatedPjDto});
</script>




	
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
		    	<span style="padding-left:20px">${memDto.memNick} </span><a href="/edit/profile"><i class="fa-solid fa-gear"></i></a>
		    
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
     	 <li><a href="/mypage/created" ><p style="color:#0072b2; ">올린 프로젝트</p></a></li>
         <li><a href="/mypage/supported">후원한 프로젝트</a></li>         
    </ul> 
    
</div>

     <hr style="border:1px color= silver;" width="1400px">          
                       
<div>
</div>	
                       
          <c:choose>
           		<c:when test="${empty myCreatedPjDto}">
	           		<div class="container-1000 center" >
	           			<img src="/img/CutnoProject.png" width="1000px" height="575px">
	           		</div>
           		</c:when>
          		
          		<c:otherwise>  
                  
           
	
	<div class= "container-800" style="height:400px">
	
	<table class="table table-hover table-slit tm">
			<thead>
				<tr align="center">
					<th>프로젝트 번호</th>
					<th>카테고리</th>
					<th>프로젝트 제목</th>												
				</tr>
			</thead>
			<tbody>
				<c:forEach var="myCreatedPjDto" items="${myCreatedPjDto}" >	
					<tr align="center">								
							<td><a href="/mypage/created/detail?pjNo=${myCreatedPjDto.pjNo}" class="aa"'>${myCreatedPjDto.pjNo}</a></td>
							<td>${myCreatedPjDto.pjCategory}</td>
                            <td>${myCreatedPjDto.pjName}</td>						
						</tr>							
				</c:forEach>
			</tbody>
		</table>	
	
		</div>

           </c:otherwise>
           
           </c:choose>            
        
	

    </div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>