package com.sh.mvc.member.model.service;

import com.sh.mvc.member.model.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class MemberServicePagingTest {
    private MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        this.memberService = new MemberService();
    }

    @DisplayName("MemberService 객체는 null이 아니다")
    @Test
    public void test1(){
        assertThat(memberService).isNotNull();
    }

    @DisplayName("회원목록 페이징은 10건씩 조회되어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13})
    public void test2(int page){
        assertThat(page).isGreaterThan(0);
        System.out.println(page);
        int limit = 10;
        Map<String, Object> param = Map.of("page", page, "limit", limit);
        List<Member> members = memberService.findAll(param);
        System.out.println(members);
        assertThat(members).isNotNull();
        assertThat(members.size()).isLessThanOrEqualTo(limit);
    }

    @DisplayName("전체 회원수가 정상 조회된다.")
    @Test
    public void test3(){
        int totalCount = memberService.getTotalCount();
        System.out.println(totalCount);
        assertThat(totalCount).isGreaterThan(0);
    }
}
