package com.example.demo.service;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //super.afterConnectionEstablished(session); VÕIB VAJA MINNAs
        webSocketSessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //super.handleTextMessage(session, message); VÕIB VAJA MINNA
        for(WebSocketSession webSocketSession: webSocketSessions){
            System.out.println("MESSAGE: " + message);
            System.out.println(message.getPayload());
            System.out.println(webSocketSession.getUri());
            String uri = webSocketSession.getUri().toString();
            String payLoad = message.getPayload();



            webSocketSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //super.afterConnectionClosed(session, status); VÕIB VAJA MINNA
        webSocketSessions.remove(session);
    }
}
