package com.kh.doran.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.kh.doran.interceptor.AdminInterceptor;
import com.kh.doran.interceptor.MemInterceptor;
import com.kh.doran.vo.MemReplyOwnerCheckInterceptor;

//스프링 설정파일
//- 프로그램 동작 방식과 관련된 설정일 경우 특정 인터페이스를 상속 받아야 함
//- interface WebMvcConfigurer
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{
	
	@Autowired
	private MemInterceptor memInterceptor;
	
	@Autowired
	private MemReplyOwnerCheckInterceptor ownerCheckInterceptor2;
	
//	@Autowired
//	private AdminInterceptor adminInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//(참고) 등록 코드 작성 순으로 실행됨

		//회원용 인터셉터
		registry.addInterceptor(memInterceptor)
					.addPathPatterns(//인터셉터가 감시할 주소
							"/mem/**",//회원 전체
							"/board/write", //글쓰기
							"/mypage/**",
							"/doran-q/write"
					)
					.excludePathPatterns(//위의 주소에서 제외할 주소 (비회원)
							"/mem/join*",//회원가입
							"/mem/login",//로그인
							"/mem/findEmail",//이메일 찾기
							"/mem/findPw",//비밀번호 찾기
							"/mem/goodbyeResult", //탈퇴 결과
							"doran-q/list" //1:1 문의 목록
					);
		
		//관리자만 수정 / 삭제를 할 수 있도록 검사하는 인터셉터
//				registry.addInterceptor(adminInterceptor)
//							.addPathPatterns(//인터셉터가 감시할 주소
//									"/admin/**"
//							);
		
		//소유자만 댓글 수정/삭제 가능하도록 검사하는 인터셉터
		registry.addInterceptor(ownerCheckInterceptor2)
					.addPathPatterns(
							"/board/reply/edit",
							"/board/reply/delete"
					);
		
	}
}