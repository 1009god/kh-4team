create table mem(
mem_no number primary key,
mem_email varchar2(50) unique not null,
mem_pw varchar2(16) not null check(regexp_like(mem_pw, '^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$])[A-Za-z0-9!@#$]{8,16}$')),
mem_nick varchar2(30) not null unique check(regexp_like(mem_nick, '^[가-힣][가-힣0-9]{0,9}$')),
mem_tel char(11) check(regexp_like(mem_tel, '^010[0-9]{8}$')),
mem_join_date date default sysdate not null,
mem_route varchar2(100)
);


회원테이블
이메일 정규표현식 안 넣었음
닉네임 정규표현식검사 일단 뺐음
비밀번호 검사 대문자1개 소문자1개 특수문자!@#$중 1개 숫자1개 8~16자
전화번호검사 수업시간에했던거
가입경로는 잘 모르겠어서 varchar2(100)만 걸었습니다 select 걸어서 옵션선택하면 거기 있는 value값 들어가는건가요?? 