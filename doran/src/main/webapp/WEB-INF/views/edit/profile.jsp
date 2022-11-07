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



#proimg {
	border-radius: 100%;
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
 
 
 
  
 <hr style="border:1px color= silver;" width="1400px">
 
 <div class="container-1400" style="height:400px;">
 
 <form method="post" enctype="multipart/form-data"  style="margin:30px">
 <!-- 이미지 첨부파일 -->
 
 <table>
<thead>
  <tr>
  
  			<c:choose>
					<c:when test="${empty profileImg}">
						<img width="80px" height="80px" src="/img/NonProfile.png" id="proimg">
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
    
    <td style="
    padding-top: 20px;
    padding-bottom: 20px;">
프로필 사진</td>
    <th> <input type="file" name= "files" > </th>

  </tr>
</thead>
<tbody>
  <tr>  
    <td>닉네임</td>
    <td><input type="text" name="memNick" value="${memDto.memNick}"  autocomplete="off" class="input input-underline" ></td>
     <input type="hidden" name="memNo" value="${memDto.memNo}" > 
  </tr>
 
</tbody>
</table>
 
 <div class="left" style="margin-top: 20px;">
    <button type="submit" class="btn" >수정</button>
</div>
 </form>
  
 

  </div>
<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
