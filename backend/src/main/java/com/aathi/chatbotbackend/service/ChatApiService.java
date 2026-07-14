package com.aathi.chatbotbackend.service;

import com.aathi.chatbotbackend.dto.request.ChatRequest;
import com.aathi.chatbotbackend.dto.request.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static com.aathi.chatbotbackend.constants.ApiConstants.API_KEY;
import static com.aathi.chatbotbackend.constants.ApiConstants.URI;


@Service
@RequiredArgsConstructor
public class ChatApiService {

    private final RestClient restClient;

    public String getResponse(String prompt) {

        ChatRequest.HuggingFaceChatRequest chatRequest =
                ChatRequest.HuggingFaceChatRequest.builder()
                        .model("meta-llama/Llama-3.1-8B-Instruct")
                        .messages(List.of(Message.builder()
                                .role("user")
                                .content(prompt)
                                .build()
                        ))
                        .build();

        return restClient.post()
                .uri(URI)
                .header("Authorization", "Bearer " + API_KEY)
                .body(chatRequest)
                .retrieve()
                .body(String.class);
    }

    public String getResponseOptions(ChatRequest.HuggingFaceChatRequest request) {
        return restClient.post()
                .uri(URI)
                .header("Authorization", "Bearer " + API_KEY)
                .body(request)
                .retrieve()
                .body(String.class);
    }
}
