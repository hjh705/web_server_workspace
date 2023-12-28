package com.sh.mvc.notification.model.entity;

public enum Type {
    NEW_COMMENT, // 내 게시글에 댓글이 달린 경우
    DM, // DM이 온 경우
    NEW_FOLLOWER, // 새 팔로워가 생겼을 때
    NEW_CONTENT; // 팔로잉 하는 회원의 새 게시글 등록 시
}
