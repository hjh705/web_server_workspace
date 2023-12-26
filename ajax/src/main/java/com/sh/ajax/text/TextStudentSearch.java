package com.sh.ajax.text;

import com.sh.ajax.student.model.entity.Student;
import com.sh.ajax.student.model.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/text/studentSearch")
public class TextStudentSearch extends HttpServlet {
    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        String term = req.getParameter("term");
        System.out.println(term);
        // 2. 업무 로직
        List<Student> students = studentService.findByName(term);
        System.out.println(students);

        // 3. 응답 출력 csv
        // 1,홍길동\n2,고길동
        resp.setContentType("text/csv; charset = utf-8");
        PrintWriter out = resp.getWriter();
        for(int i = 0; i < students.size(); i++){
            Student student = students.get(i);
            out.println("%d, %s".formatted(student.getId(), student.getName()));
            if(i < students.size() -1) {
                out.println();
            }
        }

    }
}