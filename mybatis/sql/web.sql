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
delete from board where id = 63;
delete from attachment where id = 60;

-- 첨부파일이 있는 게시글 조회 
select
    b.*,
    (select count(*) from attachment where board_id = b.id) attach_count
from
    board b;


-- 게시글 상세보기
-- 1. board 조회 + attachment 조회 
select * from board where id = 70;
select * from attachment where board_id = 70;

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
    b.id = 70;
    
select 
    b.*,
    m.name member_name,
    a.id attach_id,
    a.board_id board_id,
    a.original_filename,
    a.renamed_filename,
    a.reg_date attach_reg_date
from
    board b 
        left join member m
            on b.member_id = m.id
        left join attachment a
            on b.id = a.board_id
where
    b.id = 70;

-- 댓글 기능 구현
create table board_comment (
    id number,
    board_id number,
    member_id varchar2(20),
    content varchar2(2000),
    comment_level number default 1,-- 댓글인 경우 1, 대댓글인 경우 2
    parent_comment_id number,-- 댓글인 경우 null, 대댓글인 경우 부모 댓글 id 
    reg_date date default sysdate,
    constraints pk_board_comment_id primary key(id),
    constraints fk_board_comment_board_id foreign key(board_id) references board(id) on delete cascade,
    constraints fk_board_comment_member_id foreign key(member_id) references member(id) on delete set null,
    constraints fk_board_parent_comment_id foreign key(parent_comment_id) references board_comment(id) on delete cascade -- 댓글 삭제시 대댓글까지 삭제되도록 
    
);
create sequence seq_board_comment_id;

--59번 게시판 댓글 데이터 
insert into board_comment
values(seq_board_comment_id.nextval, 59, 'qwerty', '에베베베베', default, null, default);
insert into board_comment
values(seq_board_comment_id.nextval, 59, 'opqr', '베베벱벱베', default, null, default);
insert into board_comment
values(seq_board_comment_id.nextval, 59, 'abcde', '호로롤로로로로', default, null, default);
--대댓글 
insert into board_comment
values(seq_board_comment_id.nextval, 59, 'admin', '대댓글따림ㅁㅁ', 2, 1, default);
insert into board_comment
values(seq_board_comment_id.nextval, 59, 'admin', '베레베레베렙ㅂ', 2, 2, default);
insert into board_comment
values(seq_board_comment_id.nextval, 59, 'abcde', '델데델데데베뎁', 2, 2, default);
insert into board_comment
values(seq_board_comment_id.nextval, 59, 'admin', '로로로로롤호로롤', 2, 3, default);

select * from board_comment;

-- 계층형 쿼리
-- 행과 행을 부모/자식 관계로 연결해서 tree 구조의 순서로 결과집합을 반환해줌 
-- 계층 구조의 데이터 표현에 적합. 댓글, 조직도, 메뉴 등 

-- start with 루트 부모행을 지정하는 조건절
-- connect by 부모/자식을 연결하는 조건절. prior 를 부모행 컬럼 앞에 작성 
-- level 이라는 가상 컬럼(계층 레벨) 제공 
-- order silblings by 컬럼 계층형 쿼리 전용 정렬 
select
    *
from
    board_comment
where
    board_id = 59
start with 
    comment_level =1
connect by
    prior id = parent_comment_id
order siblings by -- 그냥 order by 하면 결과가 뭉개짐 
    id;

-- sql 에서 들여쓰기 해서 보기 
select
    lpad(' ', (level - 1) * 5)|| content,
    member_id,
    level
from
    board_comment
where
    board_id = 59
start with
    comment_level = 1
connect by
    parent_comment_id = prior id;

commit;

