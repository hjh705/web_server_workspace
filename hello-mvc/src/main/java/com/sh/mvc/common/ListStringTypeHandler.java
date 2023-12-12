package com.sh.mvc.common;

//     Java 와 JDBC 간의 자동 변환이 안되는 경우 직접 클래스를 만들어서 설정해줘야만 한다
//     Java의 List는 JDBC 에선 varchar2로 반환하기에 별도 과정이 필요
//     작성 후 mybatis-config.xml에 등록해야함

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * setter PreparedStatement 에 ? 값 대입
 * getter * 3 ResultSet/CallableStatement에서 값을 가져올 때
 */

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListStringTypeHandler extends BaseTypeHandler<List<String>> {
    /**
     * List 요소를 꺼내서 , 기준으로 연결해 하나의 문자열을 반환함
     * @param preparedStatement
     * @param i
     * @param strings
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        String value = "";
        for(int j = 0; j < strings.size(); j++){
            String v = strings.get(j);
            value += v;
            if(j != strings.size() -1) {
                value += ",";
            }
            preparedStatement.setString(i, value);
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        if(value != null){
            return Arrays.asList(value.split(","));
        }
        return null;
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String value = resultSet.getString(columnIndex);
        if(value != null){
            return Arrays.asList(value.split(","));
        }
        return null;
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String value = callableStatement.getString(columnIndex);
        if(value != null){
            return Arrays.asList(value.split(","));
        }
        return null;
    }
}