package com.ai.SpringAIDemo;

import com.ai.SpringAIDemo.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @MockBean
    private ChatModel chatModel;

    private final static String recipe = "created";

    @Test
    void  createRecipeTest(){
        ChatResponse chatResponse = new ChatResponse(List.of
                (new Generation(new AssistantMessage(recipe))));
        when(chatModel.call(any(Prompt.class))).thenReturn(chatResponse);
        String response = recipeService.createRecipe("meat", "greek", "no garlic");
        assert response.equals(recipe);
    }

}
