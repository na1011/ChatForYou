package com.chatting.service;

import com.chatting.model.ChatRoomDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoomDto> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRoom() {
        return new ArrayList<>(chatRoomMap.values());
    }

    public ChatRoomDto findRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    public ChatRoomDto createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoomDto chatRoomDto = ChatRoomDto.builder()
                .roomId(randomId)
                .name(name)
                .build();

        chatRoomMap.put(randomId, chatRoomDto);
        return chatRoomDto;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
