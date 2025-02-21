package com.ai.SpringAIDemo.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class RecipeService {

    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients, String cuisine, String dietaryRestriction) {

        var template = """
                        I want to create a recipe using ingredients {ingredients}. Cuisine should be {cuisine} with the dietary restrictions as {dietaryRestriction}.
                        Please provide me a details recipe including list of ingredients used and cooking instructions.
                        """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "ingredients" , ingredients,
                "cuisine" , cuisine,
                "dietaryRestriction" , dietaryRestriction
        );

        Prompt prompt = promptTemplate.create(params);
        ChatResponse response = chatModel.call(prompt);
         return response.getResult().getOutput().getContent();
    }
}
