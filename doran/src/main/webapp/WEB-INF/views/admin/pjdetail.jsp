<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="/WEB-INF/views/template/adminheader.jsp">
	<jsp:param value="${pjDto.pjNo}프로젝트 상세" name="title"/>
</jsp:include>
	
	 <div>
        <table>
            <tbody>
                <tr>
                    <th>프로젝트 번호</th>
                    <td>${pjDto.pjNo}</td>
                </tr>
                <tr>
                    <th>프로젝트 이름</th>
                    <td>${pjDto.pjName}</td>
                </tr>

            </tbody>
        </table>

        <c:forEach var="OptionsDto" items="${OptionsDto}">
            <span><a href="selectCheck?optionsNo=${OptionsDto.optionsNo}">${OptionsDto.optionsName}</a></span>
            <span>${OptionsDto.optionsPrice}</span>
            <span>${OptionsDto.optionsStock}</span>
        </c:forEach>
    </div>





<jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>