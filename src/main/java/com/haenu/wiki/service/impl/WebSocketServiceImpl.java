package com.haenu.wiki.service.impl;

import com.haenu.wiki.service.WebSocketService;
import com.haenu.wiki.websocket.WebSocketServer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author haenu
 * @version 1.0
 * @date 2023/12/22 9:39
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {
    @Resource
    private WebSocketServer webSocketServer;

    @Override
    @Async
    public void sendInfo(String message) {
        webSocketServer.sendInfo(message);
    }
}
