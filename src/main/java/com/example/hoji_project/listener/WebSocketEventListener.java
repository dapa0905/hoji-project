package com.example.hoji_project.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import com.example.hoji_project.model.chat.ChatMessage;
import com.example.hoji_project.model.chat.MessageType;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebSocketEventListener {

  @Autowired
  private SimpMessageSendingOperations messageSendingOperations;

  @EventListener
  public void hadleWebSocketConnectListner(SessionConnectedEvent event) {
    log.info("Received a new web socket connection");
  }

  public void hadleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

    String username = (String) headerAccessor.getSessionAttributes().get("username");
    if (username != null) {
      log.info("User Disconnected : " + username);
      ChatMessage chatMessage =
          ChatMessage.builder().type(MessageType.LEAVE).sender(username).build();

      messageSendingOperations.convertAndSend(chatMessage);
    }
  }

}
