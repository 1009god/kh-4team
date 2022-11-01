<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="옵션 체크" name="title" />
</jsp:include>

    <style>
        .cate {
            color:#9e9e9e;
            font-size:13px;
            font-style:bold;
        }

        .nam {
            font-size: 38px;
        color:#000000DE;
        margin: 0px 10px 0px 0px;
        text-align: left;
        }

    </style>

    <script type="text/javascript">
        $(function(){
            const datecountonjs=Math.ceil("${DateCount}");
         $(".endCount").text(datecountonjs+"일 남음");
        });


       
    </script>





<div class="container-1400 center">
    <div class="center" style="display:flex;justify-content: center;">
        <!--프로젝트 정보 작게 보여주는 곳-->
        <div style="margin-right: 20px;">
            <!--프로젝트 이미지-->
            <c:set var="PjFileList" items="${PjFileList}" />
            <img style="width:150px;height: 130px;" src="http://localhost:8888/files/download/${PjFileList[0].pjFileNo}">
        </div>
        <div>
            <div>
                <span class="cate">${PjDto.pjCategory}</span>
            </div>
            <div>
                <span class="nam">${PjDto.pjName}</span>
            </div>
            <div>
                <c:set var="OrdersCalVO" items="${OrdersCalVO}" />
                <span>${OrdersCalVO.priceTotal}</span><span>${OrdersCalVO.achievementRate}</span><span class="endCount"></span>
            </div>

        </div>
    
    </div>

    <div class="row center" style="margin:10px 100px;">
        <!--선물정보, 후원금액-->
        <div>
            <div>
                <span>옵션 정보</span>
            </div>
            <div>
                <div>
                    <span>옵션 구성</span><span>${OptionsDto.optionsName}</span>
                </div>
                <div>
                    <span>옵션 가격</span><span>${OptionsDto.optionsPrice}</span>
                </div>
                <div>
                    <span>배송비</span><span>${OptionsDto.optionsDeliveryPrice}</span>
                </div>
            </div>
        </div>
        <div>
            
            <div>
                <span>프로젝트 성공시 결제는 ${PjDto.pjFundingEndDate} 에 진행됩니다. 프로젝트가 무산되거나 중단된 경우, 예약된 결제는 자동으로 취소됩니다.</span>
            </div>
            
            <div>
                
                <details>
                    <summary>후원 유의사항 확인</summary>
                    <p>후원은 구매가 아닌 창의적인 계획에 자금을 지원하는 일입니다.
                        텀블벅에서의 후원은 아직 실현되지 않은 프로젝트가 실현될 수 있도록 제작비를 후원하는 과정으로, 기존의 상품 또는 용역을 거래의 대상으로 하는 매매와는 차이가 있습니다. 따라서 전자상거래법상 청약철회 등의 규정이 적용되지 않습니다.
                        프로젝트는 계획과 달리 진행될 수 있습니다.
                        예상을 뛰어넘는 멋진 결과가 나올 수 있지만 진행 과정에서 계획이 지연, 변경되거나 무산될 수도 있습니다. 본 프로젝트를 완수할 책임과 권리는 창작자에게 있습니다.</p>
                </details>
            </div>
        
        </div>

        <div>
            <button class="btn btn-positive" onclick="location.href='order?optionsNo=${OptionsDto.optionsNo}'">후원하기</button>
        </div>
        
    </div>


</div>



<a href="order?optionsNo=${OptionsDto.optionsNo}"><button>다음</button></a>


<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>