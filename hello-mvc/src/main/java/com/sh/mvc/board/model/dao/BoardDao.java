package com.sh.mvc.board.model.dao;

import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.entity.BoardComment;
import com.sh.mvc.board.model.vo.BoardVo;
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


    public List<BoardVo> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");

        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);

        return session.selectList("board.findAll",param,rowBounds);
    }

    public BoardVo findById(SqlSession session, long id) {
        return session.selectOne("board.findById", id);
    }

    public int insertBoard(SqlSession session, Board board) {
        return session.insert("board.insertBoard", board);
    }

    public int deleteBoard(SqlSession session, long id) {
        return session.delete("board.deleteBoard", id);
    }

    public int updateBoard(SqlSession session, Board board) {
        return  session.update("board.updateBoard", board);
    }

    public Board findByMemberId(SqlSession session, String memberId) {
        return session.selectOne("board.findByMemberId", memberId);
    }

    public int insertAttachment(SqlSession session, Attachment attach) {
        return session.insert("board.insertAttachment", attach);
    }

    public int updateBoardReadCount(SqlSession session, long id) {
        return session.update("board.updateBoardReadCount", id);
    }


    public int deleteAttachment(SqlSession session, Long id) {
        return session.delete("board.deleteAttachment", id);
    }

    public List<BoardComment> findCommentByBoardId(SqlSession session, long boardId) {
        return session.selectList("board.findCommentByBoardId", boardId);
    }

    public int insertBoardComment(SqlSession session, BoardComment comment) {
        return session.insert("board.insertBoardComment", comment);
    }

    public BoardComment findCommentById(SqlSession session, Long id) {
        return session.selectOne("board.findCommentById", id);
    }

    public int deleteBoardComment(SqlSession session, long id) {
        return session.delete("board.deleteBoardComment", id);
    }
}
