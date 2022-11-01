<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


<jsp:include page="/WEB-INF/views/template/header3.jsp">
	<jsp:param value="프로젝트 주문" name="title" />
</jsp:include>


<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js" integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<style>
      .boxer {
        border : 2px;
        border-color: lightgray;
        border-style: solid;
        padding: 20px;
        margin: 40px;
        }
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
    .red {
        color:#ff5757;
    }
    .little-left {
        margin-left:8px;
    }
        
    .updown {
        margin-top:5px;
        margin-bottom:5px;
    }
</style>


<script type="text/javascript">
    //자바스크립트 코드
    $(function(){
        $(".toggle-control").on("click",function(){
            $(".target").toggle();
        });
      
        
    });
    
    function findAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
            
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
   
function saveAddress(){
    document.frm.target="ifrm";
    document.frm.action="http://localhost:8888/edit/address_plus";
    document.frm.submit();
    location.reload();
}
</script>

<div class="container-1400 center">


    <form action="order" method="post">

    <div class="boxer">
    
        
    <div>
        <div class="updown">
            <span class="projectIntroduce">후원 프로젝트</span><span class="projectValue little-left">${PjDto.pjName}</span>
        </div>
        <div class="updown">
            <span class="projectIntroduce">선택 옵션</span><span class="projectValue little-left">${OptionsDto.optionsName}</span>
        </div>
        <div class="updown">
            <span class="projectIntroduce">옵션 가격</span><span class="projectValue little-left">${OptionsDto.optionsPrice}</span>
        </div>
        <div class="updown">
            <span class="projectIntroduce">배송비</span><span class="projectValue little-left">${OptionsDto.optionsDeliveryPrice}</span>
        </div>
        <div class="row updown">
            <input type="text" name="ordersMessage" class="input input-underline w-50" placeholder="배달시 요청사항(예: 경비실에 맡겨주세요)">
        </div>
        
        <input type="hidden" name="ordersOptionsNo" value="${OptionsDto.optionsNo}">
        <input type="hidden" name="ordersPayDate" value="${PjDto.pjFundingEndDate}">
        <input type="hidden" name="ordersDeliveryPay" value="${OptionsDto.optionsDeliveryPrice}">
        <input type="hidden" name="ordersMemNo" value="${AddressDto[0].addressMemNo}">       
    </div>
    </div>

    

    <div id="choose" class="boxer">
        
        <h2>배송지 선택</h2>
        
        

        <c:if test="${AddressDto==null}">
            <span>배송지를 새로 등록한 후 선택해주세요</span>
        </c:if>

        <select name="ordersAddressNo" style="height:50px;">
        <c:forEach var="AddressDto" items="${AddressDto}">
           <option value="${AddressDto.addressNo}">
            
            <div>
                <div>
                    <span>배송지 번호: </span>
                    <span>${AddressDto.addressNo}</span>
                </div>
                <div>
                    <span>택배 수령인: </span>
                    <span>${AddressDto.addressName}</span>
                </div>
                <div>
                    <span>연락처: </span>
                    <span>${AddressDto.addressTel}</span>
                </div>
                <div>
                    <span>우편번호: </span>
                    <span>${AddressDto.addressPost}</span>
                </div>
                <div>
                    <span>기본주소: </span>
                    <span>${AddressDto.addressBasic}</span>
                </div>
                <div>
                    <span>상세주소: </span>
                    <span>${AddressDto.addressDetail}</span>
                </div>
            </div>

            </option>
        </c:forEach>
        </select>

        
        <button type="submit" class="btn btn-positive little-left">주문하기</button>
        
    </form>
    
</div>

    <div class="boxer">

        <details>

            <summary>
                <h2>배송지 입력</h2>
            </summary>
            <p>
                <form method ="post" name="frm" class="target">
                    <iframe name="ifrm" width="0" height="0" frameborder="0"></iframe> 
                <div>

                    <label>해당 회원 번호
                        <input class="input input-underline w-50" name="addressMemNo" value="${sessionScope.loginNo}" readonly> 
                    </label>
                
                </div>

                <div>

                    <label>받는 사람
                        <input class="input input-underline w-50" name = "addressName" type="text" placeholder="수령인" required autocomplete="off">
                    </label>
                    
                </div>
                
                <div>	
                    <label>우편주소
                        <input class="input input-underline w-50" name = "addressPost"  id="sample6_postcode" placeholder="우편번호" type="text" required autocomplete="off">
                    </label>
                    <button class="btn btn-positive" onclick="findAddress()" value="우편번호 찾기">우편번호 찾기</button>
                </div>
                
                
                
                <div>
                    <label>주소
                        <input class="input input-underline w-50" name = "addressBasic" id="sample6_address" placeholder="주소" type="text" required autocomplete="off">
                    </label>
                </div>
                
                <div>
                    <label>상세주소
                        <input class="input input-underline w-50" name = "addressDetail" id="sample6_detailAddress" placeholder="상세주소" type="text" required autocomplete="off">
                    </label>
                </div>
                
                <div>
                    <label>받는 사람 휴대폰 번호
                        <input class="input input-underline w-50" name = "addressTel" type="text" required autocomplete="off">
                    </label>
                </div>
                
                <div class="updown">
                <button class="btn btn-positive" value="등록하기" onclick="saveAddress();">등록하기</button>
                </div>
                    
                </form>
            </p>
        </details>

    



</body>
</html>