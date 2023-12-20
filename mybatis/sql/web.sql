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
    hobby varchar2(500),
    point number default 1000,
    reg_date date default sysdate,
    constraints pk_member_id primary key(id),
    constraints uq_member_email unique(email),
    constraints ck_member_gender check(gender in('M','F')),
    constraints ck_member_role check(role in('U','A')),
    constraints ck_member_point check(point>=0)
);


--alter table member
--add constraints uq_member_email unique(email);

insert into member 
values('abcde','1234','아무개','U','M', to_date('20000909','yyyymmdd'), 'abcde@naver.com', '01012340909', '운동,등산,독서', default, default);

insert into member 
values('qwerty','1234','쿼띠이','U','F', to_date('19900109','yyyymmdd'), 'qwerty@naver.com', '01012341234', '운동,등산', default, default);

--관리자계정 추가
insert into member 
values('admin','1234','관리자','A','M', to_date('19971020','yyyymmdd'), 'admin@naver.com', '01044441234', '게임,독서',default, default);


select * from member;
--delete from member where id in ('sejong');

--update
--    member
--set
--    password = '9FipNtrDaOHuV+j/mI/im1JzeI4r04M496uRbGxYJagaVotqCfHIfBzDd5OZdFoOJPI52pbZaE+iWvLFFZHBhg=='
--where
--    id = 'qwerty';


-- rnum을 통한 페이징 
select
    *
from
    (select
        rownum rnum,
        m.*
    from (
        select
            *
        from
            member
        order by
            reg_date desc) m 
    )
where
    rnum between 11 and 20;


select
    *
from(  
    select
        row_number() over(order by reg_date desc) rnum,
        m.*
    from
        member m
    )
where
    rnum between 1 and 10;

-- mybatis는 RowBounds를 사용하면 페이징 처리를 대신 해준다 
-- page, limit 값을 mybatis에게 줘야만 한다  
select
    *
from
    member
order by
    reg_date desc;

--전체 회원수
select count(*) from member;

-- 게시판 테이블
create table board(
    id number,
    title varchar2(500),
    member_id varchar2(15),
    content varchar2(4000),
    read_count number default 0,
    reg_date date default sysdate,
    constraints pk_board_id primary key(id),
    constraints fk_board_member_id foreign key(member_id) references member(id) on delete set null
);
create sequence seq_board_id;

-- 첨부파일을 서버컴퓨터에 저장, 저장된 파일에 대한 정보만 db테이블에 저장한다. 
create table attachment (
    id number,
    board_id number not null,
    original_filename varchar2(255) not null,-- 사용자가 업로드한 파일명
    renamed_filename varchar2(255) not null, -- 저장된 파일명(uuid) - univeral unique id
    reg_date date default sysdate,
    constraints pk_attachment_id primary key(id),
    constraints fk_attachment_board_id foreign key (board_id) references board(id) on delete cascade
);
create sequence seq_attachment_id;

select * from board order by id desc;
select * from attachment order by id desc;
select count(*) from board;
select * from board where id = 0;
delete from board where id = 64;
delete from attachment where id = 60;

-- 첨부파일이 있는 게시글 조회 
select
    b.*,
    (select count(*) from attachment where board_id = b.id) attach_count
from
    board b;


-- 게시글 상세보기
-- 1. board 조회 + attachment 조회 
select * from board where id = 60;
select * from attachment where board_id = 60;

-- 2. join 쿼리
-- left join 해야 첨부파일이 없는 게시글도 전부 조회된다 
select 
    b.*,
    a.id attach_id,
    a.board_id board_id,
    a.original_filename,
    a.renamed_filename,
    a.reg_date attach_reg_date
from
    board b left join attachment a
        on b.id = a.board_id
where
    b.id = 60;
    




commit;
       