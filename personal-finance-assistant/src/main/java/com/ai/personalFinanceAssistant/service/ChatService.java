package com.ai.personalFinanceAssistant.service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
public class ChatService {


    @Autowired
    private ChatClient chatClient;

    @Value("classpath:prompt/system.txt")
    private Resource resource;

    public String getResponse(String prompt) throws IOException {
        String systemPrompt= new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);
        return chatClient.prompt().system(systemPrompt).user(prompt).call().content();
    }
}
