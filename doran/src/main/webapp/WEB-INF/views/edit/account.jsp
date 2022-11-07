<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="계정 정보 변경" name="title"/>
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

.btn-round {
  color: white; 
  text-align: center;
  background: lightgrey;
  border: solid 1px lightgrey;
  border-radius: 50px;    
  }


</style>




<div class= "container-1400" style="padding-left:10px;">
	<h1>설정</h1>
</div>
 
 
 <div class= "container-1400">
		<ul class="mypage_menu">                             
	     	 <li><a href="${pageContext.request.contextPath}/edit/profile">프로필</a></li>
	         <li><a href="${pageContext.request.contextPath}/edit/account">계정</a></li>	    
	         <li><a href="${pageContext.request.contextPath}/edit/address_list">배송지</a></li>    
	    </ul> 
   </div>
 
 
 
  
 <hr style="border:1px color= silver;" width="1400px">


	<div class="container-1400" style="height:400px">
		
	<input type="hidden" name="memNo" value="${memDto.memNo}" > 
		
		<table>
			<thead>
			  <tr>
			    <td>비밀번호</td>		
			    <td></td>	    
			    <td> <button class="btn-round"  type="button" ><a href="${pageContext.request.contextPath}/edit/account_change_pw">수정</a></button></td>
			  </tr>
			</thead>
			<tbody>
			  <tr>
			  <form method="post">
			    <td>전화번호</td>
			    <td><input type="text" name="memTel" value="${memDto.memTel}" required autocomplete="off"  class="input"></td>
			    <td><button  class="btn-round"  type="submit" >수정</button>	</td>
			    </form>
			  </tr>
			  <tr>
			    <td>회원탈퇴</td>
			    <td></td>
			    <td><button class="btn-round" type="button" ><a href="${pageContext.request.contextPath}/mypage/goodbye_content">탈퇴</a></button></td>
			  </tr>
			</tbody>
		</table>
		
	
	</div>
	
	





<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>