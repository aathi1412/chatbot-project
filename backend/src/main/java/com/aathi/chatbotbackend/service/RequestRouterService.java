package com.aathi.chatbotbackend.service;

import com.aathi.chatbotbackend.dto.response.ChatResponse;
import com.aathi.chatbotbackend.enums.ToolType;
import com.aathi.chatbotbackend.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestRouterService {

    private final IntentClassifierService intentClassifier;
    private final TextGenerationService textGenerationService;
    private final ImageGenerationService imageGenerationService;
    public ChatResponse routeRequest(String prompt) {

        ToolType tool = intentClassifier.classifyPrompt(prompt);

        return switch (tool) {
            case TEXT -> textGenerationService.generateChatResponse(prompt);
            case IMAGE ->  imageGenerationService.generateImage(prompt);
            case ERROR -> throw new BadRequestException("Bad Request");
        };
    }
}
