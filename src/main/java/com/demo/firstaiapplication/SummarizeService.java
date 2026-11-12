package com.demo.firstaiapplication;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SummarizeService {

    private ChatClient  chatClient;

    public SummarizeService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    public String chat(String query) {
        String response = chatClient.prompt()
                .user(query)
                .call()
                .content();

        return response;
    }
}
