package com.qz.example.job;

import com.qz.example.job.mapper.TaskModelMapper;
import com.qz.example.job.model.TaskModel;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.List;
public class JobInitUtil {

    private StdSchedulerFactory schedulerFactory;
    private Scheduler scheduler;
    private static JobInitUtil instance=new JobInitUtil();
    public JobInitUtil(){
        if (scheduler == null)
        {
            try
            {
                schedulerFactory = new StdSchedulerFactory();
                schedulerFactory.initialize("spring/quartz.properties");
                scheduler = schedulerFactory.getScheduler();
            }
            catch (SchedulerException e)
            {
                throw new RuntimeException("cannot initiated Quartz ", e);
            }
        }
    }

    public  void initJob() throws SchedulerException {
        TaskModelMapper taskModelMapper = SpringUtil.getBean(TaskModelMapper.class);
        List<TaskModel> taskModels = taskModelMapper.selectAll();
        for (TaskModel taskModel : taskModels) {
            addJob(taskModel);
        }
        scheduler.start();
    }

    private void addJob(TaskModel taskModel) {
        try {
            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(taskModel.getName()));
            if(jobDetail==null){
                jobDetail=buildJobDetail(taskModel);
                Trigger trigger = buildTrigger(taskModel);
                scheduler.scheduleJob(jobDetail,trigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private Trigger buildTrigger(TaskModel taskModel) {
        TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger().withIdentity(taskModel.getName())
                .withSchedule(CronScheduleBuilder.cronSchedule(taskModel.getCorn()));
        return triggerBuilder.build();
    }

    private JobDetail buildJobDetail(TaskModel taskModel) {
        JobDetail jobDetail = null;
        try {
            Class<? extends Job> clazz = (Class<? extends Job>)Class.forName(taskModel.getJobClassPath());
            jobDetail = JobBuilder.newJob(clazz).withIdentity(taskModel.getName()).build();
            jobDetail.getJobDataMap().put("JOB_MODEL", taskModel);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return jobDetail;
    }

    public static JobInitUtil getInstance(){
        return instance;
    }
}
