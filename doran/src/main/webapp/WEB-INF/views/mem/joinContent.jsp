<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <jsp:include page="/WEB-INF/views/template/header3.jsp">
	<jsp:param value="이용약관" name="title" />
</jsp:include>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="/css/reset.css">
<link rel="stylesheet" type="text/css" href="/css/commons.css">
    <style>
        .btn.btn-positive[disabled],
        .btn.btn-positive[disabled]:hover {
            background-color: #bdc3c7;
           	color: #FFFFFF;
            cursor: not-allowed;
        }

        .fullscreen {
            background-color: white;
        }
        .red {
            color: red;
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
    <script type="text/javascript">
        //자바스크립트 코드

        //전체 선택 처리
        function checkAll() {
            var checkedItem = document.querySelector(".ck-all");
            var checkboxList = document.querySelectorAll(".ck");
            for(var i=0; i < checkboxList.length; i++){
                checkboxList[i].checked = checkedItem.checked;
            }

            checkAgree();
        }

        //필수 동의 항목에 대한 체크 여부 검사 및 버튼 활성화 처리
        function checkAgree(){
            var agreeBoxList = document.querySelectorAll(".ck-required");

            var count = 0;
            for(var i=0; i < agreeBoxList.length; i++){
                if(agreeBoxList[i].checked){
                    count++;
                }
            }

            var button = document.querySelector(".btn-positive")
            //if(count == agreeBoxList.length){//전체 체크가 되어 있는 경우
            //    button.disabled = false;
            //}
            //else {//전체 체크가 되어 있지 않은 경우
            //    button.enabled = true;
            //}
            button.disabled = count != agreeBoxList.length;
        }

        function fullScreen3(){

            var textarea = document.querySelector(".textarea3");
            textarea.classList.add("fullscreen");

            return false;//기본이벤트 실행 방지
        }

        function closeScreen3(e){
            if(e.keyCode == 27){
                var textarea = document.querySelector(".textarea3");
                textarea.classList.remove("fullscreen");
            }
        }

    </script>

</head>
<body>

    <!-- HTML 화면 -->
    <div class="container-500">
        <div class="row center">
            <h1>이용약관</h1>            
        </div>        

        <div class="row mt-30">
            <label>
                <input type="checkbox" class="ck ck-all" oninput="checkAll();">
                <span>전체 선택</span>
            </label>
        </div>

        <div class="row mt-30">
            <h2>제1절 총칙</h2>
        </div>
        <div class="row">
            <textarea class="solid-lines w-100 fix-size" rows="10" readonly>제1조 목적
도란도란 주식회사(이하 "회사")는 다양한 분야에 걸친 창작 프로젝트들을 통해 창작자와 후원자를 연결하고 있습니다. 회사의 주된 서비스는 창작 프로젝트의 후원을 위한 플랫폼 제공입니다. 이 약관은 회사가 운영하는 사이트 Tumblbug(이하 "사이트"라 합니다)에서 제공하는 모든 서비스(이하 "서비스"라 합니다)를 이용함에 있어 회사와 회원의 권리와 의무, 책임 사항 및 회원의 서비스 이용 절차에 관한 사항을 규정함을 목적으로 합니다.

 

제2조 용어의 정의
① 이 약관에서 사용하는 용어의 정의는 다음과 같습니다.

"회사"라 함은 도란도란 주식회사를 지칭하며 사이트 내에서 창조적인 분야를 총 망라하는 온라인 크라우드 펀딩 플랫폼 서비스를 제공하는 주체를 의미합니다.
"회원"이라 함은 사이트에 접속하여 본 약관에 동의함으로써 회사에 회원등록을 하고 회사가 제공하는 서비스를 지속해서 이용할 수 있는 이용자를 의미합니다.
"창작자"라 함은 사이트에서 프로젝트를 게시하고 운영하여 다수로부터 후원을 받는 회원을 의미합니다.
"후원자"라 함은 사이트 내에 게시된 프로젝트가 실현될 수 있도록 일정한 금액을 지불하는 방식으로 자금을 후원하는 회원을 의미합니다. 이러한 후원자의 행위는 프로젝트를 "후원"한다고 하거나 "프로젝트 밀어주기" 라고 지칭합니다.
"프로젝트"라 함은 사이트 내에서 타 회원들에게 자신이 수행하고자 하는 창작 작업에 대한 창작의 목적, 내용, 계획, 선물 등에 대한 사항을 소개함으로써 후원을 받을 수 있는 단위를 지칭합니다.
"목표 기금액"이라 함은 프로젝트에서 최종적으로 모금하고자 하는 목표 금액을 의미합니다.
"성사"는 어느 프로젝트에 대하여 후원자의 후원에 따라 결제예약된 총 후원금액이 창작자가 지정한 목표 기금액 이상일 경우, "무산"은 어느 프로젝트에 대하여 후원자의 후원에 따라 결제예약된 총 후원금액이 창작자가 지정한 목표 기금액 미만일 경우를 의미합니다.
"선물"이라 함은 프로젝트의 결과물로서 프로젝트가 "성사"될 경우 창작자가 후원자에게 프로젝트 생성시 약정한 보상을 일컫는 말입니다.
"게시물"이라 함은 회원이 서비스를 이용하면서 게시한 글, 사진, 각종 파일과 링크 등을 의미하며, 창작자가 게시한 프로젝트 관련 내용이 이에 포함됩니다.
② 본 약관에 대해서는 본 조에서 정한 용어가 우선적으로 적용되며, 본 조에서 정의되지 않은 약관 상의 용어는 관련 법령 및 관습에 따릅니다.

 

제3조 약관의 효력과 변경
① 회사는 이 약관의 내용, 상호, 대표자 성명, 전화번호, 사업자등록번호, 통신판매업 신고번호, 헬프센터 등을 회원이 쉽게 알 수 있도록 사이트의 초기 서비스화면(전면)에 게시합니다. 다만, 약관은 회원이 연결화면을 통하여 볼 수 있도록 게시할 수 있습니다.

② 회사는 "약관의 규제 등에 관한 법률", "전자서명법", "정보통신망 이용촉진 및 정보보호 등에 관한 법률" 등 관련 법령을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.

③ 회사가 약관을 개정할 경우에는 적용일자 및 개정내용, 개정사유를 명시하여 현행 약관과 함께 제1항의 방법으로 개정약관 적용일자 적용일로부터 최소 7일 전부터 적용일자 전일까지 공지합니다. 다만 회원에게 불리하거나 중대한 사항에 관한 개정인 경우에는 사이트 초기 서비스화면 또는 그 연결화면 게재 외에 전자우편, 로그인시 동의창 등의 가능한 전자적인 수단으로 적용일자 30일 이전부터 적용일자 전일까지 같은 방법으로 공지합니다.

④ 제3항에 따라 공지된 적용일자 이후에 회원이 명시적으로 거부의 의사를 표시하지 아니하고 회사의 서비스를 계속 이용하는 경우에는 개정된 약관에 동의하는 것으로 봅니다.

⑤ 회원이 개정약관의 적용에 동의하지 않으면 회사는 해당 회원에 대해 개정약관의 내용을 적용할 수 없으며, 이 경우 회원은 언제든지 자유롭게 이용계약(아래 제8조 제1항에서 정의함. 이하 같음)을 해지할 수 있습니다. 다만, 기존약관을 적용할 수 없는 특별한 사정이 있는 경우에는 회사는 회원에게 고지 후 이용계약을 해지할 수 있습니다.

 

제4조 약관의 적용
① 회원은 회사가 제공하는 서비스를 이용함에 있어서 관련 법령을 준수하여야 하며, 이 약관의 규정을 들어 관련 법령 위반에 대한 면책을 주장할 수 없습니다.

② 회사는 사이트 내 개별 서비스 등에 대하여 별도의 이용약관 및 운영정책(이하 "개별 서비스 약관 등")을 둘 수 있으며, 개별 서비스 약관 등의 내용이 이 약관과 상충할 경우에는 개별 서비스약관 등이 우선하여 적용됩니다.

③ 약관에 정하지 아니한 사항이나 해석에 대해서는 개별 서비스약관 등 및 "전자상거래 등에서의 소비자보호에 관한 법률", "약관의 규제에 관한 법률", "정보통신망 이용촉진 및 정보보호 등에 관한 법률" 등 관련 법령에 따릅니다.</textarea>
        </div>
        <div class="row float-container">

            <label class="float-left w-50">
                <input oninput="checkAgree();" class="ck ck-required" type="checkbox" name="agree" value="type1">
                <span>동의합니다</span>
                <span class="red">*</span>
            </label>

            <div class="float-left w-50 right">
                <a href="#">전문 보기</a>
            </div>

        </div>

        <div class="row mt-30">
            <h2>제2절 서비스의 이용</h2>
        </div>
        <div class="row">
            <textarea class="solid-lines w-100 fix-size" rows="10" readonly>제5조 서비스의 제공
① 회사는 다음과 같은 서비스를 제공합니다.

프로젝트의 정보 게시 및 후원계약의 체결을 위한 플랫폼 제공
프로젝트 모금 업무 대행 및 기금액 전달
이에 부수되는 서비스 및 기타 회사가 정하는 업무
② 회사는 회원의 이용신청을 승낙하여 이용계약이 성립한 때부터 서비스를 개시합니다. 단 일부 서비스의 경우에는 지정된 일자부터 서비스를 개시합니다.

③ 일부 서비스에 대해서는 서비스 제공자의 요구에 따라 특정회원에게만 서비스를 제공할 수 있으며, 회사는 서비스를 일정범위로 분할하여 각 범위 별로 이용가능 시간을 별도로 정할 수 있습니다. 이 경우 그 내용을 사전에 공개합니다.

 

제6조 서비스의 중단 및 변경
① 천재지변, 국가비상사태 등 회사가 정상적인 서비스 제공이 불가능할 경우 일시적으로 서비스를 제한, 정지시킬 수 있으며 사전 또는 사후에 회원에게 중지사유 및 기간을 공지합니다.

② 회사는 긴급한 시스템의 보수점검, 교체 및 고장, 통신두절 또는 운영상 상당한 이유가 있는 경우 콘텐츠의 이용을 일시적으로 중단할 수 있습니다. 서비스 중지 1주일 전에 사이트에 고지 후 서비스를 중지할 수 있으며, 이 기간 동안 회원이 고지내용을 인지하지 못한 것에 대하여 회사는 책임을 부담하지 않습니다. 상당한 이유가 있을 경우 위 사전 고지기간은 감축되거나 생략될 수 있습니다. 다만, 회사가 사전에 통지할 수 없는 부득이한 사유가 있는 경우 사후에 고지할 수 있습니다.

③ 회사는 서비스 내용의 결정, 변경, 유지, 중단에 관한 포괄적인 권한을 가집니다. 회사는 영업의 폐지, 합병, 분할, 당해 서비스의 수익악화, 서비스 유지 등 상당한 이유가 있는 경우 제공하고 있는 전부 또는 일부 서비스를 변경하거나 중단할 수 있습니다.

④ 회사의 사정으로 서비스를 영구적으로 중단하여야 할 경우에 제2항을 준용합니다. 다만 이 경우 사전 고지기간은 1개월로 합니다.

⑤ 회사는 서비스 중지에 의하여 본 서비스에 보관 또는 전송된 메시지 및 기타 통신 메시지 등의 내용이 삭제된 경우, 전송되지 못한 경우 및 기타 통신 데이터의 손실이 있을 경우에 대하여 책임을 부담하지 않습니다.

⑥ 회사는 서비스의 이용에 필요한 경우 정기점검을 실시할 수 있으며, 정기점검시간은 서비스 제공화면에 공지한 바에 따릅니다.

⑦ 장기간 휴면 회원인 경우, 안내 메일 또는 공지사항 발표 후 1주일간의 통지기간을 거쳐 서비스 사용을 중지할 수 있습니다.

 

제7조 서비스의 이용료 등
① 회원의 서비스 가입은 무료이며, 특정 서비스 이용 시에만 수수료가 부과됩니다.

② 기타 서비스에 있어서 회사가 서비스 이용에 따른 수수료를 부과하고자 할 경우에 회사는 부과기준 및 기타 상세한 내용을 시행 7일 전에 홈페이지에 게재합니다.

 

제8조 이용계약의 체결
① 회사가 본 약관에 따라 제공하는 서비스를 이용하기 위하여 회사와 서비스이용계약(이하 "이용계약")을 체결하여 회원가입에 따른 회원의 자격을 부여받아야 합니다.

② 서비스를 이용하고자 하는 자(이하 "이용신청자")가 본 약관의 내용에 대하여 동의를 한 다음 회사가 제공하는 양식에 따라 정보입력을 하고 본인확인을 위한 인증절차를 이행하는 방법으로 회원가입신청을 하면 서비스에 대한 이용신청(이하 "이용신청")이 있는 것으로 보며, 이용신청에 대하여 회사가 승낙함으로써 이용계약이 체결됩니다.

③ 제2항의 회원가입신청 절차의 방법과 내용은 회원이 개인(자연인)인 경우와 법인인 경우에 따라 다를 수 있습니다.

④ 이용신청자는 회원가입에 있어 정확하고 완전한 정보를 입력해야 합니다. 회사는 관련 법령에 따라 이용신청자가 제1항에 따라 입력한 정보에 대한 사실 확인을 위하여 필요한 경우 이용신청자에게 증빙자료의 제출을 요청할 수 있으며, 이용신청자는 이에 따라 증빙자료를 제출하여야 합니다.

⑤ 회사는 다음에 해당하는 경우에는 이용신청을 승낙하지 아니할 수 있습니다.

이미 가입된 회원과 이메일 주소가 동일한 경우
제2항의 이용신청시 타인의 이메일 계정 또는 명의를 이용하여 이용신청을 한 경우
제2항의 이용신청 시 필요한 정보를 입력하지 않거나 허위의 정보를 기재한 경우
사회의 안녕 질서 혹은 미풍양속을 저해할 목적으로 신청했을 때
기타 회사가 정한 이용신청 요건이 미비되었을 때
위법 또는 부당한 목적으로 이용신청을 한 경우</textarea>
        </div>
        <div class="row float-container">

            <label class="float-left w-50">
                <input oninput="checkAgree();" class="ck ck-required" type="checkbox" name="agree" value="type2">
                <span>동의합니다</span>
                <span class="red">*</span>
            </label>

            <div class="float-left w-50 right">
                <a href="#">전문 보기</a>
            </div>

        </div>

        <div class="row mt-30">
            <h2>제3절 회원정보의 보호 및 관리</h2>
        </div>
        <div class="row">
            <textarea onkeydown="closeScreen3(event);" class="solid-lines w-100 fix-size textarea3" rows="10" readonly>제11조 개인정보보호
① 회사는 서비스 제공을 위하여 이용계약의 체결 시 필요한 최소한의 정보(이하 "회원정보")를 수집할 수 있으며, 그 외에도 수집목적 또는 이용목적을 밝혀 회원으로부터 정보를 수집할 수 있습니다. 이 경우 회사는 회원으로부터 정보수집에 대한 동의를 받으며, 회원은 정보제공에 동의를 한 이후에도 회사가 제공하는 양식에 따라 그 동의를 철회할 수 있습니다.

② 회원의 개인정보의 수집, 보호 및 사용에 대해서는 관련 법령 및 회사의 개인정보처리방침이 적용됩니다. 다만, 회사의 홈페이지 이외의 링크된 사이트에서는 회사의 개인정보처리방침이 적용되지 않습니다.

③ 회원은 언제든지 회사가 가지고 있는 자신의 개인정보에 대해 열람 및 오류정정을 요구할 수 있으며 회사는 이에 대해 지체 없이 필요한 조치를 취할 의무를 집니다. 회원이 오류의 정정을 요구한 경우에는 회사는 오류를 정정할 때까지 당해 개인정보를 이용하지 않습니다.

④ 회사는 정보통신망법 등 관련 법령 및 회사의 개인정보처리방침이 정하는 바에 따라 회원의 개인정보를 보호하기 위해 노력합니다. 회사의 개인정보 보호책임자 및 연락처는 아래와 같습니다.

성명: 야도란
전화번호: 02-5555-5555
이메일: support@doran.com
 

제12조 회원의 계정이메일과 비밀번호에 대한 의무
① 회원의 계정이메일과 비밀번호를 포함한 회원 정보에 관한 관리책임은 회원에게 있으며, 이를 소홀히 하여 발생한 모든 민∙형사상의 책임은 회원에게 있습니다.

② 회원은 사이트 내에서 회원정보를 관리 및 변경을 할 수 있으며 회원은 회원정보를 최신으로 유지하며 변동이 있을 경우 회원은 즉시 해당 정보를 수정해야 합니다. 다만, 서비스의 제공 및 관리를 위해 필요한 일부정보는 수정이 불가능할 수 있습니다.

③ 회원은 자신의 계정이메일 및 비밀번호를 포함한 회원정보를 제3자에게 양도, 대여, 이용하도록 해서는 안됩니다.

④ 회원이 자신의 회원정보를 도난당하거나 제3자가 사용하고 있음을 인지한 경우에는 즉시 회사에 통보하고 회사의 안내가 있는 경우에는 그에 따라야 합니다.

⑤ 회원이 제4항에 따른 통지를 하지 않거나 회사의 안내에 응하지 아니하여 발생하는 모든 불이익에 대한 책임은 회원에게 있습니다.</textarea>
        </div>
        <div class="row float-container">

            <label class="float-left w-50">
                <input class="ck" type="checkbox" name="agree" value="type3">
                <span>(선택) 동의합니다</span>
            </label>

            <div class="float-left w-50 right">
                <a href="#" onclick="return fullScreen3();">전문 보기</a>
            </div>

        </div>

        <div class="row mt-50 mb-50">
            <button class="btn btn-positive w-100" onclick=" location.href='join' " disabled>다음 단계로 이동</button>
        </div>
    </div>
    
</body>
</html>