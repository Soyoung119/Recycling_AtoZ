package com.example.RecyclingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class VisionController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @RequestMapping("/getLabelDetection")
    public String getLabelDetection() {
        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Lion_d%27Afrique.jpg/290px-Lion_d%27Afrique.jpg";
        Resource imageResource = this.resourceLoader.getResource(imageUrl);
        //Resource imageResource = this.resourceLoader.getResource("file:/Users/soyoungahn/Downloads/coke.jpg");
        AnnotateImageResponse response = this.cloudVisionTemplate.analyzeImage(imageResource,
                Feature.Type.LABEL_DETECTION);
        return response.getLabelAnnotationsList().toString();
    }

    @RequestMapping("/getLandmarkDetection")
    public String getLandmarkDetection() {
//        String imageUrl =
//        Resource imageResource = this.resourceLoader.getResource(imageUrl);
        Resource imageResource = this.resourceLoader.getResource("file:/Users/soyoungahn/Downloads/aaa.jpg");
        AnnotateImageResponse response = this.cloudVisionTemplate.analyzeImage(imageResource,
                Feature.Type.LANDMARK_DETECTION);
        return response.getLandmarkAnnotationsList().toString();
    }

    @RequestMapping("/extractTextFromImage")
    public String extract() {
//        String imageUrl = "https://cloud.google.com/vision/docs/images/sign_text.png";
//        return this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(imageUrl));

        Resource imageResource = this.resourceLoader.getResource("file:/Users/soyoungahn/Downloads/text.jpg");

        return this.cloudVisionTemplate.extractTextFromImage(imageResource);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<List<String>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Transform MultipartFile to Resource
            Resource imageResource = new InputStreamResource(file.getInputStream());

            // transfer the file to API and return a result
            AnnotateImageResponse response = cloudVisionTemplate.analyzeImage(imageResource, Feature.Type.LABEL_DETECTION);

            // Return value of description
            List<String> descriptions = response.getLabelAnnotationsList().stream()
                    .map(labelAnnotation -> labelAnnotation.getDescription())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(descriptions);
        } catch (IOException e) {
            e.printStackTrace();
            // Exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList("Error analyzing the image."));
        }
    }
}