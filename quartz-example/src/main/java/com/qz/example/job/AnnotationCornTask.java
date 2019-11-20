package com.qz.example.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnnotationCornTask {
    @Scheduled(cron = "0/10 * * * * ?")
    public void execute(){
        System.out.println("AnnotationCornTask 执行。。。");
    }
}

