-- web계정 생성(관리자)
alter session set "_oracle_script" = true;

create user web
identified by web
default tablespace users;

grant connect, resource to web;

alter user web quota unlimited on users;


-- web계정 시작
create table member (
    id varchar2(15),
    password varchar2(300) not null,
    name varchar2(100) not null,
    role char(1) default 'U' not null,
    gender char(1),
    birthday date,
    email varchar2(100),
    phone char(11),
    bobby varchar2(500),
    point number default 1000,
    reg_date date default sysdate,
    constraint pk_member_id primary key(id),
    constraint ck_member_gender check(gender in('M','F')),
    constraint ck_member_role check(role in('U','A')),
    constraint ck_member_point check(point>=0)
);

insert into member 
values('abcde','1234','아무개','U','M', to_date('20000909','yyyymmdd'), 'abcde@naver.com', '01012340909', '운동,등산,독서', default, default);

insert into member 
values('qwerty','1234','쿼띠이','U','F', to_date('19900109','yyyymmdd'), 'qwerty@naver.com', '01012341234', '운동,등산', default, default);

--관리자계정 추가
insert into member 
values('admin','1234','관리자','A','M', to_date('19971020','yyyymmdd'), 'admin@naver.com', '01044441234', '게임,독서',default, default);


select * from member;
commit;