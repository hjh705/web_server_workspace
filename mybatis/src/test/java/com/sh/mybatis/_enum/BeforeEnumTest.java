package com.sh.mybatis._enum;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BeforeEnumTest {
    public static final String TYPE_MEMBER = "M";
    public static final String TYPE_ADMIN = "A";
    public static final String GENDER_MALE = "M";
    public static final String GENDER_FEMALE = "F";
    public class Member {
        String id;
        String type; // M, A
        String gender; // M, F

        public Member(String id, String type, String gender) {
            this.id = id;
            this.type = type;
            this.gender = gender;
        }
    }

    @Test
    public void test1(){
        // Member member = new Member("honggd", TYPE_MEMBER, GENDER_MALE);
        Member member = new Member("honggd", GENDER_MALE, GENDER_MALE); // String으론 같은 이름을 가진 오류를 잡아낼 수 없다
        assertThat(member.type).isEqualTo(TYPE_MEMBER); //assertThat(member) 까지 적고 import 첫번째
        assertThat(member.gender).isEqualTo(GENDER_MALE);

        Member admin = new Member("sinsa", TYPE_ADMIN, GENDER_FEMALE);
        assertThat(admin.type).isEqualTo(TYPE_ADMIN);
        assertThat(admin.gender).isEqualTo(GENDER_FEMALE);
    }
}
