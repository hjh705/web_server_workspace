package com.sh.mvc.common;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

public class SqlSessionTemplateTest {

    @DisplayName("정상적인 SqlSession객체를 반환한다.")
    @Test
    public void test(){
        SqlSession sqlSession = getSqlSession();
        assertThat(sqlSession).isNotNull();
    }
}