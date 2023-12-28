package com.sh.mvc.notification.model.service;

import com.sh.mvc.notification.model.entity.Notification;
import com.sh.mvc.notification.model.entity.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        this.notificationService = new NotificationService();
    }

    @Disabled
    @DisplayName("한 행의 알림 데이터를 기록한다.")
    @ParameterizedTest
    @CsvSource({
            "qwerty,NEW_COMMENT,마약시작하면바로끝 게시글에 댓글이 달렸습니다.",
            "abcde,NEW_COMMENT,ㅂㅂㅂㅂㅂㅂㅂ 게시글에 댓글이 달렸습니다."})
    void test1(String memberId, Type type, String content) {
        // given
        assertThat(memberId).isNotNull();
        assertThat(type).isNotNull();
        assertThat(content).isNotNull();
        // when
        Notification noti = new Notification();
        noti.setMemberId(memberId);
        noti.setType(type);
        noti.setContent(content);
        int result = notificationService.insertNotification(noti);
        // then
        assertThat(result).isGreaterThan(0);
    }

    @DisplayName("특정 회원의 확인 안한 알림 내역을 조회한다.")
    @ParameterizedTest
    @ValueSource(strings = {"qwerty", "abcde"})
    void test2(String memberId) {
        // given
        assertThat(memberId).isNotNull();
        // when
        List<Notification> notifications = notificationService.findByMemberId(memberId);
        // System.out.println(notifications);
        // then
        assertThat(notifications)
                .isNotNull()
                .allSatisfy((noti)-> {
                   assertThat(noti.getId()).isNotNull().isNotZero();
                   assertThat(noti.getMemberId()).isEqualTo(memberId);
                   assertThat(noti.getType()).isNotNull();
                   assertThat(noti.getContent()).isNotNull();
                   assertThat(noti.isChecked()).isFalse();
                   assertThat(noti.getRegDate()).isNotNull();
                });
    }
}