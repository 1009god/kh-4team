<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			</section>
		</div>
	
		<hr>
		<footer>
			<div class="float-container">
				<div class="float-left w-33">
					<div class="row">
						<h2>회사소개</h2>	
					</div>
					<div class="row">
						이용약관
					</div>
					<div class="row">
						공지사항
					</div>
					<div class="row">
						서비스소개
					</div>
					<div class="row">
						채용
					</div>
					<div class="row">
						헬프 센터
					</div>
				</div>
			
				<div class="float-left w-33">
					<div class="row">
						<h2>지점 연락처</h2>	
					</div>
					<div class="row">
						당산 지원 : 000-0000-0000
					</div>
					
				</div>
				<div class="float-left w-33">
					<div class="row">
						<h2>상태창</h2>	
					</div>
					<div class="row">
						loginId : ${sessionScope.loginId}
					</div>
					<div class="row">
						로그인 : ${sessionScope.loginId != null}
					</div>
				</div>
			</div>
		</footer>
	
	</main>
    
    </body>
</html>


    		
    		
    		<%--  
    		사용자 세션과 관련된 정보 출력 (지울 예정)
    			세션 ID : ${pageContext.session.getId() }<br>
    			신규세션 : ${pageContext.session.isNew()}<br>
    		--%>


















