package com.aathi.chatbotbackend.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record HuggingFaceChatRequest(
        String model,
        List<Message> messages
) {}
