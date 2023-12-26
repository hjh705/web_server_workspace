package com.sh.mvc.board.model.entity;

import java.time.LocalDateTime;

/**
 * entity 클래스
 * - db 테이블과 매칭되는 클래스
 */
public class Board {
    private long id;
    private String title;
    private String memberId;
    private String content;
    private int readCount;
    private LocalDateTime regDate;

    public Board() {
    }
    public Board(long id, String title, String memberId, String content, int readCount, LocalDateTime regDate) {
        this.id = id;
        this.title = title;
        this.memberId = memberId;
        this.content = content;
        this.readCount = readCount;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", memberId='" + memberId + '\'' +
                ", content='" + content + '\'' +
                ", readCount=" + readCount +
                ", regDate=" + regDate +
                '}';
    }


}
