<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>배송지 수정페이지</h1>

<h2>등록된 배송지</h2>

(배송지목록 출력)

<hr>

<h2>배송지 추가</h2>
<form method ="post">
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
	<button type="submit">등록완료</button>
	</div>
	
</form>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
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

</body>
</html>

