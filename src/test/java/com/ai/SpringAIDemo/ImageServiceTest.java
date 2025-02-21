package com.ai.SpringAIDemo;

import com.ai.SpringAIDemo.service.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    @MockBean
    private OpenAiImageModel openAiImageModel;

    public static final String userPrompt = "A beautiful sunset over the mountains";

    @Test
    void generateImageTest(){
        List<ImageGeneration> generations = new ArrayList<>();
        generations.add(new ImageGeneration(new Image("The URL where the image can be accessed", "Base64 encoded image string")));
        ImageResponse imageResponse = new ImageResponse(generations);
        when(openAiImageModel.call(any())).thenReturn(imageResponse);

        ImageResponse outputResponse = imageService.generateImage(userPrompt);
        assert!(outputResponse.getResult().getOutput().getUrl()).isEmpty();
    }

    @Test
    void generateImageWithOptionsTest(){

        List<ImageGeneration> generations = new ArrayList<>();
        generations.add(new ImageGeneration(new Image("The URL where the image can be accessed", "Base64 encoded image string")));
        ImageResponse imageResponse = new ImageResponse(generations);

//        ImagePrompt p = new ImagePrompt(userPrompt,
//                        OpenAiImageOptions.builder()
//                                //      .withModel("dall-e-2")
//                                .withQuality("hd")
//                                .withN(3)
//                                .withHeight(720)
//                               .withWidth(720)
//                                .build());

        when(openAiImageModel.call(any())).thenReturn(imageResponse);

       ImageResponse response = imageService.generateImageWithOptions("anyString()", "=[" +
               "'" +
               "/anyString()",3,730,239);
        assert !(response.getResult().getOutput().getUrl()).isEmpty();

    }
}

