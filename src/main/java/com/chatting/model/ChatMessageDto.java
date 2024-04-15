package com.chatting.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {

    // 메세지 타입 : 입장안내, 대화
    public enum MessageType {
        ENTER, TALK, JOIN
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
