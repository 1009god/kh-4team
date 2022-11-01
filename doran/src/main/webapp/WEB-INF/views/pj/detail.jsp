<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header2.jsp"></jsp:include>
<!--swiper 의존성-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
<!--폰트어썸-->
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">


<style>
	

    .selectOption {
        border: 1px dotted gray;
    }


    .projectIntroduce {
        font-size: 14px;
        color:#000000DE;
        margin: 0px 0px 14px;
        text-align: left;
    }

    .projectValue {
        font-size: 38px;
        color:#000000DE;
        margin: 0px 10px 0px 0px;
        text-align: left;
    }

    .projectSmall {
        font-size: 14px;
        color:#000000DE;
        margin: 0px 0px 0px 3.5px;
        text-align: left;
    }

    .projectPercentage {
        font-size: 18px;
        color:#000000DE;
        text-align: left;
        font-weight: bolder;
    }

    .targetLeft {
        width: 56px;
    margin-right: 20px;
    font-weight: 700;
    color: rgb(61, 61, 61);
    font-size: 12px !important;
    line-height: 20px !important;
    }

    .targetRight {
        margin: 0px;
        flex: 1 1 auto;
    
    -webkit-box-align: center;
    align-items: center;
    font-weight: 400;
    color: rgb(61, 61, 61);
    font-size: 13px !important;
    line-height: 22px !important;
    }

    .rewardFence {
    width: 100%;
    height: auto;
    padding: 24px 0px 0px;
    }

    .rewardBox {
        border-color:#9E9E9E;
        border-width:thin;
        border-style:solid;
        border-radius: 2px;
        padding: 20px;
        margin:15px;
    }
    
    .rewardBoxInner {
        flex: 1 1 0%;
    }
    

    .rewardPrice {
    font-size: 24px;
    line-height: 36px;
    letter-spacing: -0.025em;
    margin: 0px 0px 6px;
    }
    
    .rewardInformation {
    font-size: 13px;
    line-height: 20px;
    letter-spacing: -0.015em;
    }

    .rewardPriceComplete {
        font-size: 24px;
        color:#9E9E9E;
    line-height: 36px;
    letter-spacing: -0.025em;
    margin: 0px 0px 6px;
    }
    
    .rewardInformationComplete {
        font-size: 13px;
        color:#9E9E9E;
    line-height: 20px;
    letter-spacing: -0.015em;
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



<div class="container-1400">

    <div class="center" style="margin-top:10px;">
        <button class="btn btn-neutral" onclick="location.href='list?category=${PjDto.pjCategory}'">${PjDto.pjCategory}</button>
    </div>

    <div class="center" style="margin-top:10px;margin-bottom:10px;">
        <div>
            <h3>${PjDto.pjSummary}</h3>
        </div>
        <div>
            <h1>
                ${PjDto.pjName}
            </h1>
        </div>
    </div>
        
    <div class="center" style="margin:0px 100px;"><!--프로젝트 대표이미지, 정보 들어가는 자리(float써야됨 구와아악)-->
    
        <div style="width:700px;height:480px; margin-right:20px;float:left;">
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
        
        <aside style="text-align:left;width:360px;height:480px;float:left;">
            <div>
                <span class="projectIntroduce">모인 금액</span>
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
                <span class="projectIntroduce">남은 시간</span>
            </div>
            <div>
                <c:set var="dateCount" value="${DateCount}" />
                <c:if test="${dateCount>0}">
                        <span class="endCount projectValue"></span>
                </c:if>    
                <c:if test="${dateCount<=0}">
                    <span>마감된 프로젝트입니다</span>
                </c:if>
            </div>
            <div>
                <span class="projectIntroduce">후원자</span>
            </div>
            <div>
                <span class="projectValue">${OrderCountAll}</span><span class="projectSmall">명</span>
            </div>

            <div>
                <hr>
            </div>
            <div>
                <span class="targetLeft">목표금액</span><span class="targetRight">${PjDto.pjTargetMoney}원</span>
            </div>
            <div>
                <span class="targetLeft">펀딩기간</span><span class="targetRight">${PjDto.pjFundingStartDate} ~ ${PjDto.pjFundingEndDate}</span>
            </div>
            <div>
                <span class="targetLeft">결제</span><span class="targetRight">목표금액 달성시 ${PjDto.pjFundingEndDate}에 진행</span>
            </div>
            
            <hr>

            <div class="center">
                <button class="btn btn-neutral">
                    <div>
                        <i class="fa-solid fa-thumbs-up"></i>
                    </div>
                    <span>${PjDto.pjLikesNumber}</span>
                </button>
                <button class="btn btn-neutral" style="height:54px;" onclick="javascript:shareTwitter();"><i class="fa-brands fa-twitter"></i></button>
                <button id="btnKakao" class="btn btn-neutral" style="height:54px;" onclick="javascript:shareKakao();"><i class="fa-solid fa-comment"></i></button>
                <button class="btn btn-positive" style="height:54px;" onclick="location.href='#comeHere';">이 프로젝트 후원하기</button>
            </div>

            <div style="margin-top:10px;">
                <button class="btn btn-neutral w-100" style="height:54px;"><a href="/sellerq/write?pjNo=${PjDto.pjNo}">판매자에게 문의</a></button>
            </div>
         
        </aside>

<<<<<<< HEAD
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
		<button><a href="/sellerq/write?pjNo=${PjDto.pjNo}">판매자에게 문의</a></button>
	</div>
	


    <div>
        <span>목표금액 ${PjDto.pjTargetMoney}</span>
        <span>펀딩기간 ${PjDto.pjFundingStartDate} ~ ${PjDto.pjFundingEndDate}</span>
        <span>결제 목표금액 달성시 ${PjDto.pjFundingEndDate}에 진행</span>
    </div>


	
	<div class="shareParent">
	<button type="button">sns 공유 버튼</button>
	</div>
	
	<div class="shareChild">
	<a href="javascript:shareTwitter();">트위터에 공유</a>
	<a id="btnKakao" href="javascript:shareKakao();">카카오톡에 공유</a>
	</div>
        
        <div class="float-container"></div>
    
    </div>

    

    <div class="center" style="margin:0px 100px;">
        <div style="width:700px;margin-right:20px;float:left;"><!--소개이미지 들어가는 div-->
            <img src="http://localhost:8888/files/download/${PjFileIntroduce.pjFileNo}">
        </div>

        <div style="text-align:left;width:360px;float:left;position: sticky;top: 5px;"><!--후원 리워드 선택 div-->
            <div class="rewardFence">
                <a name="comeHere"></a>
                <div style="margin:0px 0px 7px;">
                    <span>리워드 선택</span>
                </div>

                <c:forEach var="OptionsDto" items="${OptionsDto}">
        
                <c:set var="loginNo" value="${loginNo}" />
                <c:set var="currentStock" value="${OptionsDto.optionsStock}" />
                <c:set var="orderCount" value="${OrderCount}" />

                <c:choose>
                    <c:when test="${DateCount<=0}">
                        <div class="rewardBox">
                            <div class="rewardBoxInner">
                                <div class="rewardInformationComplete">
                                    <!--재고-->
                                    <span></span>
                                </div>
                                <div class="rewardPriceComplete">
                                    <!--가격-->
                                    <span class="price">${OptionsDto.optionsPrice}</span>
                                </div>
                                <div class="rewardInformationComplete">
                                    <!--상품이름-->
                                    <span class="name">${OptionsDto.optionsName}</span>
                                </div>
                            </div>
                        </div>
                    </c:when>

                    <c:when test="${loginNo==null}">
                        <div class="rewardBox" onclick="location.href='selectCheck?optionsNo=${OptionsDto.optionsNo}';">
                            <div class="rewardBoxInner">
                                <div class="rewardInformation">
                                    <!--재고-->
                                    <span class="stock">${OptionsDto.optionsStock}</span>
                                </div>
                                <div class="rewardPrice">
                                    <!--가격-->
                                    <span class="price">${OptionsDto.optionsPrice}</span>
                                </div>
                                <div class="rewardInformation">
                                    <!--상품이름-->
                                    <span class="name">${OptionsDto.optionsName}</span>
                                </div>
                            </div>
                        </div>

                    </c:when>

                    <c:when test="${OrderCount==1}">
                        <div class="rewardBox" onclick="alert('이미 후원한 프로젝트입니다. 추가로 후원할 수 없습니다');">
                            <div class="rewardBoxInner">
                                <div class="rewardInformation">
                                    <!--재고-->
                                    <span class="stock">${OptionsDto.optionsStock}</span>
                                </div>
                                <div class="rewardPrice">
                                    <!--가격-->
                                    <span class="price">${OptionsDto.optionsPrice}</span>
                                </div>
                                <div class="rewardInformation">
                                    <!--상품이름-->
                                    <span class="name">${OptionsDto.optionsName}</span>
                                </div>
                            </div>
                        </div>

                    </c:when>

                    <c:when test="${currentStock==0}">
                        <div class="rewardBox" onclick="alert('품절');">
                            <div class="rewardBoxInner">
                                <div class="rewardInformation">
                                    <!--재고-->
                                    <span class="stock">${OptionsDto.optionsStock}</span>
                                </div>
                                <div class="rewardPrice">
                                    <!--가격-->
                                    <span class="price">${OptionsDto.optionsPrice}</span>
                                </div>
                                <div class="rewardInformation">
                                    <!--상품이름-->
                                    <span class="name">${OptionsDto.optionsName}</span>
                                </div>
                            </div>
                        </div>
                    </c:when>

                    <c:when test="${loginNo!=null&&OrderCount==0&&currentStock!=0}">
                        <div class="rewardBox" onclick="location.href='selectCheck?optionsNo=${OptionsDto.optionsNo}';">
                            <div class="rewardBoxInner">
                                <div class="rewardInformation">
                                    <!--재고-->
                                    <span class="stock">${OptionsDto.optionsStock}</span>
                                </div>
                                <div class="rewardPrice">
                                    <!--가격-->
                                    <span class="price">${OptionsDto.optionsPrice}</span>
                                </div>
                                <div class="rewardInformation">
                                    <!--상품이름-->
                                    <span class="name">${OptionsDto.optionsName}</span>
                                </div>
                            </div>
                        </div>
                    </c:when>

                </c:choose>

                </c:forEach>

                
            </div>

        </div>
        
    </div>


    

    <!--푸터-->
    <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>