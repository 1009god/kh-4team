<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="메인페이지" name="title"/>
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
	.fail-message, .no-message { 
	    display: none;
	}
	.success-message {
	    color:green;
	}
	.fail-message, .no-message {
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


 
 <script type="text/javascript">
        $(function(){

//             $("input[name=memberId]").blur(function(){
//                 var text = $(this).val();
//                 var regex = /^[a-z][a-z0-9]{7,19}$/;
//                 var judge = regex.test(text);

//                 //$(this).removeClass("success");
//                 //$(this).removeClass("fail");
                
//                 //$(this).removeClass("success").removeClass("fail");

//                 $(this).removeClass("success fail");
//                 if(judge){
//                     $(this).addClass("success");
//                 }
//                 else {
//                     $(this).addClass("fail");
//                 }
//             });

            $("input[name=newPw]").blur(function(){
                var text = $(this).val();
                var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$])[a-zA-Z0-9!@#$]{8,16}$/;
                var judge = regex.test(text);

                $(this).removeClass("no fail");
                if(judge){
                    $(this).addClass("no");
                }
                else {
                    $(this).addClass("fail");
                }
            });

            $("#password-check").blur(function(){
                var origin = $("input[name=memPw]").val();
                var repeat = $(this).val();
                var judge = origin == repeat;

                $(this).removeClass("no fail");
                if(judge){
                    $(this).addClass("no");
                }
                else {
                    $(this).addClass("fail");
                }
            });

        });
    </script>






<div class="container-1400">


비밀번호 변경 페이지

<div class="container-600 left" >
 <form method="post">
	<div class="row">
		
		<input type="hidden" name="memNo" value="${memDto.memNo}" > 
		
		<div class="row">
			<label>현재 비밀번호</label>
			<input type="text" name="oldPw" required autocomplete="off">
			<span class ="no-message">비워두시면 안됩니다</span>
			<span class="fail-message">6자 이상, 20자 이내로 입력해주세요.</span>
		</div>
		
		<div class="row">
			<label>변경할 비밀번호</label>
			<input type="text" name="newPw" required autocomplete="off">
			<span class ="no-message">비워두시면 안됩니다</span>
			<span class="fail-message">6자 이상, 20자 이내로 입력해주세요.</span>
			
			<!-- 최종적으로 넘어갈 pw -->
			<input type="text" name="memPw" value="${memDto.memPw}" required autocomplete="off">
			<span class ="no-message">비워두시면 안됩니다</span>
			<span class="fail-message">6자 이상, 20자 이내로 입력해주세요.</span>
		</div>
		
	</div>
	
	<div class="row center">	
		<button type="submit" >수정</button>	
	</div>
		
	</form>
	</div>



</div>



<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>