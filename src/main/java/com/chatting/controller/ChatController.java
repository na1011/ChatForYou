package com.chatting.controller;

import com.chatting.model.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto messageDto) {
        if (ChatMessageDto.MessageType.JOIN.equals(messageDto.getType())) {
            messageDto.setMessage(messageDto.getSender() + "님이 입장하셨습니다.");
        }
        messagingTemplate.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto);
    }
}
