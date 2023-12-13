package com.sh.mvc._enum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EnumTest {
    public enum Type {
        M,A;
    }
    public enum Gender {
        M,F;
    }
    public class Member {
        String id;
        Type type; // M, A
        Gender gender; // M, F

        public Member(String id, Type type, Gender gender) {
            this.id = id;
            this.type = type;
            this.gender = gender;
        }
    }

    @Test
    public void test1(){
        Member member = new Member("honggd", Type.M, Gender.M);
        // Member member = new Member("honggd", Gender.M, Gender.M); // Compile Error
        assertThat(member.type).isEqualTo(Type.M);
        assertThat(member.gender).isEqualTo(Gender.M);

        Member admin = new Member("sinsa", Type.A, Gender.F);
        assertThat(admin.type).isEqualTo(Type.A);
        assertThat(admin.gender).isEqualTo(Gender.F);
    }

    @DisplayName("Enum객체는 하나만 만들어져 공유된다. ")
    @Test
    public void test2(){
        Type M1 = Type.M;
        Type M2 = Type.M;
        assertThat(M1).isEqualTo(M2);
        assertThat(M1 == M2).isTrue();
    }

    @DisplayName("Enum을 String으로 변환")
    @Test
    public void test3(){
        Gender F = Gender.F;
        String f = F.name(); // String "F"
        assertThat(f)
                .isEqualTo("F")
                .isEqualTo(Gender.F.name());
    }

    @DisplayName("String을 Enum으로 변환")
    @Test
    public void test4(){
        Gender M = Gender.valueOf("M");
        assertThat(M).isEqualTo(Gender.M);
    }
}
