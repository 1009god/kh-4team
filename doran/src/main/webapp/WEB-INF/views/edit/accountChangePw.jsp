<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%-- 템플릿 페이지인 header.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="비밀번호 변경" name="title"/>
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
        
	
	
	/* 메세지는 숨김처리 */
	.success-message,
	.fail-message, .no-message { 
	    display: none;
	}
	.success-message{
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
	
	</style>


  <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
 	<script type="text/javascript">
 	
		//AJAX 이용시 주의사항
		 //- 기존처럼 즉시 검사가 불가능하므로 상태를 저장할 객체 필요
		 var inputStatus = {
		     memPwValid:false		     
		 };
  
 
        $(function(){
	// 1.newPw의 정규식 검사 진행
            $("input[name=newPw]").blur(function(){
                var text = $(this).val();
                var regex = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/;                
                var judge = regex.test(text);
                console.log(text);
                console.log(judge);
                
                $(this).removeClass("no fail success");
                if(judge){ //정규식 표현이 맞으면
                    $(this).addClass("success");
                }
                else if(text == null){
                	$(this).addClass("no");
                }
                else {
                    $(this).addClass("fail");
                }
            });
            $("input[name=memPw]").blur(function(){
                var origin = $("input[name=newPw]").val();
                var repeat = $(this).val();
                var judge = origin == repeat;
                console.log(origin);
                console.log(repeat);
                console.log(judge);
                
                
                $(this).removeClass("no fail success");
                if(judge){
                    $(this).addClass("success");
                }
                else if(judge == null){
                	$(this).addClass("no")
                }
                else {
                    $(this).addClass("fail");
                }
            });
            
            $("input[name=oldPw]").blur(function(){
            	var old = $("input[name=oldPw]").val();
            	var judge2 = old == ${memDto.memPw};
            	
            	$(this).removeClass("no fail success");
                if(judge){
                    $(this).addClass("success");
                }
                else if(judge == null){
                	$(this).addClass("no")
                }
                else {
                    $(this).addClass("fail");
                }          	
            	
            });
            
//             $("input[name=oldPw]").blur(function(){
//             	var pastPw =  ${findPw.memPw};
//             	var judge3 = old == $("input[name=oldPw]").val();
            	
//             	$(this).removeClass("no fail success");
//             	if(judge){
//             		$()).
//             	}
            	
//             });
            
            //만약에 위 조건이 만족한다면
            
//             $.ajax({
// 				url:"http://localhost:8888/rest/mem/pw?memPw=" + ${memDto.memPw},
// 				method:"put",
// 				success:function(resp){
					
// 					if(resp == "NNNNN") {
// 						inputStatus.memPwValid = true;
// 						$("input[name=oldPw").next("span").text("비밀번호가 일치합니다"); 
// 						System.out.println("성공");
// 					}
// 					else if(resp == "NNNNY") {
// 						inputStatus.memPwValid = false;
// 						$("input[name=oldPw").next("span").text("비밀번호가 일치하지 않습니다");
// 						System.out.println("실패");
// 					}
// 				}
// 			});
            
            
            
            
            
        });
    </script>






<div class="container-1400">




<!-- 비밀번호가 일치하지 않는다면 input되어서는 안됨 -->



<div class="container-1200 center" >
 <form method="post">
	<div class="row">
		
		<input type="hidden" name="memNo" value="${memDto.memNo}" > 
		
		<div class="row">
<!-- 			<label>현재 비밀번호</label> -->
<!-- 			<input type="text" name="oldPw" required autocomplete="off"> -->
<!-- 			<span></span> -->
<!-- 			<span class ="no-message">비워두시면 안됩니다</span> -->
<!-- 			<span class="fail-message">6자 이상, 20자 이내로 입력해주세요.</span> -->
		</div>
		
		<div class="row">
<!-- 			<label>변경할 비밀번호</label> -->
<!-- 			<input type="text" name="newPw" required autocomplete="off"> -->
<!-- 			<span class = "success-message">성공</span> -->
<!-- 			<span class ="no-message">비워두시면 안됩니다</span> -->
<!-- 			<span class="fail-message">6자 이상, 20자 이내로 입력해주세요.</span> -->
			
			<div>
			<!-- 최종적으로 넘어갈 pw -->
			<label>변경할 비밀번호</label>
			<input type="text" name="memPw"  required autocomplete="off" class="input input-underline">
			<span class = "success-message">성공</span>
			<span class ="no-message">비워두시면 안됩니다</span>
			<span class="fail-message">6자 이상8~16자로 영문 대/소문자, 숫자, 특수문자 포함</span>
			</div>
		</div>
		
	</div>	
				<div class="row center">	
					<button type="submit" class="btn">수정</button>	
				</div>	
		
	</form>
	</div>



</div>



<%-- footer.jsp 를 동적으로 불러와라 --%>
<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>