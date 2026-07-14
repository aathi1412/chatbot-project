package com.aathi.chatbotbackend.dto.request;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public class ChatRequest {

    @Builder
    public record UserChatRequest(
            @NonNull
            String prompt
    ) {}

    @Builder
    public record HuggingFaceChatRequest(
            @NonNull
            String model,
            List<Message> messages
    ){}
}
