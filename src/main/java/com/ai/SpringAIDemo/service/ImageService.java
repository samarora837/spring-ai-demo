package com.ai.SpringAIDemo.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String userPrompt) {
        return openAiImageModel.call(
                new ImagePrompt(userPrompt)
        );
    }

    public ImageResponse generateImageWithOptions(String imagePrompt, String quality, int n, int width, int height) {
        return openAiImageModel.call(
                new ImagePrompt(imagePrompt,
                        OpenAiImageOptions.builder()
                          //      .withModel("dall-e-2")
                                .withQuality(quality)
                                .withN(n)
                                .withHeight(height)
                                .withWidth(width)
                                .build())
        );
    }
}

