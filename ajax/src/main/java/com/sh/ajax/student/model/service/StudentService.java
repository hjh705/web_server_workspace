package com.sh.ajax.student.model.service;

import com.sh.ajax.student.model.dao.StudentDao;
import com.sh.ajax.student.model.entity.Student;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.ajax.common.SqlSessionTemplate.getSqlSession;

public class StudentService {
    private StudentDao studentDao = new StudentDao();
    public List<Student> findByName(String name) {
        SqlSession session = getSqlSession();
        List<Student> students = studentDao.findByName(session, name);
        session.close();
        return students;
    }
}
