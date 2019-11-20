package com.qz.example.job.listener;

import com.qz.example.job.JobInitUtil;
import org.quartz.SchedulerException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JobInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new Thread(()->{
            try {
                JobInitUtil.getInstance().initJob();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
