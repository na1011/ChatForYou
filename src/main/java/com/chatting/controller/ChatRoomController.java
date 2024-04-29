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
    @GetMapping()
    public String viewRoomList(Model model){
        model.addAttribute("roomList", chatRepository.findAllRoom());
        log.info("SHOW ALL ChatList {}", chatRepository.findAllRoom());

        return "roomList";
    }

    // 채팅방 생성
    @PostMapping("/room/create")
    public String createRoom(@RequestParam String roomName, RedirectAttributes rttr) {
        ChatRoomDto room = chatRepository.createChatRoom(roomName);
        log.info("CREATE Chat Room {}", room);
        rttr.addFlashAttribute("roomName", room);
        return "redirect:/";
    }

    // 채팅방 입장 화면
    @GetMapping("/room")
    public String viewRoomDetail(@RequestParam String roomId ,Model model){
        // roomId 로 해당하는 채팅방을 찾은 후
        model.addAttribute("room", chatRepository.findRoomById(roomId));
        return "roomDetail";
    }
}
