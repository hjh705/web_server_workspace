package com.sh.mvc.board.model.entity;

import java.time.LocalDateTime;

public class Attachment {
    private long id;
    private long boardId;
    private String originalFilename;
    private String renamedFilename;
    private LocalDateTime regDate;

    public Attachment() {
    }

    public Attachment(long id, long boardId, String originalFilename, String renamedFilename, LocalDateTime regDate) {
        this.id = id;
        this.boardId = boardId;
        this.originalFilename = originalFilename;
        this.renamedFilename = renamedFilename;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getRenamedFilename() {
        return renamedFilename;
    }

    public void setRenamedFilename(String renamedFilename) {
        this.renamedFilename = renamedFilename;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", boardId=" + boardId +
                ", originalFilename='" + originalFilename + '\'' +
                ", renamedFilename='" + renamedFilename + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
