package com.quartz.quartz.job;

import org.quartz.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;



@Component
public class PocJob implements QuartzJob {

    private static final String JOB_GROUP="MTJ";
    public static final String JOB_NAME="PocJob_Detail";
    public static final String JOB_TRIGGER="PocJob_Trigger";

    private static Integer defaultIntervalInSeconds=5;

    public SimpleTrigger jobTrigger() {
        return jobTrigger(defaultIntervalInSeconds);
    }

    public SimpleTrigger jobTrigger(Integer intervalInSeconds) {
        return TriggerBuilder.newTrigger().forJob( JobKey.jobKey(JOB_NAME,JOB_GROUP))
                .withIdentity(TriggerKey.triggerKey(JOB_TRIGGER,JOB_GROUP))
                .withDescription("PocJob trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(intervalInSeconds))
                .build();
    }

    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(PocJob.class)
                .storeDurably()
                .withIdentity(JobKey.jobKey(JOB_NAME,JOB_GROUP))
                .withDescription("Invoke PocJob service...")
                .build();
    }

    @Override
    public void execute(JobExecutionContext context) {

        System.out.println("PocJob executed:"+ LocalDateTime.now());
    }
}
