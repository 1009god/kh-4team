<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
    <style>
      body {
        margin: 0;
      }
      .container {
        width: 50%;
        height: 100%;
        background:url('/img/loginBack.jpg') no-repeat;
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;
      }
      
      .big-border {
      	
      }
    </style>
  </head>

<body>      

 <div class="" style="float: left;">
      <img src="/img/DoranMini.png" width="30%">
    </div>
    <div class=container style="float: right;"></div>
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h3>로그인</h3>
          </div>
        </div>
        <form action="login" method="post" class="login-form">
          <input type="text" name="memEmail" placeholder="이메일" required/>
          <input type="password" name="memPw" placeholder="비밀번호" required/>
          <button>login</button>
          <p class="message"><a href="/mem/findEmail">이메일을 잊으셨나요?</a></p>
          <p class="message"><a href="/mem/findPw">비밀번호를 잊으셨나요?</a></p>
          <p class="message"><a href="/mem/join">도란도란 계정이 없으신가요?</a></p>
       
       <c:if test="${param.error != null}">
			<h5 style="color:red;">없는 회원 정보거나 아이디 또는 비밀번호가 틀렸습니다</h5>
		</c:if>
		
        </form>
      </div>
    
	
</div>
</body>
</html>
