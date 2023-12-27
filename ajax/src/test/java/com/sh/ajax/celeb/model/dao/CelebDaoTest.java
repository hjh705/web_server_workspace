package com.sh.ajax.celeb.model.dao;

import com.sh.ajax.celeb.model.entity.Celeb;
import com.sh.ajax.celeb.model.entity.Type;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.sh.ajax.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

public class CelebDaoTest {
    CelebDao celebDao;
    SqlSession session;

    @BeforeEach
    public void setUp() {
        // fixture 생성
        this.celebDao = new CelebDao();
        this.session = getSqlSession();
    }

    @AfterEach
    public void tearDown() {
        // fixture 해제
        // session rollback처리
        this.session.rollback();
        this.session.close();
    }

    @DisplayName("Fixture 확인")
    @Test
    void test() {
        assertThat(session).isNotNull();
        assertThat(celebDao).isNotNull();
    }

    @DisplayName("Celeb 전체조회")
    @Test
    void test1() {
        // given
        // when
        List<Celeb> celebs = celebDao.findAll(session);
        // then
        assertThat(celebs)
                .isNotNull()
                .allSatisfy((celeb) -> {
                    assertThat(celeb.getId()).isNotNull().isNotZero();
                    assertThat(celeb.getName()).isNotNull();
                    assertThat(celeb.getProfile()).isNotNull();
                    assertThat(celeb.getType()).isNotNull();
                });
    }

    @DisplayName("Celeb 존재하는 한건 조회")
    @ParameterizedTest
    @MethodSource("celebIdProvider")
    void test2(Long id) {
        // given
        assertThat(id).isNotNull().isNotZero();
        // when
        Celeb celeb = celebDao.findById(session, id);
        // then
        assertThat(celeb)
                .isNotNull()
                .satisfies((_celeb) -> {
                    assertThat(_celeb.getId()).isNotNull().isNotZero();
                    assertThat(_celeb.getName()).isNotNull();
                    assertThat(_celeb.getProfile()).isNotNull();
                    assertThat(_celeb.getType()).isNotNull();
                });

    }

    @DisplayName("Celeb 존재하지 않는 한건 조회")
    @ParameterizedTest
    @ValueSource(longs = {1000000000L, 20000000000L})
    void test3(Long id) {
        // given
        assertThat(id).isNotNull().isNotZero();
        // when
        Celeb celeb = celebDao.findById(session, id);
        // then
        assertThat(celeb).isNull();
    }

    @DisplayName("Celeb 등록")
    @ParameterizedTest
    @CsvSource({"홍길동,honggd.jpg,SINGER", "신사임당,sinsa.jpg,MODEL"})
    void test4(String name, String profile, Type type) {
        // given
        assertThat(name).isNotNull();
        assertThat(profile).isNotNull();
        assertThat(type).isNotNull();
        // when
        Celeb celeb = new Celeb();
        celeb.setName(name);
        celeb.setProfile(profile);
        celeb.setType(type);
        int result = celebDao.insertCeleb(session, celeb);
        // then
        assertThat(result).isGreaterThan(0);
        Long id = celeb.getId(); // <selectKey>로 처리된 값 가져오기
        assertThat(id).isNotNull().isNotZero();
        Celeb celebInserted = celebDao.findById(session, id);
        assertThat(celebInserted)
                .isNotNull()
                .satisfies((_celeb) -> {
                    assertThat(_celeb.getId()).isNotNull().isNotZero();
                    assertThat(_celeb.getName()).isNotNull();
                    assertThat(_celeb.getProfile()).isNotNull();
                    assertThat(_celeb.getType()).isNotNull();
                });
    }

    @DisplayName("Celeb 수정")
    @ParameterizedTest
    @MethodSource("celebIdProvider")
    void test5(Long id) {
        // given
        assertThat(id).isNotNull().isNotZero();
        // when
        String newName = "새이름";
        String newProfile = "새프로필.jpg";
        Type newType = Type.valueOf("ENTERTAINER");
        Celeb celeb = new Celeb(id, newName, newProfile, newType);
        int result = celebDao.updateCeleb(session, celeb);
        // then
        assertThat(result).isGreaterThan(0);
        Celeb celebUpdated = celebDao.findById(session, id);
        assertThat(celebUpdated)
                .isNotNull()
                .satisfies((_celeb) -> {
                    assertThat(_celeb.getId()).isEqualTo(id);
                    assertThat(_celeb.getName()).isEqualTo(newName);
                    assertThat(_celeb.getProfile()).isEqualTo(newProfile);
                    assertThat(_celeb.getType()).isEqualTo(newType);
                });

    }

    @DisplayName("Celeb 삭제")
    @ParameterizedTest
    @MethodSource("celebIdProvider")
    void test6(Long id) {
        // given
        assertThat(id).isNotNull().isNotZero();
        // when
        int result = celebDao.deleteCeleb(session, id);
        // then
        assertThat(result).isGreaterThan(0);
        Celeb celebDeleted = celebDao.findById(session, id);
        assertThat(celebDeleted).isNull();
    }

    public static Stream<Long> celebIdProvider() {
        CelebDao celebDao = new CelebDao();
        SqlSession session = getSqlSession();
        List<Celeb> celebs = celebDao.findAll(session);
        return celebs.stream() // Stream<Celeb>
                .filter((celeb) -> celeb.getId() <= 5)
                .map((celeb -> celeb.getId())); // Stream<Long>
    }
}
