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

    .boxer {
        border : 2px;
        border-color: lightgray;
        border-style: solid;
        padding: 20px;
        margin: 40px;
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
    var pjNo="${PjDto.pjNo}";
    var sendUrl = "http://localhost:8888/pj/detail?pjNo="+pjNo; // 전달할 URL
    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
}
function shareKakao() {
	
	 var pjNo="${PjDto.pjNo}";
	 
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



    <div class="container-1100 center">


    <div class="row">
        ${PjDto.pjCategory}
    </div>

  
        <div class="boxer">
                <img src="http://localhost:8888/files/download/${PjFileList[0].pjFilePjNo}">

        </div>

        <div class="boxer">
            <div>
                ${PjDto}
            </div>

            <div>
                ${OrdersCalVO}
            </div>

        </div>

        <div class="boxer">
            ${OptionsDto}
        </div>
        

    </div>

    <jsp:include page="/WEB-INF/views/template/adminfooter.jsp"></jsp:include>