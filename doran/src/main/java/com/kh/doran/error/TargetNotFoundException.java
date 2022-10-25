package com.kh.doran.error;

import lombok.NoArgsConstructor;

//사용자 지정 예외 클래스
//- jvm 이 인지하지 못하지만 문제가 되는 상황을 알려주기 위한 클래스
//- 상속으로 자격 획득
//- 아 없구나-
//- 런타임 익셉션을 상속 받으면 추가 예외처리 생략 가능

@NoArgsConstructor
//public class TargetNotFoundException extends Exception 
public class TargetNotFoundException extends RuntimeException {  //실행 중 발생하는 예외처리들
	public TargetNotFoundException(String message) {
		super(message);
	}
}
