package com.chatting.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomDto {

    private String roomId;
    private String roomName;
    private int userCount;
    private int maxUserCnt;

    private String roomPwd;
    private boolean secretChk;

    private Map<String, String> userMap = new HashMap<>();

    public static ChatRoomDto create(String roomName) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.roomId = UUID.randomUUID().toString();
        chatRoomDto.roomName = roomName;

        return chatRoomDto;
    }
}
