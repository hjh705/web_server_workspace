package com.sh.mvc.ws.endpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sh.mvc.common.LocalDateTimeSerializer;
import com.sh.mvc.notification.model.entity.Notification;
import com.sh.mvc.ws.config.HelloMvcWebSocketConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 서버사이드 웹소켓 엔드포인트 (최초 요청을 처리하는 곳)
 */
@ServerEndpoint(value = "/echo", configurator = HelloMvcWebSocketConfigurator.class)
public class EchoWebSocket {
    private static Gson gson;
    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gson = builder.create();
    }

    /**
     * 웹소켓 세션 관리를 위한 맵 객체
     * - id = session
     * - 멀티쓰레드 환경을 위한 동기화(쓰레드 간 이용 순서가 정해진, 한번에 하나의 쓰레드만 접근 가능한) 처리된 맵이 필요하다.
     */
    private static Map<String, Session> clientMap = Collections.synchronizedMap(new HashMap<>());
    private static void addToClientMap(String memberId, Session session) {
        clientMap.put(memberId, session);
        log();
    }


    private void removeFromClientMap(String memberId) {
        clientMap.remove(memberId);
        log();
    }
    /*
    개인별 실시간 알림 메소드
    */
    public static void sendNotification(Notification noti) {
        Session session = clientMap.get(noti.getMemberId());
        if(session != null) {
            RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
            // 형식이 깨지지 않으면서 전달하기 제일 좋은 방식은 json에 담아 전달하는 것
            try {
                basicRemote.sendText(gson.toJson(noti));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(noti.getMemberId() + "에 실시간 알림을 전송했습니다.");
        } else{
            System.out.println(noti.getMemberId() + "는 접속 중이 아닙니다.");
        }
    }

    private static void log() {
        System.out.println(clientMap.size()+ " 명 접속 중" + clientMap.keySet());
    }

    @OnOpen
    public void open(EndpointConfig config, Session session) {
        System.out.print("[open] ");
        Map<String, Object> props = config.getUserProperties();
        String memberId = (String) props.get("memberId");
        System.out.println(memberId + "님이 접속했습니다.");
        addToClientMap(memberId, session);

        // 연결 해제시 map에서 사용자 제거를 위해 session의 내부 맵에도 사용자 id를 등록
        Map<String, Object> sessionProps = session.getUserProperties();
        sessionProps.put("memberId", memberId);
    }



    @OnMessage
    public void message(String msg, Session session){
        System.out.print("message : " + msg);

        Collection<Session> sessions = clientMap.values();
        for(Session sess : sessions){
            RemoteEndpoint.Basic basic = sess.getBasicRemote();
            try {
                basic.sendText(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @OnError
    public void error(Throwable e){
        System.out.print("error");
        e.printStackTrace();
    }
    @OnClose
    public void close(Session session){
        System.out.print("[close] ");
        Map<String, Object> sessionProps = session.getUserProperties();
        String memberId = (String) sessionProps.get("memberId");
        System.out.println(memberId + " 님이 접속 해제했습니다.");
        removeFromClientMap(memberId);
    }


}
