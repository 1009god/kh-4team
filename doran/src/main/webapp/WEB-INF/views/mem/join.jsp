<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="회원가입" name="title"/>
</jsp:include>
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
	
	.solid-lines {
	  border: 1px solid gray;
	}
	
	.w-30 {
    width:30%;
	}
	
	.mt-200 { margin-top: 200px;}
	.p-80 {padding:80;}
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
			
			var regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			
			var memEmail = $(this).val();
			if(!memEmail) return;
			
			console.log(memEmail);
			
			var judge = regex.test(memEmail);
			if (!judge) {
				$(this).next("span").text(
						"이메일을 형식에 맞게 작성해 주세요.");
				status.memEmail = false;
				return;
			} 
			else {
				$(this).next("span").text("사용 가능한 이메일입니다.");
				status.memEmail = true;
			}
		
			
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
		
		//닉네임 입력창을 만들고 blur 상황에서 중복 검사를 실시하도록 구현
        //- 닉네임은 한글+숫자 2~10글자 이내로 작성해야 함
        //- 형식에 맞지 않을 경우 "닉네임은 한글과 숫자 2~10글자로 작성하세요" 출력
        //- 형식에 맞을 경우 ajax 통신을 사용하여 서버로 확인 요청을 전송
        //- 서버에서 처리를 위하여 MemberRestController에 /nick 주소 처리 매핑 생성
        //- 닉네임이 존재할 경우 NNNNN , 존재하지 않을 경우 NNNNY를 반환
        //- 반환된 결과에 따라 메세지 출력
        $("input[name=memNick]").blur(function() {
        	
        	//작성된 닉네임을 불러온다
        	var memNick = $(this).val();
        	
        	//형식 검사를 실시한다
        	var regex = /^[가-힣a-z0-9]{2,10}$/;
        	var judge = regex.test(memNick);
        	
        	//형식 검사 결과에 따라 다른 처리를 수행한다
        	//- 성공 : ajax 통신으로 닉네임 중복 검사 실시
        	//- 실패 : 실패 메시지 처리
        	
        	console.log(memNick);
        	
        	$(this).removeClass("fail NNNNN NNNNY");
            if(judge){
                var that = this;//this를 보관
                $.ajax({
                    //url:"http://localhost:8888/rest/mem/nick?memberNick="+memberNick,
                    //method:"get",
                    url:"http://localhost:8888/rest/mem/nick",
                    method:"post",
                    data:{
                        memNick:memNick
                    },
                    success:function(resp){
                        //(+주의) 이곳에서의 this는 입력창이 아니다
                        if(resp == "NNNNN"){
                            $(that).addClass("NNNNN");
                            inputStatus.memNickValid = false;
                        }
                        else if(resp == "NNNNY"){
                            $(that).addClass("NNNNY");
                            inputStatus.memNickValid = true;
                        }
                    },
                    error:function(){}//통신 오류 발생 시
                });
            }
            else {
                $(this).addClass("fail");
                inputStatus.memNickValid = false;
            }
        });
		
        $("input[name=memPw]").blur(function(){
            var text = $(this).val();
            var regex = /^[A-Za-z0-9~!@#*]{8,16}$/;
            var judge = regex.test(text);
            $(this).removeClass("success fail");
            if(judge){
                $(this).addClass("success");
            }
            else {
                $(this).addClass("fail");
            }
        });
        
        $("#password-check").blur(function(){
            var origin = $("input[name=memPw]").val();
            var repeat = $(this).val();
            var judge = origin == repeat;
            $(this).removeClass("success fail");
            if(judge){
                $(this).addClass("success");
            }
            else {
                $(this).addClass("fail");
            }
        });
        
        $("input[name=memTel]").blur(function() {
			var regex = /^010([0-9]{8})$/;
			var memTel = $(this).val();
			var span = $(this).next("span");
			var judge = regex.test(memTel);
			if (!judge) {
				$(this).next("span").text(
					"올바른 형식의 번호를 입력해 주세요");
				status.memTel = false;
				return;
			} 
			else {
				$(this).next("span").text("사용 가능한 번호입니다");
				status.memtel = true;
			}
			
		
		});
		
		
    });
	</script>
	<form action="join" method="post" class="login-formcheck">
	<div class="container-350">
		<div class="row center">
			<h1>회원 가입</h1>
		</div>

		 <div class="row">
            <label>이메일</label>
            <input type="text" name="memEmail" class="input w-100" placeholder="name@example.com"  required>
            <span></span>
        </div>

         <div class="row">
            <label>비밀번호</label>
            <input type="password" name="memPw" class="input w-100" onblur="checkPassword();" placeholder="8-16자 영문 소문자/숫자/특수문자(~!@#*)" required>
        	<span class="success-message">올바른 비밀번호 형식입니다</span>
            <span class="fail-message">8~16자로 반드시 영문 대/소문자, 숫자, 특수문자가 포함되어야 합니다</span>
        </div>

        <div class="row">
            <label>비밀번호 확인</label>
            <input type="password" class="input w-100" id="password-check" placeholder="8-16자 영문 소문자/숫자/특수문자(~!@#*)" >
            <span class="success-message">비밀번호가 일치합니다</span>
            <span class="fail-message">비밀번호가 일치하지 않습니다</span>
        </div>

		<div class="row">
            <label>닉네임</label>
            <input type="text" name="memNick" class="input w-100" placeholder="한글, 영어, 숫자로 2-10글자" required>
       		<span class="NNNNN-message">이미 사용중인 닉네임입니다</span>
            <span class="NNNNY-message">사용 가능한 닉네임입니다!</span>
            <span class="fail-message">한글과 영어 소문자 숫자 2~10글자로 작성해주세요</span>
        </div>
		<div class="row">
            <label>전화번호</label>
            <input type="tel" name="memTel" class="input w-100" placeholder="- 제외하고 입력"  required>
       		<span></span>
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