package com.example.jenkinsdemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public String test(){
        return "ok:"+new Date();
    }

}
