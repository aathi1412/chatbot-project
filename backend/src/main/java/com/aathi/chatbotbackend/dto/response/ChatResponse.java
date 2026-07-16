package com.aathi.chatbotbackend.dto.response;

import com.aathi.chatbotbackend.enums.ToolType;
import lombok.Builder;

@Builder
public record ChatResponse(
        ToolType type,
        String data
) {
}
