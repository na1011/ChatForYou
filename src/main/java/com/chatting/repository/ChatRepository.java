package com.chatting.repository;

import com.chatting.model.ChatRoomDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Repository
public class ChatRepository {

    private Map<String, ChatRoomDto> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRoom() {
        // 채팅방 생성순서를 최신 순으로 반환
        List<ChatRoomDto> chatRoomList = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRoomList);
        return chatRoomList;
    }

    public ChatRoomDto findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    // 채팅방 생성 후 LinkedHashMap에 UUID, chatRoom 객체 저장
    public ChatRoomDto createChatRoom(String roomName) {
        ChatRoomDto chatRoom = ChatRoomDto.create(roomName);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    // 채팅방 전체 유저 목록 조회
    public List<String> getUserList(String roomId) {
        List<String> userList = new ArrayList<>();

        ChatRoomDto room = chatRoomMap.get(roomId);
        room.getUserMap().forEach((key, value) -> userList.add(value));

        return userList;
    }

    // 채팅방 유저 조회
    public String getUserName(String roomId, String userId) {
        ChatRoomDto room = chatRoomMap.get(roomId);
        return room.getUserMap().get(userId);
    }

    // 채팅방 유저 목록에 유저 추가
    public String addUser(String roomId, String userName) {
        ChatRoomDto room = chatRoomMap.get(roomId);

        String userId = UUID.randomUUID().toString();
        room.getUserMap().put(userId, userName);

        return userId;
    }

    // 유저 이름 중복 시, 뒤에 랜덤한 숫자를 붙인 후 반환
    public String isDuplicatedName(String roomId, String userName) {
        ChatRoomDto room = chatRoomMap.get(roomId);

        if (!room.getUserMap().isEmpty() && room.getUserMap().containsValue(userName)) {
            int randomNum = (int) ((Math.random() * 100) + 1);
            userName = userName + randomNum;
        }
        return userName;
    }

    // 유저 퇴장 시 유저 목록에서 삭제
    public void delUser(String roomId, String userId) {
        ChatRoomDto room = chatRoomMap.get(roomId);
        room.getUserMap().remove(userId);
    }

    // 채팅방 인원 증가
    public void plusUserCnt(String roomId) {
        ChatRoomDto room = chatRoomMap.get(roomId);
        room.setUserCount(room.getUserCount() + 1);
    }

    // 채팅방 인원 감소
    public void minusUserCnt(String roomId) {
        ChatRoomDto room = chatRoomMap.get(roomId);
        room.setUserCount(room.getUserCount() - 1);
    }
}
