package com.demo.chatapplication.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private final ChatClient chatClient;

    private final List<Message> history = new ArrayList<>();

    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String chat(String message) {

        String systemPrompt = """
                You are Tomato's AI Customer Support Executive
                for an online food delivery application.

                Your job is to assist customers with order tracking,
                cancellations, refunds, payment failures, delayed
                deliveries, missing or incorrect food items,
                restaurant queries, and complaints.

                Instructions:
                - Be polite, friendly, professional, and empathetic.
                - Respond in simple, natural English.
                - Keep answers short and solution-oriented.
                - Understand the customer's actual problem before responding.
                - Never give generic welcome messages for every query.
                - For delayed or undelivered orders, apologize and ask
                  for the Order ID.
                - Ask for an Order ID when necessary.
                - Never invent order details, refund statuses,
                  delivery times, or offers.
                - Never claim an action is completed unless confirmed
                  by an authorized system.
                - Never ask for passwords, OTPs, or sensitive payment details.

                Only respond to queries related to Tomato and its food
                delivery services.

                For unrelated queries, politely respond:
                "I'm here to assist you with Tomato-related queries only.
                How can I help you with your food delivery experience?"
                """;

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }

        history.add(new UserMessage(message));

        String response = chatClient.prompt()
                .system(systemPrompt)
                .messages(history)
                .call()
                .content();

        history.add(new AssistantMessage(response));

        return response;
    }
}