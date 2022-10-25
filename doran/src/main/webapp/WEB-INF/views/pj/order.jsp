<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="OptionsDto" items="${OptionsDto}"></c:set>
<c:set var="PjDto" items="${PjDto}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h2>결제</h2>
    <div>
        ${PjDto.pjName}
        ${OptionsDto.optionsNo}
        ${OptionsDto.optionsName}
        ${OptionsDto.optionsPrice}
    </div>



    <div>
        <h2>배송지 선택</h2>
        <select>
            <option></option>
        </select>
        
    </div>





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
        <label>우편주소
            <input name = "addressPost" type="text" required>
        </label>
        </div>
        
        <div>
        <label>주소
            <input name = "addressBasic" type="text" required>
        </label>
        </div>
        
        <div>
        <label>상세주소
            <input name = "addressDetail" type="text" required>
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

<button>주문하기</button>



</body>
</html>