package com.sree.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/api/")
public class NewFeatureController {

    @PostMapping("/submit")
    public void submit(){
        //loading
    }
}
