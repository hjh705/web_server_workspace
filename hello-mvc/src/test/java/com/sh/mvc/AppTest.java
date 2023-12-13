package com.sh.mvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**

 JUnit 테스트 구성요소
 fixture method : 매 테스트 전후작업
 모든 테스트는 독립적으로 진행(매번 새로운 테스트객체를 생성후 진행)
 @BeforeClass, @AfterClass (static)
 @BeforeEach, @AfterEach
  *
 단정문 (assertion)
 코드실행결과는 이것이다~ 단정
 assertThat(...)
 assertNotNull(...)
 ...
  *
 TestRunner : 테스트주체
 */

public class AppTest {
    App app = new App();

    @DisplayName("com.sh.mvc.App#sum메소드 - 두수의 합을 반환하는지 테스트")
    @Test
    public void test(){
        int result = app.sum(10, 20);
        assertThat(result).isEqualTo(30);

        result = app.sum(30, 5);
        assertThat(result).isEqualTo(35);

    }

    @DisplayName("com.sh.mvc.App#random는  1 ~ 100사이의 정수를 반환하는지 테스트")
    @Test
    public void test2(){
        int n = app.random();

        assertThat(n)
                .isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(100);
    }

}