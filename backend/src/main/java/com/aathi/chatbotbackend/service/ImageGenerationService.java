package com.aathi.chatbotbackend.service;

import com.aathi.chatbotbackend.config.ApiProperties;
import com.aathi.chatbotbackend.dto.request.ChatRequest;
import com.aathi.chatbotbackend.dto.response.ChatResponse;
import com.aathi.chatbotbackend.enums.ToolType;
import com.aathi.chatbotbackend.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import tools.jackson.databind.JsonNode;

@Service
@RequiredArgsConstructor
public class ImageGenerationService {

    private final ApiProperties apiProperties;
    private final RestClient restClient;

    public ChatResponse generateImage(String prompt){

        ChatRequest request = ChatRequest.builder()
                .prompt(prompt).build();

        String image;

        try {
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
            image = "data:image/jpeg;base64," + base64;

        } catch (RestClientResponseException e) {
            throw new BadRequestException(e.getResponseBodyAsString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ChatResponse.builder()
                .type(ToolType.IMAGE)
                .data(image)
                .build();
    }
}
