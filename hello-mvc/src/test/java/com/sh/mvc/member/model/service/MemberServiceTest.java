package com.sh.mvc.member.model.service;

import com.sh.mvc.member.model.entity.Gender;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/***
 * - 테스트는 전체 실행 했을 때 각 test들이 전부 작동할 수 있도록 독립적으로 짠다
 * - 테스트가 실행되는 순서는 순차적이 아니다.
 * - DML 관련 테스트의 경우 작동 확인 후 @Disabled 해놓도록 한다.
 */

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        this.memberService = new MemberService();
    }

    @DisplayName("MemberService객체는 null이 아니다.")
    @Test
    public void test1(){
        assertThat(memberService).isNotNull();
    }

    /**
     * mybatis는 ResultSet의 데이터를 vo클래스 객체로 자동변환한다.
     * - 컬럼명과 필드명이 일치해야만 한다. (mybatis-config.xml에 언더스코어 <-> 카멜케이싱 호환 설정 필수)
     * - varchar2 / char <-> String
     * - number <-> int / double
     * - date <-> java.util.date(기본값), java.time.LocalDate
     * - number(0|1) <-> boolean
     * - varchar2/char <-> Enum
     */
    @DisplayName("존재하는 회원이 정상적으로 조회된다.")
    @Test
    public void test2(){
        Member member = memberService.findById("abcde");
        System.out.println(member);
        // 객체
        assertThat(member).isNotNull();
        // 필드
        assertThat(member.getId()).isNotNull();
        assertThat(member.getPassword()).isNotNull();
        assertThat(member.getName()).isNotNull();
        assertThat(member.getRole()).isNotNull();

    }

    @DisplayName("존재하지 않는 회원은 NULL이 반환되어야 한다.")
    @Test
    public void test3(){
        Member member = memberService.findById("qwwwqqdfeu");
        assertThat(member).isNull();
    }

    @DisplayName("회원 전체 조회")
    @Test
    public void test4(){
        List<Member> members = memberService.findAll();
        assertThat(members)
                .isNotNull()
                .isNotEmpty();
        // Consumer 타입 람다식 : 매개변수가 하나 있고, 리턴 타입은 없음.
        members.forEach((member) -> {
            System.out.println(member);
            assertThat(member.getId()).isNotNull();
            assertThat(member.getPassword()).isNotNull();
            assertThat(member.getName()).isNotNull();
            assertThat(member.getRole()).isNotNull();
        });

    }
    @DisplayName("회원 이름 검색")
    @Test
    public void test5(){
        String keyword = "무개";
        List<Member> members = memberService.findByName(keyword);
        assertThat(members)
                .isNotNull()
                .isNotEmpty();
        members.forEach((member) -> assertThat(member.getName()).contains(keyword));
    }
    @DisplayName("성별 검색")
    @Test
    public void test6(){
        String gender = "M";
        List<Member> members = memberService.findByGender(gender);
        assertThat(members)
                .isNotNull()
                .isNotEmpty();
        // Gender enum 객체의 실제값 : name();
        // members.forEach((member) -> assertThat(member.getGender().name()).isEqualTo(gender)); // String 타입 비교
        members.forEach((member) -> assertThat(member.getGender()).isEqualTo(Gender.valueOf(gender))); // enum 비교
    }
    @Disabled //전체 테스트에서 제외 (다시 실행하면 이미 값이 들어가있어서 오류가 발생함)
    @DisplayName("회원가입")
    @Test
    public void test7(){
        String id = "honggd";
        String password = "1234";
        String name = "홍길동";

        Member member = new Member(
                id, password, name, Role.U, Gender.M,LocalDate.of(1999,9,9)
                , "honggd@naver.com", "01012344321", Arrays.asList("게임", "독서"), 0,null);
        int result = memberService.insertMember(member);
        assertThat(result).isEqualTo(1);

        Member member2 = memberService.findById(id);
        assertThat(member2).isNotNull();
        assertThat(member2.getId()).isEqualTo(id);
        assertThat(member2.getPassword()).isEqualTo(password);
        assertThat(member2.getName()).isEqualTo(name);
    }
    @Disabled
    @DisplayName("회원 가입시 오류 체크")
    @Test
    public void test8(){

        Member member = new Member(
                "sinsa", null, "신사임당", Role.U, Gender.M,LocalDate.of(1999,9,9)
                , "honggd@naver.com", "01012344321", Arrays.asList("게임", "독서"), 0,null);
    //     Cause: java.sql.SQLIntegrityConstraintViolationException:
    //     ORA-01400: NULL을 ("WEB"."MEMBER"."PASSWORD") 안에 삽입할 수 없습니다
        Throwable th = catchThrowable(() -> {
            int result = memberService.insertMember(member);
        });
        assertThat(th).isInstanceOf(Exception.class);
        //오류가 발생했을 때 이를 catch하면서 오류를 없앰
    }
    @Disabled
    @DisplayName("회원정보 수정")
    @Test
    public void test9(){
        //given 주어진 상황 작성
        String id = "honggd";
        Member member = memberService.findById(id);

        //when 업무로직 작성
        String newName = member.getName() + "길동";
        Gender newGender = null;
        LocalDate newBirthday;
        newBirthday = LocalDate.of(2000,1,1);
        String newEmail = "honggd@gmail.com" ;
        String newPhone = "01023213242";

        member.setName(newName);
        member.setGender(newGender);
        member.setBirthday(newBirthday);
        member.setEmail(newEmail);
        member.setPhone(newPhone);

        int result = memberService.updateMember(member);
        assertThat(result).isGreaterThan(0);

        //then 검증코드
        Member member2 = memberService.findById(id);
        assertThat(member2.getName()).isEqualTo(newName);
        assertThat(member2.getGender()).isEqualTo(newGender);
        assertThat(member2.getBirthday()).isEqualTo(newBirthday);
        assertThat(member2.getEmail()).isEqualTo(newEmail);
        assertThat(member2.getPhone()).isEqualTo(newPhone);
    }

    @Disabled
    @DisplayName("회원 비밀번호 수정")
    @Test
    public void test10(){
        String id = "honggd";
        Member member = memberService.findById(id);
        String newPassword = "4321";
        // Dao 단에서 무조건 하나의 값만 받기 때문에 하나만 바꾸더라도 객체 하나로 묶어서 주는 것이 좋다.

        member.setPassword(newPassword);

        int result = memberService.changePassword(member);

        assertThat(result).isGreaterThan(0);
        Member member2 = memberService.findById(id);
        assertThat(member2.getPassword()).isEqualTo(newPassword);

    }
    @Disabled
    @DisplayName("회원 권한 수정")
    @Test
    public void test11(){
        String id = "honggd";
        Member member = memberService.findById(id);
        Role newRole = Role.A;
        member.setRole(newRole);

        int result = memberService.updateMemberRole(member);

        assertThat(result).isGreaterThan(0);
        Member member2 = memberService.findById(id);
        assertThat(member.getRole()).isEqualTo(newRole);
    }

    @Disabled
    @DisplayName("회원 삭제")
    @Test
    public void test12(){
        String id = "honggd";
        Member member = memberService.findById(id);
        assertThat(member).isNotNull();

        int result = memberService.deleteMember(id);
        assertThat(result).isGreaterThan(0);

        Member member2 = memberService.findById(id);
        assertThat(member2).isNull();
    }
}