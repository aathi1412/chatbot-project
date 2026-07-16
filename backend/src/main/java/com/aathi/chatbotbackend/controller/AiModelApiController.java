package com.aathi.chatbotbackend.controller;

import com.aathi.chatbotbackend.dto.request.ChatRequest;
import com.aathi.chatbotbackend.dto.response.ChatResponse;
import com.aathi.chatbotbackend.service.ImageGenerationService;
import com.aathi.chatbotbackend.service.RequestRouterService;
import com.aathi.chatbotbackend.service.TextGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class AiModelApiController {

    private final TextGenerationService textGenerationService;
    private final ImageGenerationService imageGenerationService;
    private final RequestRouterService requestRouterService;

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request){
        ChatResponse response = requestRouterService.routeRequest(request.prompt());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ask-ai")
    public ResponseEntity<ChatResponse> AiResponse(@RequestBody ChatRequest request){
        ChatResponse response = textGenerationService.generateChatResponse(request.prompt());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ask-image")
    public ResponseEntity<ChatResponse> chatResponseOptions(@RequestBody ChatRequest request){
        ChatResponse response = imageGenerationService.generateImage(request.prompt());

        return ResponseEntity.ok(response);
    }
}
