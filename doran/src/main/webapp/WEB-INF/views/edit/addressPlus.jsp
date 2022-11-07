<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="배송지 추가" name="title"/>
</jsp:include>

<style>

form > div {
padding: 10px;

}

</style>






<div class="container-1400" style="height:600px">

<div class= "container-1400" style="padding-left:10px;">
	<h1 style="padding-top: 10px;padding-bottom: 10px;">배송지 추가</h1>
</div>
    
 <hr style="border:1px color= silver;" width="1400px">




<form method ="post">

	<div>
		<input name="addressMemNo" value="${sessionScope.loginNo}" hidden> 
	</div>
	
	
<table>
	<thead>
	  <tr>
	    <th>받은사람</th>
	    <th><input name = "addressName" type="text" required autocomplete="off" class="input input-underline"></th>
	  </tr>
	</thead>
	<tbody>
	  <tr>
	    <td>우편주소</td>
	    <td><input type="button" onclick="findAddress()" value="우편번호 찾기"></td>
	  </tr>
	  <tr>
	    <td colspan="2">
	    	<input name = "addressPost"  id="sample6_postcode"  type="text" required autocomplete="off"  class="input input-underline">
	    </td>
	  </tr>
	  <tr>
	    <td>주소</td>
	    <td><input name = "addressBasic" id="sample6_address"  type="text" required autocomplete="off" class="input input-underline"></td>
	  </tr>
	  <tr>
	    <td>상세주소</td>
	    <td><input name = "addressDetail" id="sample6_detailAddress" type="text" required autocomplete="off" class="input input-underline"></td>
	  </tr>
	  <tr>
	    <td>번호</td>
	    <td><input name = "addressTel" type="text" required autocomplete="off" class="input input-underline"></td>
	  </tr>
	</tbody>
</table>
	
	<div>
	<button type="submit" class="btn">등록완료</button>
	</div>
	
</form>
</div>





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
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>

