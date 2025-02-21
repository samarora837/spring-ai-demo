package com.ai.SpringAIDemo.controller;

import com.ai.SpringAIDemo.service.ChatService;
import com.ai.SpringAIDemo.service.ImageService;
import com.ai.SpringAIDemo.service.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SpringAIController {

    private final ChatService chatService;

    private final ImageService imageService;

    private final RecipeService recipeService;

    public SpringAIController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String userPrompt){
        return chatService.getResponseFromAI(userPrompt);
    }

    @GetMapping("ask-ai2")
    public String getResponse2(@RequestParam String userPrompt){
        return chatService.getResponse2FromAI(userPrompt);
    }

    @GetMapping("generate-image")
    public void getImages(HttpServletResponse response , @RequestParam String userPrompt) throws IOException {
        ImageResponse imageResponse =  imageService.generateImage(userPrompt);
        String url = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(url);
    }

    @GetMapping("generate-image-options")
    public List<String> getImagesWithOptions( @RequestParam String userPrompt,
                                              @RequestParam(defaultValue = "hd") String quality,
                                              @RequestParam(defaultValue = "1") int n,
                                              @RequestParam(defaultValue = "1024") int width,
                                              @RequestParam(defaultValue = "1024") int height) {
        ImageResponse imageResponse =  imageService.generateImageWithOptions(userPrompt, quality, n, width, height);
        //streams to get url from image response
        return imageResponse.getResults().stream().map(
                result -> result.getOutput().getUrl()).toList();
    }

    @GetMapping("create-recipe")
    public String createRecipe(@RequestParam String ingredients,
                                     @RequestParam(defaultValue = "hd") String cuisine,
                                     @RequestParam(defaultValue = "") String dietaryRestriction){
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestriction);

    }
}
