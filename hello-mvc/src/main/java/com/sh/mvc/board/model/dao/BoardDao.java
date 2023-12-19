package com.sh.mvc.board.model.dao;

import com.sh.mvc.board.model.entity.Board;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class BoardDao {
    public List<Board> findAll(SqlSession session) {
        return session.selectList("board.findAll");
    }

    public int getTotalCount(SqlSession session) {
        return session.selectOne("board.getTotalCount");
    }


    public List<Board> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");

        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);

        return session.selectList("board.findAllPage",null,rowBounds);
    }

    public Board findById(SqlSession session, long id) {
        return session.selectOne("board.findById", id);
    }

    public int insertBoard(SqlSession session, Board board) {
        return session.insert("board.insertBoard", board);
    }

    public int deleteBoard(SqlSession session, long id) {
        return session.delete("board.deleteBoard", id);
    }
}
