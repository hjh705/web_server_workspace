package com.sh.mvc.board.model.service;

import com.sh.mvc.board.model.dao.BoardDao;
import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.entity.BoardComment;
import com.sh.mvc.board.model.vo.BoardVo;
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


    public List<BoardVo> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<BoardVo> boards = boardDao.findAll(session, param);
        session.close();
        return boards;
    }

    // 조회수 상관 없이 게시글을 조회해야 하는 경우
    public BoardVo findById(long id) {
        return findById(id, true);
    }

    public BoardVo findById(long id, boolean hasRead) {
        SqlSession session = getSqlSession();
        BoardVo board = null;
        int result = 0;
        try {
            // 조회수 증가처리
            if(!hasRead) // false 일 때만 증가 처리
                result = boardDao.updateBoardReadCount(session, id);
            //조회
            board = boardDao.findById(session, id);
            List<BoardComment> comments = boardDao.findCommentByBoardId(session,id);
            board.setComments(comments);

            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        }finally {
            session.close();
        }
        return board;
    }

    /**
     * 트랜잭션 관리
     * - board 처리 후 attachments 처리
     * @param board
     * @return
     */
    public int insertBoard(BoardVo board) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            // board 테이블에 등록
            result = boardDao.insertBoard(session, board);
            System.out.println("BoardService#insertBoard : board#id = " + board.getId());
            // attachment 테이블에 등록
            List<Attachment> attachments = board.getAttachments();
            if(!attachments.isEmpty()) {
                for(Attachment attach : attachments){
                    attach.setBoardId(board.getId()); // fk boardId 필드값 대입
                    result = boardDao.insertAttachment(session, attach);
                }
            }
            session.commit(); // 모두 성공해야 commit
        } catch (Exception e) {
            session.rollback(); // 하나라도 실패 시 rollback
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

    public int updateBoard(BoardVo board) {
        int result = 0;
        SqlSession session = getSqlSession();

        try {
            result = boardDao.updateBoard(session, board);

            // attachment테이블 삭제
            List<Long> delFiles = board.getDelFiles();
            if(!delFiles.isEmpty()){
                for(Long id : delFiles){
                    result = boardDao.deleteAttachment(session, id);
                }
            }

            List<Attachment> attachments = board.getAttachments();
            if(!attachments.isEmpty()){
                for(Attachment attach : attachments){
                    attach.setBoardId(board.getId()); // fk 등록 (어떤 게시글의 첨부파일인지 알 수 있도록)
                    result = boardDao.insertAttachment(session, attach);
                }
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public Board findByMemberId(String memberId) {
        SqlSession session = getSqlSession();
        Board board = boardDao.findByMemberId(session, memberId);
        session.close();
        return board;
    }


    public int insertBoardComment(BoardComment comment) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = boardDao.insertBoardComment(session,comment);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }
}
