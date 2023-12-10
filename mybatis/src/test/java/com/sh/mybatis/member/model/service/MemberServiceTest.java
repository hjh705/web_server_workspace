package com.sh.mybatis.member.model.service;

import com.sh.mybatis.member.model.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

}