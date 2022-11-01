<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/adminheader.jsp"></jsp:include>


<!--swiper 의존성-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
<!--폰트어썸-->
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<style>
	.shareParent {
	position:relative;
	}
	
	.shareChild{
	position:absolute;
	bottom:10px;
	}

    .selectOption {
        border: 1px dotted gray;
    }


    .projectIntroduce {
        size: 14px;
        color:#000000DE;
        margin: 0px 0px 14px;
        text-align: left;
    }

    .projectValue {
        size: 38px;
        color:#000000DE;
        margin: 0px 10px 0px 0px;
        text-align: left;
    }

    .projectSmall {
        size: 14px;
        color:#000000DE;
        margin: 0px 0px 0px 3.5px;
        text-align: left;
    }

    .projectPercentage {
        size: 18px;
        color:#000000DE;
        text-align: left;
    }
    

    
</style>

<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<!-- 카톡js파일 -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js" integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
<script type="text/javascript">
    
$(function(){
    $(".shareParent").on("click",function(){
        $(".shareChild").toggle();
    });

    const datecountonjs=Math.ceil("${DateCount}");
    $(".endCount").text(datecountonjs+"일");


    const swiper = new Swiper('.swiper', {
            // Optional parameters
            direction: 'horizontal',//슬라이드 방향
            loop: true,//반복 여부

            // If we need pagination
            pagination: {//페이징 옵션
                el: '.swiper-pagination',//페이징 적용 대상
                type: "bullets",//페이징 도구 모양
                clickable:true,//클릭 가능 여부
            },

            // Navigation arrows 좌우 이동 버튼
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },

            //자동 재생 옵션
            autoplay: {
                delay:1000,//자동재생 간격 1000밀리초
            },

            //페이지 전환 효과
            //effect:"slide",//슬라이드 방식(기본)
            //effect: "fade", //페이드인-아웃 효과
            //effect: "cube",//큐브 회전 효과
            //effect:"coverflow",//3d 회전 효과
            //effect:"flip",//플립 효과
            effect:"cards",//카드 전환 효과


            // And if we need scrollbar
            // scrollbar: {
            //     el: '.swiper-scrollbar',
            // },
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



    <div class="container-1100">

    <div class="center">
        <button class="btn btn-neutral" onclick="location.href='list?category=${PjDto.pjCategory}'">${PjDto.pjCategory}</button>
    </div>

  
        <h3>${PjDto.pjSummary}</h2>
        <h1>${PjDto.pjName}</h1>
    
        
    <div class="center" style="margin:0px 100px;"><!--프로젝트 대표이미지, 정보 들어가는 자리(float써야됨 구와아악)-->
    
        <div style="width:700px; border:1px dotted gray;float:left;">
            <div class="swiper">
                <c:forEach var="PjFileList" items="${PjFileList}">
                    <img width="594px" height="445px" src="http://localhost:8888/files/download/${PjFileList.pjFileNo}">
                </c:forEach>
                <!-- If we need pagination -->
            <div class="swiper-pagination"></div>
        
            <!-- If we need navigation buttons -->
            <div class="swiper-button-prev"></div>
            <div class="swiper-button-next"></div>
            </div>
        </div>
        
        <aside style="width:360px; border:1px dotted red;float:left;">
            <div class="projectIntroduce">
                <span class>모인 금액</span>
            </div>
            <c:choose>
                <c:when test="${OrdersCalVO.priceTotal==null}">
                    <div>
                        <span class="projectValue">0</span><span class="projectSmall">원</span><span class="projectPercentage">0%</span>
                </div>
                </c:when>
                <c:when test="${OrdersCalVO.priceTotal!=null}">
                    <div>
                        <span class="projectValue">${OrdersCalVO.priceTotal}</span><span class="projectSmall">원</span><span class="projectPercentage">${OrdersCalVO.achievementRate} %</span>
                    </div>   
                </c:when>
            </c:choose>
            <div>
                <span>남은 시간</span>
            </div>
            <div>
                <c:set var="dateCount" value="${DateCount}" />
                <c:if test="${dateCount>0}">
                        <span class="endCount"></span>
                </c:if>    
                <c:if test="${dateCount<=0}">
                    <span>마감된 프로젝트입니다</span>
                </c:if>
            </div>
            <div>
                <span>후원자</span>
            </div>
            <div>
                <span>${OrderCountAll}</span><span>명</span>
            </div>

            <div>
                <hr>
            </div>
            <div>
                <span>목표금액</span><span>${PjDto.pjTargetMoney}원</span>
            </div>
            <div>
                <span>펀딩기간</span><span>${PjDto.pjFundingStartDate} ~ ${PjDto.pjFundingEndDate}</span><span></span>
            </div>

         
        </aside>
    
    </div>

    <div class="float-container"></div>

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
                    <th>모인 금액</th>
                    <td>${OrdersCalVO.priceTotal} 원 ${OrdersCalVO.achievementRate} %</td>
                </tr>
                <tr>
                    <c:set var="OrderCountAll" value="${OrderCountAll}" />
                    <th>후원자</th>
                    <td>${OrderCountAll} 명</td>
                </tr>
                <tr>
                    <th>남은 시간</th>
                    <td>
                        <c:set var="dateCount" value="${DateCount}" />
                        <c:if test="${dateCount>0}">
                                <span class="endCount"></span>
                        </c:if>    
                        <c:if test="${dateCount<=0}">
                            <span>마감된 프로젝트입니다</span>
                        </c:if>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div>
        <span>목표금액 ${PjDto.pjTargetMoney}</span>
        <span>펀딩기간 ${PjDto.pjFundingStartDate} ~ ${PjDto.pjFundingEndDate}</span>
        <span>결제 목표금액 달성시 ${PjDto.pjFundingEndDate}에 진행</span>
    </div>

    <c:forEach var="OptionsDto" items="${OptionsDto}">
        
 	<c:set var="loginNo" value="${loginNo}" />
    <c:set var="currentStock" value="${OptionsDto.optionsStock}" />
    <c:set var="orderCount" value="${OrderCount}" />
    

    <c:choose>

        <c:when test="${DateCount<=0}">
            <div>
                <span>마감된 프로젝트입니다</span>
            </div>
            <div>
                
                <span class="no">${OptionsDto.optionsNo}</span>
                <span class="name">${OptionsDto.optionsName}</span>
                <span class="price">${OptionsDto.optionsPrice}</span>
                <span class="stock">${OptionsDto.optionsStock}</span>
            </div>
        </c:when>
    	
    	<c:when test="${loginNo==null}">
    		<div class="selectOption"  onclick="location.href='selectCheck?optionsNo=${OptionsDto.optionsNo}';">
                <span class="no">${OptionsDto.optionsNo}</span>
                <span class="name">${OptionsDto.optionsName}</span>
                <span class="price">${OptionsDto.optionsPrice}</span>
                <span class="stock">${OptionsDto.optionsStock}</span>
            </div>
    	</c:when>
    
    
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

        <c:when test="${loginNo!=null&&OrderCount==0&&currentStock!=0}">
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

</div>

    <!--푸터-->
    <jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>