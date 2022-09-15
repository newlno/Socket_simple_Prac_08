package com.example.Socket_simple_Prac_08.controller;

import com.example.Socket_simple_Prac_08.domain.ChatMessage;
import com.example.Socket_simple_Prac_08.domain.MessageType;
import com.example.Socket_simple_Prac_08.service.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

@Controller
public class ChatController {

    private ChatService chatService;


    @MessageMapping("/chat.sendMessage/{chatRoomId}")
    public void sendMessage(@DestinationVariable("chatRoomId") String chatRoomId,
                            @Payload ChatMessage chatMessage) {
        if (!StringUtils.hasText(chatRoomId) || chatMessage == null) {
            return;
        }
        if (chatMessage.getMessageType() == MessageType.CHAT) {
            chatService.sendMessage(chatRoomId, chatMessage);
        }
    }
}
