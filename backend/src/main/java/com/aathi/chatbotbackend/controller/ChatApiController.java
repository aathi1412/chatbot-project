package com.aathi.chatbotbackend.controller;

import com.aathi.chatbotbackend.dto.request.ChatRequest;
import com.aathi.chatbotbackend.service.ChatApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatApiController {

    private final ChatApiService chatApiService;

    @PostMapping("/ask-ai")
    public ResponseEntity<String> chatResponse(@RequestBody ChatRequest.UserChatRequest request){
        String response = chatApiService.getResponse(request.prompt());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ask-ai-options")
    public ResponseEntity<String> chatResponseOptions(@RequestBody ChatRequest.HuggingFaceChatRequest request){
        String response = chatApiService.getResponseOptions(request);

        return ResponseEntity.ok(response);
    }
}
