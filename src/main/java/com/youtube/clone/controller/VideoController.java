package com.youtube.clone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @GetMapping("/")
    public String home() {
        return "Welcome to YouTube Clone!";
    }

    @GetMapping("/videos")
    public String videos() {
        return "Here are some trending videos!";
    }
}

