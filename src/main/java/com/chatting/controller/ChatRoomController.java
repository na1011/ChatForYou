package com.chatting.controller;

import com.chatting.model.ChatRoomDto;
import com.chatting.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRepository chatRepository;

    // 채팅 리스트 화면
    @GetMapping
    public String viewChatList(Model model){
        model.addAttribute("chatRoomList", chatRepository.findAllRoom());
        return "roomList";
    }

    // 채팅방 생성 후 리스트 화면으로 리다이렉트
    @PostMapping("/createroom")
    public String createRoom(@RequestParam String roomName, RedirectAttributes rttr) {
        ChatRoomDto room = chatRepository.createChatRoom(roomName);
        rttr.addFlashAttribute("roomName", room);
        return "redirect:/chat";
    }

    // 채팅방 입장 화면
    @GetMapping("/room")
    public String roomDetail(Model model, String roomId){
        model.addAttribute("chatRoom", chatRepository.findRoomById(roomId));
        return "chatRoom";
    }
}
