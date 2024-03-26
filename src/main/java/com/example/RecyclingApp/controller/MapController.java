package com.example.RecyclingApp.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MapController {
    @GetMapping("/showMap")
    public String glass(){
        return "/items/glass";
    }

}
