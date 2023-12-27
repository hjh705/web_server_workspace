package com.sh.ajax.celeb.model.service;

import com.sh.ajax.celeb.model.dao.CelebDao;
import com.sh.ajax.celeb.model.entity.Celeb;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.ajax.common.SqlSessionTemplate.getSqlSession;

public class CelebService {
    private CelebDao celebDao = new CelebDao();
    public List<Celeb> findAll() {
        SqlSession session = getSqlSession();
        List<Celeb> celebs = celebDao.findAll(session);
        session.close();
        return celebs;
    }

    public Celeb finById(Long id) {
        SqlSession session = getSqlSession();
        Celeb celeb = celebDao.findById(session, id);
        session.close();
        return celeb;
    }


    public int insertCeleb(Celeb celeb) {
        int result = 0;
        SqlSession session = getSqlSession();

        try {
            result = celebDao.insertCeleb(session, celeb);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
            return result;
    }
}
