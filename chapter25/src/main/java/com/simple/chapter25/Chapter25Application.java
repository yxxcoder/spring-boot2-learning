package com.simple.chapter25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * 打造属于你的聊天室（WebSocket）
 *
 * @author yxxcoder
 * @since 2019-03-30 23:00 PM
 */
@EnableWebSocket
@SpringBootApplication
public class Chapter25Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter25Application.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}