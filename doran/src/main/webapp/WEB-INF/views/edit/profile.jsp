<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="프로필 수정" name="title"/>
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




</style>




<div class="container-1400">

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
 
 
 
  
 <hr style="border:1px color= silver;" width="100%">
 
 
 <form method="post" enctype="multipart/form-data">
 <!-- 이미지 첨부파일 -->
 
 <table>
<thead>
  <tr>
    <th>프로필 사진</th>
    <th> <input type="file" name= "files" > </th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>닉네임</td>
    <td><input type="text" name="memNick" value="${memDto.memNick}"  autocomplete="off"></td>
     <input type="hidden" name="memNo" value="${memDto.memNo}" > 
  </tr>
 
</tbody>
</table>
 
 <div class="left">
	
		<button type="submit" >수정</button>
	
	</div>
 </form>
  
 

<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
