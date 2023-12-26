package com.sh.ajax.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * SqlSessionFactoryBuilder
 * SqlSessionFactory
 * SqlSession
 */
public class SqlSessionTemplate {
    private static SqlSessionFactory factory;

    static {
        String resource = "mybatis-config.xml"; // src/main/resources/mybatis-config.xml

        try(InputStream is = Resources.getResourceAsStream(resource)) {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlSession() {
        return factory.openSession(false); // auto-commit
    }

}