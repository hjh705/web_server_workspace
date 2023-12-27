create table student (
    id number,
    name varchar2(100),
    constraints pk_student_id primary key(id)
);
create sequence seq_student_id;

insert into student values(seq_student_id.nextval, '고혜진');
insert into student values(seq_student_id.nextval, '김명준');
insert into student values(seq_student_id.nextval, '김정효');
insert into student values(seq_student_id.nextval, '성민준');
insert into student values(seq_student_id.nextval, '오승현');
insert into student values(seq_student_id.nextval, '오우진');
insert into student values(seq_student_id.nextval, '유정호');
insert into student values(seq_student_id.nextval, '이민정');
insert into student values(seq_student_id.nextval, '이재준');
insert into student values(seq_student_id.nextval, '임초임');
insert into student values(seq_student_id.nextval, '정승범');
insert into student values(seq_student_id.nextval, '정용준');
insert into student values(seq_student_id.nextval, '정진우');
insert into student values(seq_student_id.nextval, '천무진');
insert into student values(seq_student_id.nextval, '한보경');
insert into student values(seq_student_id.nextval, '한승훈');
insert into student values(seq_student_id.nextval, '한준희');

commit;

-- celeb 테이블
create table celeb (
    id number,
    name varchar2(100) not null,
    profile varchar2(200) default 'default.png',
    type varchar2(50), -- actor, model, singer...
    constraints pk_celeb_id primary key(id),
    constraints ck_celeb_type check (type in ('ACTOR', 'SINGER', 'MODEL', 'ENTERTAINER', 'COMEDIAN'))
);

create sequence seq_celeb_id;

delete from celeb where id = 17;

select * from celeb;

insert into celeb values(seq_celeb_id.nextval, '박보검', '박보검.jpg', 'ACTOR');
insert into celeb values(seq_celeb_id.nextval, '쥴리아 로버츠', 'Julia_Roberts.jpg', 'ACTOR');
insert into celeb values(seq_celeb_id.nextval, '맷 데이먼', 'Matt_Damon.jpg', 'ACTOR');
insert into celeb values(seq_celeb_id.nextval, '차은우', '차은우.png', 'SINGER');
insert into celeb values(seq_celeb_id.nextval, '춘리', '춘리.png', 'MODEL');
insert into celeb values(seq_celeb_id.nextval, '카리나', '카리나.png', 'SINGER');
insert into celeb values(seq_celeb_id.nextval, '정재영', '정재영.jpg', 'ACTOR');
insert into celeb values(seq_celeb_id.nextval, '아이유', '아이유.jpg', 'SINGER');
insert into celeb values(seq_celeb_id.nextval, '오킹', '오킹.jpg', 'ENTERTAINER');
insert into celeb values(seq_celeb_id.nextval, '김고은', '김고은.jpg', 'ACTOR');
insert into celeb values(seq_celeb_id.nextval, '매즈 미켈슨', '매즈미켈슨.jpg', 'ACTOR');

commit