package com.aathi.chatbotbackend.dto.request;

import lombok.Builder;

@Builder
public record Message(
        String role,
        String content
) {
}
