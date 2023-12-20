package com.sh.mvc.board.model.vo;

import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * vo클래스 value object
 * - immutable한 value객체
 * - entity클래스를 확장한 객체
 */
public class BoardVo extends Board {
    private int attachCount; // 첨부파일 개수
    private List<Attachment> attachments = new ArrayList<>();

    public int getAttachCount() {
        return attachCount;
    }

    public void setAttachCount(int attachCount) {
        this.attachCount = attachCount;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    @Override
    public String toString() {
        return "BoardVo{" +
                "attachCount=" + attachCount +
                ", attachments=" + attachments +
                "} " + super.toString();
    }
}
