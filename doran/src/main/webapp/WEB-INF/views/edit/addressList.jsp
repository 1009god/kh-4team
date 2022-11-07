<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="배송지 목록" name="title"/>
</jsp:include>



<style>

.border{
padding: 20px;
}

.container-800 {
margin : 30px
}

.btn-round {
  color: white; 
  text-align: center;
  background: lightgrey;
  border: solid 1px lightgrey;
  border-radius: 50px;  
  
  }
  
 .atag {
   text-decoration: none;
   color:white;
   }
   
   .box {
   margin: 10px;
   padding: 10px;   
   }   
   
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


</style>


<div class= "container-1400" style="padding-left:10px;">
	<h1>설정</h1>
</div>
 
 
 <div class= "container-1400">
		<ul class="mypage_menu">                             
	     	 <li><a href="/edit/profile">프로필</a></li>
	         <li><a href="/edit/account">계정</a></li>	    
	         <li><a href="/edit/address_list">배송지</a></li>    
	    </ul> 
   </div>
 
 
 
  
 <hr style="border:1px color= silver;" width="1400px">

<div class="container-1400">

<div class="container-800 left" >
	
	<div width="50%">
	
		<div class="row"><h3>등록된 배송지</h3></div>
		<div class="row">
			<button class="btn-round"><a class="atag" href="${pageContext.request.contextPath}/edit/address_plus">배송지 추가</a></button>
		</div>
		
		</div>
		<div class="container-800 left box" width = "50%">
		<c:forEach var= "dto" items = "${list}"> 
		<!-- 컨트롤러에서 넘어온 list -->
		
		<div class="border">
		<button class="btn-round"><a class="atag" href="address_delete?addressNo=${dto.addressNo}">삭제</a></button>
		<button class="btn-round"><a class="atag" href="address_change?addressNo=${dto.addressNo}">수정</a></button>
		<!--  ${dto.addressNo}-->
			<div>
				${dto.addressName}
			</div>
			<div>
				${dto.addressPost}
				${dto.addressBasic}
				${dto.addressDetail}
			</div>
			<div>
				${dto.addressTel}
			</div>
		</div>
		
		</c:forEach>
		
		</div>
		
	</div>
</div>

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
