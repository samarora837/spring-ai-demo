package com.ai.SpringAIDemo;

import com.ai.SpringAIDemo.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @MockBean
    private ChatModel chatModel;

    public final String prompt = "Hi, how are you?";
    public final String response = "I am good.";


    @Test
    void getResponseFromAITest( ) {
        when(chatModel.call(anyString())).thenReturn(response);
        String output = chatService.getResponseFromAI(prompt);
        assert output.equals(response);
    }

    @Test
    void getResponse2FromAITest( ) {
        Prompt p = new Prompt(
                prompt,
                OpenAiChatOptions.builder()
                        .withModel("gpt-4on")
                        .withTemperature(0.8)
                        .build());

        ChatResponse chatResponse = new ChatResponse(List.of
                (new Generation(new AssistantMessage(response))));

        when(chatModel.call(p)).thenReturn(chatResponse);

        String output = chatService.getResponse2FromAI(prompt);
        assert output.equals(chatResponse.getResult().getOutput().getContent());
    }

}
