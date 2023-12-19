package com.sh.mvc.board.model.service;

import com.sh.mvc.board.model.dao.BoardDao;
import com.sh.mvc.board.model.entity.Board;
import org.apache.catalina.Session;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;

public class BoardService {
    private BoardDao boardDao = new BoardDao();

    public List<Board> findAll() {
        SqlSession session = getSqlSession();
        List<Board> boards = boardDao.findAll(session);
        session.close();
        return boards;
    }

    public int getTotalCount() {
        SqlSession session = getSqlSession();
        int totalCount = boardDao.getTotalCount(session);
        session.close();
        return totalCount;
    }


    public List<Board> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<Board> boards = boardDao.findAll(session, param);
        session.close();
        return boards;
    }

    public Board findById(long id) {
        SqlSession session = getSqlSession();
        Board board = boardDao.findById(session, id);
        session.close();
        return board;
    }

    public int insertBoard(Board board) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = boardDao.insertBoard(session, board);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }


    public int deleteBoard(long id) {
        int result = 0;
        SqlSession session = getSqlSession();

        try {
            result = boardDao.deleteBoard(session, id);
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
