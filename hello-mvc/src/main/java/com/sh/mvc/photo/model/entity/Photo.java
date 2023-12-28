package com.sh.mvc.photo.model.entity;

import java.time.LocalDateTime;

public class Photo {
    private Long id;
    private String memberID;
    private String content;
    private String originalFilename;
    private String renamedFilename;
    private int readCount;
    private LocalDateTime regDate;

    public Photo() {
    }

    public Photo(Long id, String memberID, String content, String originalFilename, String renamedFilename, int readCount, LocalDateTime regDate) {
        this.id = id;
        this.memberID = memberID;
        this.content = content;
        this.originalFilename = originalFilename;
        this.renamedFilename = renamedFilename;
        this.readCount = readCount;
        this.regDate = regDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Photo{" +
                "id=" + id +
                ", memberID='" + memberID + '\'' +
                ", content='" + content + '\'' +
                ", originalFilename='" + originalFilename + '\'' +
                ", renamedFilename='" + renamedFilename + '\'' +
                ", readCount=" + readCount +
                ", regDate=" + regDate +
                '}';
    }
}
