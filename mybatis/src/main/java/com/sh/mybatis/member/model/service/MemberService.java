package com.sh.mybatis.member.model.service;

import com.sh.mybatis.member.model.dao.MemberDao;
import com.sh.mybatis.member.model.entity.Member;
import org.apache.ibatis.session.SqlSession;

import static com.sh.mybatis.common.SqlSessionTemplate.getSqlSession;

public class MemberService {
    private MemberDao memberDao = new MemberDao();

    public Member findById(String id) {
        SqlSession session = getSqlSession();
        Member member = memberDao.findById(session, id);
        session.close();
        return member;
    }

}