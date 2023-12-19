package com.sh.mvc.member.model.service;

import com.sh.mvc.member.model.dao.MemberDao;
import com.sh.mvc.member.model.entity.Member;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;


public class MemberService {
    private MemberDao memberDao = new MemberDao();

    public Member findById(String id) {
        SqlSession session = getSqlSession();
        Member member = memberDao.findById(session, id);
        session.close();
        return member;
    }

    public List<Member> findAll() {
        SqlSession session = getSqlSession();
        List<Member> members = memberDao.findAll(session);
        session.close();
        return members;
    }

    public List<Member> findByName(String name) {
        SqlSession session = getSqlSession();
        List<Member> members = memberDao.findByName(session,name);
        session.close();
        return members;
    }

    public List<Member> findByGender(String gender) {
        SqlSession session = getSqlSession();
        List<Member> members = memberDao.findByGender(session, gender);
        session.close();
        return members;
    }

    public int insertMember(Member member) {
        int result = 0;
        SqlSession session = getSqlSession();
        try{
            result = memberDao.insertMember(session, member);
            session.commit();
        }catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int updateMember(Member member) {
        int result = 0;
        SqlSession session = getSqlSession();
        try{
            result = memberDao.updateMember(session, member);
            session.commit();
        }catch (Exception e){
            session.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public int changePassword(Member member) {
        int result = 0;
        SqlSession session = getSqlSession();
        try{
            result = memberDao.changePassword(session, member);
            session.commit();
        }catch (Exception e){
            session.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public int updateMemberRole(Member member) {
        int result = 0;
        SqlSession session = getSqlSession();
        try{
            result = memberDao.updateMemberRole(session, member);
            session.commit();
        }catch (Exception e){
            session.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public int deleteMember(String id) {
        int result = 0;
        SqlSession session = getSqlSession();
        try{
            result = memberDao.deleteMember(session, id);
            session.commit();
        }catch (Exception e){
            session.rollback();
            throw e;
        }finally{
            session.close();
        }
        return result;
    }


    public List<Member> searchMember(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<Member> members = memberDao.searchMember(session,param);
        session.close();
        return members;
    }

    public List<Member> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<Member> members = memberDao.findAll(session, param);
        session.close();
        return members;
    }

    public int getTotalCount() {
        SqlSession session = getSqlSession();
        int totalCount = memberDao.getTotalCount(session);
        session.close();
        return totalCount;
    }

    public int getTotalCount(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int totalCount = memberDao.getTotalCount(session, param);
        session.close();
        return totalCount;
    }
}