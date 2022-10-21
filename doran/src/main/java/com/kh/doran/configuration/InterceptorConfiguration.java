//package com.kh.doran.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.kh.doran.interceptor.MemberInterceptor;
//
////스프링 설정파일
////- 프로그램 동작 방식과 관련된 설정일 경우 특정 인터페이스를 상속 받아야 함
////- interface WebMvcConfigurer
//@Configuration
//public class InterceptorConfiguration implements WebMvcConfigurer{
//	
//	@Autowired
//	private MemberInterceptor memberInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		//(참고) 등록 코드 작성 순으로 실행됨
//
//		//회원용 인터셉터
//		registry.addInterceptor(memberInterceptor)
//					.addPathPatterns(//인터셉터가 감시할 주소
//							"/mem/**"//회원 전체
//					)
//					.excludePathPatterns(//위의 주소에서 제외할 주소
//							"/mem/join*",//회원가입
//							"/mem/login"//로그인
//					);
//		
//		
//	}
//}