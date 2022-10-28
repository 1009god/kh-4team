<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
    
    



</script>
<style>
    .selectOption {
        border: 1px dotted gray;
    }
</style>
</head>
<body>


    <div>
        <div>
        <table>
            <tbody>
                <tr>
                    <th>프로젝트 번호</th>
                    <td>${PjDto.pjNo}</td>
                </tr>
                <tr>
                    <th>프로젝트 이름</th>
                    <td>${PjDto.pjName}</td>
                </tr>
               <tr>
               		<th>좋아요</th>
               		<td>
               		${PjDto.pjLikesNumber}
               		
	               		<c:if test="${check==true}">
	               		<button><a href="like?pjNo=${PjDto.pjNo}">좋아요 취소하기</a></button>
	               		</c:if>
	               		<c:if test="${check==false}">
	               		<button><a href="like?pjNo=${PjDto.pjNo}">좋아요 하기</a></button>
	               		</c:if>
               		
               		</td>
               </tr>
            </tbody>
        </table>
    </div>

    <c:forEach var="OptionsDto" items="${OptionsDto}">
        

    <c:set var="currentStock" value="${OptionsDto.optionsStock}" />
    <c:set var="orderCount" value="${OrderCount}" />
    

    <c:choose>
        <c:when test="${OrderCount==1}">
            <div class="selectOption"  onclick="alert('이미 후원한 프로젝트입니다. 추가로 후원할 수 없습니다');">
                <span class="no">${OptionsDto.optionsNo}</span>
                <span class="name">${OptionsDto.optionsName}</span>
                <span class="price">${OptionsDto.optionsPrice}</span>
                <span class="stock">${OptionsDto.optionsStock}</span>
            </div>
        </c:when>

        <c:when test="${currentStock==0}">
            <div class="selectOption"  onclick="alert('품절');">
                <span class="no">${OptionsDto.optionsNo}</span>
                <span class="name">${OptionsDto.optionsName}</span>
                <span class="price">${OptionsDto.optionsPrice}</span>
                <span class="stock">${OptionsDto.optionsStock}</span>
            </div>
        </c:when>

        <c:when test="${OrderCount==0&&currentStock!=0}">
            <div class="selectOption"  onclick="location.href='selectCheck?optionsNo=${OptionsDto.optionsNo}';">
                <span class="no">${OptionsDto.optionsNo}</span>
                <span class="name">${OptionsDto.optionsName}</span>
                <span class="price">${OptionsDto.optionsPrice}</span>
                <span class="stock">${OptionsDto.optionsStock}</span>
            </div>
        </c:when>
    </c:choose>

    </c:forEach>


    </div>

</body>
</html>