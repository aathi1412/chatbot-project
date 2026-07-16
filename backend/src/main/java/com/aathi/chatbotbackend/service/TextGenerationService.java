package com.aathi.chatbotbackend.service;

import com.aathi.chatbotbackend.config.ApiProperties;
import com.aathi.chatbotbackend.dto.request.HuggingFaceChatRequest;
import com.aathi.chatbotbackend.dto.request.Message;
import com.aathi.chatbotbackend.dto.response.ChatResponse;
import com.aathi.chatbotbackend.enums.ToolType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TextGenerationService {

    private final RestClient restClient;
    private final ApiProperties apiProperties;

    public ChatResponse generateChatResponse(String prompt) {

        HuggingFaceChatRequest chatRequest =
                HuggingFaceChatRequest.builder()
                        .model("meta-llama/Llama-3.1-8B-Instruct")
                        .messages(List.of(Message.builder()
                                .role("user")
                                .content(prompt)
                                .build()
                        ))
                        .build();

        JsonNode root =  restClient.post()
                                .uri(apiProperties.huggingFaceUri())
                                .header("Authorization", "Bearer " + apiProperties.HUGGING_FACE_API_KEY())
                                .body(chatRequest)
                                .retrieve()
                                .body(JsonNode.class);

        if (root == null) {
            throw new IllegalStateException("Cloudflare returned an empty response.");
        }

        String content =  root
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asString();

        return ChatResponse.builder()
                .type(ToolType.TEXT)
                .data(content)
                .build();
    }

    public String intentClassifier(String prompt){
        ChatResponse response = generateChatResponse(prompt);
        return response.data();
    }

}
