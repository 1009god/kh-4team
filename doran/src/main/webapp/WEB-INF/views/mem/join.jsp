<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	 		<link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
        
            <link rel="stylesheet" type="text/css" href="/css/reset.css">
            <link rel="stylesheet" type="text/css" href="/css/commons.css">
            
        
            <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
       		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
       		
        
            <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>  
            <script src="/js/checkbox.js"></script>
            
	<style>
	
	<style>
        .input.NNNNN ~ .NNNNN-message ,
        .input.NNNNY ~ .NNNNY-message ,
        .input.fail ~ .fail-message {
            display: block;
        }
        .input ~ .NNNNN-message {
            color: red;
            display: none;
        }
        .input ~ .NNNNY-message {
            color: green;
            display: none;
        }
        .input ~ .fail-message {
            color: red;
            display: none;
        }
        
	.progressbar {
	    height:10px;
	    width:100%;
	    overflow: hidden;/* 넘어갈 경우에 대한 처리*/
	    position:relative;
	}
	.progressbar > .inner {
	    position: absolute;
	    top:0;
	    left:0;
	    bottom:0;
	    width:0%;
	    background: rgb(131,58,180);
	    background: linear-gradient(90deg, rgba(131,58,180,1) 0%, rgba(253,29,29,1) 50%, rgba(252,176,69,1) 100%);
	    transition: width 0.2s linear;
	}
	/* 메세지는 숨김처리 */
	.success-message,
	.fail-message { 
	    display: none;
	}
	.success-message {
	    color:green;
	}
	.fail-message {
	    color:red;
	}
	/* 입력창의 상태에 따라 특정 메세지를 표시 */
	.input.success {
	    border-color: green;
	}
	.input.success ~ .success-message {
	    display: block;
	}
	.input.fail {
	    border-color: red;
	}
	.input.fail ~ .fail-message {
	    display: block;
	}
	.fa-asterisk {
		color:red;
	}
	</style>

	 <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
    <script type="text/javascript">
	
  //AJAX 이용시 주의사항
    //- 기존처럼 즉시 검사가 불가능하므로 상태를 저장할 객체 필요
    var inputStatus = {
        memEmailValid:false,
        memNickValid:false,
    };
    
	$(function() {
		//input[name=memEmil] 에 blur 이벤트 발생 시 아이디 중복 검사
		//- 직접 할 수 없으므로 서버에 요청을 보냄
		$("input[name=memEmail]").blur(function() {
			
			var memEmail = $(this).val();
			if(!memEmail) return;
			
			console.log(memEmail)
			
			$.ajax({
				url:"http://localhost:8888/rest/mem/id?memEmail="+memEmail,
				method:"get",
				success:function(resp){
					//$("input[name=memEmail").next("span").text(resp);
					if(resp == "NNNNY") {
						$("input[name=memEmail").next("span").text("사용할 수 있는 이메일입니다");
						inputStatus.memEmailValid = true;
					}
					else if(resp == "NNNNN") {
						$("input[name=memEmail").next("span").text("이미 사용중인 이메일입니다");
						inputStatus.memEmailValid = false;
					}
				}
			});
		});
		
        
        $("input[name=memNick]").blur(function(){
        	
        	//작성된 닉네임 불러오기
        	var memNick = $(this).val();
        	
        	//형식 검사 실시
        	var regex = /^[가-힣a-z0-9]{2,10}$/;
        	var judge = regex.test(memNick);
        	
        	//형식 검사 결과에 따라 다른 처리를 수행한다
        	// - 성공 : AJAX 통신으로 닉네임 중복검사 실시
        	//- 실패 : 실패 메세지 처리
        	$(this).removeClass("fail NNNNN NNNNY");
        	
        	if(judge) {
        		
        		var that = this;
        		$.ajax({
	        		uri:"http://localhost:8888/rest/mem/nick?memNick="+memNick,
	        		method:"get",
	        		success:function(resp){
	        			if(resp == "NNNNN"){
	        				$(that).addClass("NNNNN");
	        			}
			        	else if(resp == "NNNNY") {
			        		$(that).addClass("NNNNY");
			        	}
	        		},
	        		error:function(){} //통신 오류 발생 시
	       		});
	        }
        	else{
        		$(this).addClass("fail");
        	}
		});
	</script>
	
	<form action="join" method="post" class="login-formcheck">
	<div class="container-300">
		<div class="row center">
			<h1>회원 가입</h1>
		</div>
		
		 <div class="row">
            <label>이메일</label>
            <input type="text" name="memEmail" class="input w-100" placeholder="name@example.com" id="floatingInput" required>
            <span></span>
        </div>
        
         <div class="row">
            <label>비밀번호</label>
            <input type="password" name="memPw" class="input w-100" placeholder="Password" required>
        </div>
		
		<div class="row">
            <label>닉네임</label>
            <input type="text" name="memNick" class="input w-100" placeholder="한글, 영어, 숫자로 2-10글자" required>
            <span class="NNNNN-message">이미 사용중인 아이디입니다</span>
            <span class="NNNNY-message">사용 가능한 아이디입니다!</span>
            <span class="fail-message">한글과 영어, 숫자 2~10글자로 작성해주세요</span>
        </div>
		
		<div class="row">
            <label>전화번호</label>
            <input type="tel" name="memTel" class="input w-100" placeholder="- 제외하고 입력"  required>
        </div>

		<div class="row">
			<select name="memRoute" required > 
				<option>SNS</option>
				<option>검색</option>
				<option>친구추천</option>
				<option>광고</option>
				<option>기타</option>
			</select>
		</div>
		<div class="d-grid gap-2 d-md-block">
  			<button class="btn btn-primary" type="submit">가입하기</button>
		</div>
	</div>
	</form>
		
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
		