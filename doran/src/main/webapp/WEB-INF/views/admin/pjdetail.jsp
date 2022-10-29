<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<style>
	.shareParent {
	position:relative;
	}
	
	.shareChild{
	position:absolute;
	bottom:10px;
	}
</style>

<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<!-- 카톡js파일 -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
    
$(function(){
    $(".shareParent").on("click",function(){
        $(".shareChild").toggle();
    });


    
});


function shareTwitter() {
    var sendText = "도란도란에 구경 오세요!"; // 전달할 텍스트
    var pjNo=${PjDto.pjNo};
    var sendUrl = "http://localhost:8888/pj/detail?pjNo="+pjNo; // 전달할 URL
    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
}


function shareKakao() {
	
	 var pjNo=${PjDto.pjNo};
	 
	  // 사용할 앱의 JavaScript 키 설정
	  Kakao.init('98a6f123321ab217bf1325c675369f03');
	 
	  // 카카오링크 버튼 생성
	  Kakao.Link.createDefaultButton({
	    container: '#btnKakao', // 카카오공유버튼ID
	    objectType: 'feed',
	    content: {
	      title: "도란도란", // 보여질 제목
	      description: "도란도란에 구경 오세요!", // 보여질 설명
	      imageUrl: "http://localhost:8888/pj/detail?pjNo="+pjNo, // 콘텐츠 URL
	      link: {
	         mobileWebUrl: "http://localhost:8888/",
	         webUrl: "http://localhost:8888/"
	      }
	    }
	  });
	}




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

	
	<div class="shareParent">
	<button type="button">sns 공유 버튼</button>
	</div>
	
	<div class="shareChild">
	<a href="javascript:shareTwitter();">트위터에 공유</a>
	<a id="btnKakao" href="javascript:shareKakao();">카카오톡에 공유</a>
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