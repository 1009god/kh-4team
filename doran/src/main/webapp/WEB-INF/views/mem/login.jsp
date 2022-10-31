<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
          <p class="message"><a href="/mem/findEmail">이메일 찾기</a></p>
          <p class="message"><a href="/mem/findPw">비밀번호 찾기</a></p>
        </form>
      </div>
    
	
</div>
</body>
</html>
