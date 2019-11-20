package com.qz.example.job;

import com.qz.example.job.SpringTaskService;

public class SpringTaskServiceImpl implements SpringTaskService {

    @Override
    public void simpleTask() {
        System.out.println("spring task 简单任务执行。。。");
    }

    @Override
    public void cornTask() {
        System.out.println("spring task corn任务执行。。。");
    }
}
