package com.sh.mvc.board.model.service;


import com.sh.mvc.board.model.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

// 게시판 테스트 목록(이것이 최소!)
// - 게시글 전체조회
// - 게시글 페이징 조회
// - 전체게시글수 조회
// - 게시글 한건 조회
// - 게시글 등록
// - 게시글 수정 (제목, 내용)
// - 게시글 삭제
public class BoardServiceTest {
    BoardService boardService;

    @BeforeEach
    public void beforeEach() {this.boardService = new BoardService();}

    @DisplayName("BoardService 객체는 null이 아니다.")
    @Test
    public void test1(){
        assertThat(boardService).isNotNull();
    }

    @DisplayName("게시글 전체 조회")
    @Test
    public void test2(){
        List<Board> boards = boardService.findAll();
        assertThat(boards)
                .isNotNull()
                .isNotEmpty();
        boards.forEach((board)-> {
            System.out.println(board);
            assertThat(board.getId()).isNotEqualTo(0);
            assertThat(board.getTitle()).isNotNull();
            assertThat(board.getMemberId()).isNotNull();
            assertThat(board.getContent()).isNotNull();
            assertThat(board.getRegDate()).isNotNull();
        });
    }


    @DisplayName("전체 게시글 수 조회")
    @Test
    public void test3(){
        int totalCount = boardService.getTotalCount();
        System.out.println(totalCount);
        assertThat(totalCount).isGreaterThan(0);
    }

    @DisplayName("게시글 페이징 조회")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    public void test4(int page){
        assertThat(page).isGreaterThan(0);
        System.out.println(page);
        int limit = 10;
        Map<String, Object> param = Map.of("page",page, "limit", limit);
        List<Board> boards = boardService.findAll(param);
        System.out.println(boards);
        assertThat(boards).isNotNull();
        assertThat(boards.size()).isLessThanOrEqualTo(limit);
    }

    @DisplayName("게시글 한 건 조회")
    @Test
    public void test5(){
        int id = 1;
        Board board = boardService.findById(id);
        System.out.println(board);

        assertThat(board).isNotNull();

        assertThat(board.getId()).isNotEqualTo(0);
        assertThat(board.getTitle()).isNotNull();
        assertThat(board.getMemberId()).isNotNull();
        assertThat(board.getContent()).isNotNull();
        assertThat(board.getRegDate()).isNotNull();
    }

    @DisplayName("게시글 등록")
    @Test
    public void test6(){
        long id = 1234321;
        String title = "테스트 타이틀";
        String memberId = "abcde";
        String content = "테스트입니다ㅏㅏㅏㅏㅏasdbfasd";

        Board board = new Board(id, title, memberId, content,0, null);
        int result = boardService.insertBoard(board);
        assertThat(result).isEqualTo(1);

        Board board2 = boardService.findById(id);
        assertThat(board2).isNotNull();
        assertThat(board2.getId()).isEqualTo(id);
        assertThat(board2.getMemberId()).isEqualTo(memberId);
        assertThat(board2.getTitle()).isEqualTo(title);
        assertThat(board2.getContent()).isEqualTo(content);


    }

    @DisplayName("게시글 수정")
    @Test
    public void test7(){

    }

    @DisplayName("게시글 삭제")
    @Test
    public void test8(){
        long id = 1234321;
        Board board = boardService.findById(id);
        assertThat(board).isNotNull();

        int result = boardService.deleteBoard(id);
        assertThat(result).isGreaterThan(0);

        Board board2 = boardService.findById(id);
        assertThat(board2).isNull();
    }

}
