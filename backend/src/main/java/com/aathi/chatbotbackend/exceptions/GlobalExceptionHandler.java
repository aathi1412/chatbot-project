package com.aathi.chatbotbackend.exceptions;

import com.aathi.chatbotbackend.dto.response.ChatResponse;
import com.aathi.chatbotbackend.enums.ToolType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ChatResponse> handleBadRequestException(BadRequestException ex){

        JsonNode root = new ObjectMapper().readTree(ex.getMessage());
        String content = root.path("errors")
                .path(0)
                .path("message")
                .asString();

        String error = content.replaceFirst("^AiError:\\s*", "")
                                .replaceFirst("^AiError:\\s*", "")
                                .replaceAll("\\s*\\([^)]+\\)$", "");

        System.out.println(error);
        return ResponseEntity.ok()
                .body(ChatResponse.builder()
                        .type(ToolType.ERROR)
                        .data(error)
                        .build());
    }
}
