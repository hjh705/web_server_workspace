package com.sh.mvc.notification.model.dao;

import com.sh.mvc.notification.model.entity.Notification;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class NotificationDao {
    public int insertNotification(SqlSession session, Notification noti) {
        return session.insert("notification.insertNotification", noti);
    }

    public List<Notification> findByMemberId(SqlSession session, String memberId) {
        return session.selectList("notification.findByMemberId", memberId);
    }
}
