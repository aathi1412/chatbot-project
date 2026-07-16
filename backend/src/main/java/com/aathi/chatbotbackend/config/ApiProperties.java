package com.aathi.chatbotbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ai")
public record ApiProperties(

        String HUGGING_FACE_API_KEY,
        String CLOUDFLARE_API_KEY,

        String CLOUDFLARE_ACCOUNT_ID
) {
    public String huggingFaceUri() {
        return "https://router.huggingface.co/v1/chat/completions";
    }

    public String cloudflareUri() {
        return "https://api.cloudflare.com/client/v4/accounts/"
                + CLOUDFLARE_ACCOUNT_ID
                + "/ai/run/@cf/black-forest-labs/flux-1-schnell";
    }
}
