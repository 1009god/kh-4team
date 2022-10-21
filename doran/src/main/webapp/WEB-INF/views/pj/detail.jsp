<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
</head>
<body>


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
               		<td>${PjDto.pjLikesNumber}</td>
               </tr>
            </tbody>
        </table>

        <c:forEach var="OptionsDto" items="${OptionsDto}">
            <span><a href="selectCheck?optionsNo=${OptionsDto.optionsNo}">${OptionsDto.optionsName}</a></span>
            <span>${OptionsDto.optionsPrice}</span>
            <span>${OptionsDto.optionsStock}</span>
        </c:forEach>
    </div>

</body>
</html>