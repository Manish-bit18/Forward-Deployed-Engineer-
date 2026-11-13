package com.demo.chatapplication.controller;

import com.demo.chatapplication.ChatApplication;
import com.demo.chatapplication.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private ChatService chatService;

    public ChatController (ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> sendMessage(@RequestBody  String message){

        String responseMessage = chatService.chat(message);

        return ResponseEntity.ok(responseMessage);
    }
}
