package com.chatting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {

    private String roomId;
    private String roomName;
    private int userCount;
    private int maxUserCnt;

    private String roomPwd;
    private boolean secretChk;

    private Map<String, String> userMap;

    public static ChatRoomDto create(String roomName) {
        return ChatRoomDto.builder()
                .roomId(UUID.randomUUID().toString())
                .roomName(roomName)
                .userMap(new HashMap<>())
                .build();
    }
}
