<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">


    <link rel="stylesheet" type="text/css" href="./css/reset.css">
    <link rel="stylesheet" type="text/css" href="./css/commons.css">
    <!--font awesome-->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">


    <style>
       
    </style>

    <!--
        제이쿼리를 사용하기 위하여 라이브러리 js 파일 호출
        배포할 때는 MIN버전(3.6.0.min.js) 으로, 공부할때는 일반버전으로
        +  lodash 추가-->    
    <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js" integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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


    </script>



</head>
<body>

    <form action="order" method="post">

    <h2>결제</h2>
    <div>
        ${PjDto.pjName}
        ${OptionsDto.optionsNo}
        ${OptionsDto.optionsName}
        ${OptionsDto.optionsPrice}
        
        <input type="hidden" name="ordersOptionsNo" value="${OptionsDto.optionsNo}">
        <input type="hidden" name="ordersPayDate" value="${PjDto.pjFundingEndDate}">
        <input type="text" name="ordersMessage" placeholder="배달시 요청사항(예: 경비실에 맡겨주세요)">
        <input type="hidden" name="ordersDeliveryPay" value="${OptionsDto.optionsDeliveryPrice}">
        <input type="hidden" name="ordersMemNo" value="${AddressDto[0].addressMemNo}">       
    </div>


    

    <div id="choose">
        
        <h2>배송지 선택</h2>
        
        ${AddressDto}
        <select name="ordersAddressNo">
            <c:forEach var="AddressDto" items="${AddressDto}">
           <option value="${AddressDto.addressNo}">${AddressDto}</option>
        </c:forEach>
        </select>

    </div>

    <button type="submit">주문하기</button>

</form>


    <div>
        <button class="toggle-control">배송지 등록</button>
    

    <form method ="post" class="target" action="http://localhost:8888/edit/address_plus" target="http://localhost:8888/pj/order?optionsNo=${OptionsDto.optionsNo}">
        <div>
            <h2>배송지 입력</h2>
        </div>
       
	<div>
	<label>해당 회원 번호
		<input name="addressMemNo" value="${sessionScope.loginNo}" > 
	</label>
	
	<label>받는 사람
		<input name = "addressName" type="text" required>
	</label>
	</div>
	
	<div>	
	<label>우편주소<input type="button" onclick="findAddress()" value="우편번호 찾기"><br>
		<input name = "addressPost"  id="sample6_postcode" placeholder="우편번호" type="text" required  >
	</label>
	</div>
	
	
	
	<div>
	<label>주소
		<input name = "addressBasic" id="sample6_address" placeholder="주소" type="text" required>
	</label>
	</div>
	
	<div>
	<label>상세주소
		<input name = "addressDetail" id="sample6_detailAddress" placeholder="상세주소" type="text" required>
	</label>
	</div>
	
	<div>
	<label>받는 사람 휴대폰 번호
		<input name = "addressTel" type="text" required>
	</label>
	</div>
	
	<div>
	<button type="submit" class="updateAddress">등록완료</button>
	</div>
        
    </form>

</div>





</body>
</html>