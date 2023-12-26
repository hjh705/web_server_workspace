package com.sh.ajax.student.model.dao;

import com.sh.ajax.student.model.entity.Student;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentDao {

    public List<Student> findByName(SqlSession session, String name) {
        return session.selectList("student.findByName", name);
    }


}
