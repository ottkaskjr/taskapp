package com.example.demo.websocket;


import com.example.demo.service.TaskWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@CrossOrigin
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    private final static String CHAT_ENDPOINT="/update/{id}";
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getTaskWebSocketHandler(), CHAT_ENDPOINT)
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler getTaskWebSocketHandler(){
        return new TaskWebSocketHandler();
    }
}
