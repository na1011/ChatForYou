package com.chatting.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatDto {

    /**
     * 메세지 타입 : 입장, 채팅, 퇴장
     * TALK 타입의 경우 채팅방을 sub 구독하고 있는 모든 클라이언트에게 전달된다.
     */
    public enum MessageType {
        ENTER, TALK, LEAVE
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String time;
}
