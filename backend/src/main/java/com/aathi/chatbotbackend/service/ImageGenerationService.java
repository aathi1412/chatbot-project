package com.aathi.chatbotbackend.service;

import com.aathi.chatbotbackend.config.ApiProperties;
import com.aathi.chatbotbackend.dto.request.ChatRequest;
import com.aathi.chatbotbackend.dto.response.ChatResponse;
import com.aathi.chatbotbackend.enums.ToolType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

@Service
@RequiredArgsConstructor
public class ImageGenerationService {

    private final ApiProperties apiProperties;
    private final RestClient restClient;

    public ChatResponse generateImage(String prompt){

        ChatRequest request = ChatRequest.builder()
                .prompt(prompt).build();

        JsonNode root =
                restClient.post()
                        .uri(apiProperties.cloudflareUri())
                        .body(request)
                        .header("Authorization", "Bearer " + apiProperties.CLOUDFLARE_API_KEY())
                        .contentType(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .body(JsonNode.class);

        if (root == null) {
            throw new IllegalStateException("Cloudflare returned an empty response.");
        }

        String base64 = root
                .path("result")
                .path("image").asString();

        String image = "data:image/jpeg;base64," + base64;
        return ChatResponse.builder()
                .type(ToolType.IMAGE)
                .data(image)
                .build();
    }
}
