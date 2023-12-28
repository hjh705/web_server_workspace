package com.sh.mvc.notification.model.entity;

import java.time.LocalDateTime;

public class Notification {
    private Long id;
    private String memberId;
    private Type type;
    private String content;
    private boolean checked;
    private LocalDateTime regDate;

    public Notification() {
    }

    public Notification(Long id, String memberId, Type type, String content, boolean checked, LocalDateTime regDate) {
        this.id = id;
        this.memberId = memberId;
        this.type = type;
        this.content = content;
        this.checked = checked;
        this.regDate = regDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", memberId='" + memberId + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", checked=" + checked +
                ", regDate=" + regDate +
                '}';
    }
}
