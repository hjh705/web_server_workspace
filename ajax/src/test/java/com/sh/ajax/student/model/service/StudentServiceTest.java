package com.sh.ajax.student.model.service;

import com.sh.ajax.student.model.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 테스트항목
 * - fixture 자체테스트
 * - 학생이름 검색
 * - "이" 검색시 이민정, 이재준
 * - "진" 고혜진, 오우진, 정진우, 천무진
 * - "한" 한보경, 한승훈, 한준희
 *
 * @ParameterizedTest
 * @ValueSource
 */
public class StudentServiceTest {
    // fixture
    StudentService studentService;

    @BeforeEach
    public void setUp(){
        this.studentService = new StudentService();
    }

    @DisplayName("StudentService객체는 null이 아니다")
    @Test
    public void test1(){
        assertThat(studentService).isNotNull();
    }

    @DisplayName("이름 검색")
    @ParameterizedTest
    @ValueSource(strings = {"이", "진", "한"})
    public void test2(String name) {
        // given
        assertThat(name).isNotNull();
        // when
        List<Student> students = studentService.findByName(name);
        System.out.println(students);
        // then
        assertThat(students)
                .isNotNull()
                .allSatisfy((student) -> {
                    assertThat(student.getId()).isNotNull().isNotZero();
                    assertThat(student.getName()).isNotNull().contains(name);
                });
    }


}
