package com.chatting.config.handler;

import com.chatting.model.ChatMessageDto;
import com.chatting.model.ChatRoomDto;
import com.chatting.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload = {}", payload);

        ChatMessageDto chatMessageDto = objectMapper.readValue(payload, ChatMessageDto.class);
        ChatRoomDto chatRoomDto = chatService.findRoomById(chatMessageDto.getRoomId());

        chatRoomDto.handleAction(session, chatMessageDto, chatService);
    }
}
