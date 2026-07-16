package com.aathi.chatbotbackend.service;

import com.aathi.chatbotbackend.dto.response.IntentResponse;
import com.aathi.chatbotbackend.enums.ToolType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class IntentClassifierService {

    private final TextGenerationService textGenerationService;
    private final ObjectMapper objectMapper;
    public ToolType classifyPrompt(String prompt){

        String classifierPrompt = """
                You are an intent classifier.
                
                Your only job is to determine whether the user's request requires:
                - TEXT
                - IMAGE
            
                Return ONLY valid JSON in this exact format:
            
                {
                  "tool":"TEXT"
                }
            
                Do not include any other fields.
                Do not explain your answer.
            
                User:
                """ + prompt;

        String content = textGenerationService.intentClassifier(classifierPrompt);

        IntentResponse intentResponse =
                objectMapper.readValue(content, IntentResponse.class);

        return intentResponse.tool();
    }
}
