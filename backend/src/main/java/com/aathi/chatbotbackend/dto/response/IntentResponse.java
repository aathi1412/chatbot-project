package com.aathi.chatbotbackend.dto.response;

import com.aathi.chatbotbackend.enums.ToolType;

public record IntentResponse(
        ToolType tool
) {}