-- 사진 게시판 (더보기 페이징)
create table photo (
    id number,
    member_id varchar2(15),
    content varchar2(4000),
    original_filename varchar2(100),
    renamed_filename varchar2(100),
    read_count number default 0,
    reg_date date default sysdate,
    constraints pk_photo_id primary key (id),
    constraints fk_photo_id foreign key(member_id) references member(id) on delete set null
);
--drop table photo;
create sequence seq_photo_id;

Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','하와이 가는 하늘길~','adult-adventure-aircraft-443430.jpg','20220418_174158873_108.jpg',107,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','프랑스산 와인 시음회 :)','adult-alcohol-blur-290316.jpg','20220418_174412447_349.jpg',18,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','adventure-albay-clouds-672358.jpg','20220418_174453770_556.jpg',99,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','airplane-apartment-architecture-364245.jpg','20220418_174505657_4.jpg',12,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','america-architecture-billboards-461903.jpg','20220418_174516697_101.jpg',192,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','ancient-architecture-building-415980.jpg','20220418_174527327_327.jpg',82,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','arch-architecture-art-332208.jpg','20220418_174539548_250.jpg',73,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','자나깨나 차조심, 트램조심','architecture-avenue-blur-258447.jpg','20220418_174601509_281.jpg',103,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','architecture-background-buildings-698604.jpg','20220418_174616171_833.jpg',102,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','architecture-barcelona-blue-sky-819764.jpg','20220418_174652399_241.jpg',to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','architecture-basilica-buildings-326709.jpg','20220418_174743637_226.jpg',88,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','architecture-building-door-206767.jpg','20220418_174800692_837.jpg',234,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','atmosphere-beautiful-cloudburst-531318.jpg','20220418_174814411_4.jpg',81,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','이과수이과수','back-beach-beautiful-670060.jpg','20220418_174839106_197.jpg',77,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','bicycle-tour-bicycles-bicyclists-17729.jpg','20220418_174856071_779.jpg',222,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','billboard-business-city-733778.jpg','20220418_174910053_722.jpg',204,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','black-clouds-dark-420885.jpg','20220418_174924429_849.jpg',103,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','boulders-clouds-daylight-464440.jpg','20220418_174941759_108.jpg',47,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','여행은 즐거워','capital-cathedral-city-6502.jpg','20220418_174957191_842.jpg',52,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','퐈이아아아아아','evening-fair-fire-65297.jpg','20220418_175019576_86.jpg',45,to_date('18-04-22','DD-MM-RR'));
Insert into WEB.PHOTO (ID,MEMBER_ID,CONTENT,ORIGINAL_FILENAME,RENAMED_FILENAME,READ_COUNT,REG_DATE) values (SEQ_PHOTO_ID.NEXTVAL,'abcde','소나무야','375px-Pinus_densiflora_Kumgangsan.jpg','20220418_125936088_36.jpg',23,to_date('18-04-22','DD-MM-RR'));

commit;

select * from photo order by id desc;

-- 알림 테이블 생성
create table notification (
    id number,
    member_id varchar2(15) not null,
    type varchar2(100) not null,
    content varchar2(2000) not null,
    checked number default 0,
    reg_date date default sysdate,
    constraints pk_notification_id primary key (id),
    constraints fk_notification_member_id foreign key(member_id) references member(id) on delete set null,
    constraints ck_notification_type check (type in ('NEW_COMMENT', 'DM', 'NEW_FOLLOWER', 'NEW_CONTENT'))
);
create sequence seq_notification_id;
drop sequence seq_notificaation_id;

insert into notification
values (seq_notification_id.nextval, 'qwerty', 'NEW_COMMENT', 'xxx 게시글에 새 댓글이 달렸습니다.', default, default);
insert into notification
values (seq_notification_id.nextval, 'qwerty', 'DM', 'admin 이 DM을 보냈습니다.', default, default);
insert into notification
values (seq_notification_id.nextval, 'abcde', 'NEW_CONTENT', 'qwerty가 새 게시글을 등록했습니다.', default, default);

select * from notification;

-- 조회 : 특정 회원의 확인 안된 알림 조회
select 
    *
from
    notification
where
    member_id = 'qwerty'
    and
    checked = 0
order by
    id desc;
