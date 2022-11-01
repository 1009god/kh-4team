<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="회원 탈퇴" name="title"/>
</jsp:include>

<style>
        .btn.btn-positive[disabled],
        .btn.btn-positive[disabled]:hover {
            background-color: #74b9ff;
            cursor: not-allowed;
        }
    </style>

    <!-- jquery를 사용하기 위하여 라이브러리 js 파일을 불러온다-->
    <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
    <script type="text/javascript">
        $(function(){

            //필수선택 이벤트
            $(".ck-required").change(function(){
                var count = 0;
                $(".ck-required").each(function(){
                    if($(this).prop("checked")){
                        count++;
                    }
                });
                var judge = $(".ck-required").length == count;
                if(judge){//필수요소 모두 체크 -> 버튼 비활성화 해제
                    $(".btn-next").prop("disabled", false);
                }
                else {//필수요소 모두 체크되지 않은 경우 -> 버튼 비활성화 설정
                    $(".btn-next").prop("disabled", true);
                }
            });
        });
    </script>

  <div class="container-500">
        <div class="row center">
            <h1>회원탈퇴</h1>            
        </div>        
        
		<div class="row mt-30">
		 	<h2>서비스 이용에 불편함이 있으신가요?</h2>
		 	<h5>불편한 사항이 있다면 언제든 도란도란에 알려주세요</h5>
		 	<a href="/doran-q/list">도란도란 고객센터</a>
            <h2>탈퇴 전 유의사항을 확인해 주시기 바랍니다</h2>
        </div>
        <div class="row">
            <textarea class="input w-100 fix-size" rows="6" readonly>
            후원 및 결제
            
			-이미 결제된 후원은 취소되지 않습니다
			- 결제 완료 후 탈퇴하더라도, 선물 전달이 완료될 때까지 창작자가 배송 정보를 열람할 수 있습니다
			- 아직 선물을 받지 못했다면, 선물 전달 과정에서 불이익이 발생할 수 있습니다. 배송정보를 변경할 수 없으며 전달에 관한
				커뮤니티 공지, 문의 내용 등을 확인할 수 없습니다.
			- 관련 법령에 따라 후원 및 후원취소에 관한 기록, 결제 및 선물 전달에 관한 기록은 5년 동안 보관됩니다.
			
			프로젝트
			
			-작성중, 제출, 반려 및 승인된 프로젝트는 모두 삭제되고 공개예정, 펀딩 진행중인 그 프로젝트는 모두 중단됩니다.
			- 펀딩이 종료된 프로젝트는 삭제되지 않습니다.
			- 탈퇴하더라도, 이미 펀딩이 종료된 프로젝트에 관한 창작자의 의무와 책임 조항은 그 효력을 유지합니다.
            </textarea>
        </div>
        <div class="row float-container">

            <label class="float-left w-50">
                <input class="ck ck-required" type="checkbox" name="agree" value="type1">
                <span>탈퇴 유의사항을 확인했습니다</span>
            </label>


        </div>

	<div class="row mt-50 mb-50 right">
		<button class="btn btn-positive btn-next" onclick="location.href='/mypage/goodbye'" disabled>다음 단계로 이동</button>
	</div>

</div>


<jsp:include page="/WEB-INF/views/template/footer2.jsp"></jsp:include>
