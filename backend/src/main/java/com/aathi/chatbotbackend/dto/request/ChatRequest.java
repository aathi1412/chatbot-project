package com.aathi.chatbotbackend.dto.request;

import lombok.Builder;

@Builder
public record ChatRequest (
        String prompt
) {}
