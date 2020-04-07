package com.quartz.quartz.service;

import com.quartz.quartz.exception.QuartzException;
import com.quartz.quartz.job.Poc2Job;
import com.quartz.quartz.job.PocJob;
import com.quartz.quartz.job.QuartzJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class QuartzJobScheduler implements JobScheduler{

    @Autowired
    Scheduler scheduler;

    @Override
    public boolean start() throws SchedulerException {

        QuartzJob poc = new PocJob();
        QuartzJob poc2 = new Poc2Job();

        scheduler.scheduleJob(poc.jobDetail(), poc.jobTrigger());
        scheduler.scheduleJob(poc2.jobDetail(),poc2.jobTrigger());
        scheduler.start();

        return true;
    }

    public Date rescheduleJob(String group,String jobIdentity, Integer intervalInSeconds) throws SchedulerException, QuartzException {

        scheduler.deleteJob(JobKey.jobKey(jobIdentity,group));

        QuartzJob job =selectJob(jobIdentity);

        return  scheduler.scheduleJob(job.jobDetail(), job.jobTrigger(intervalInSeconds));
    }

    public boolean cancelJob(String group,String jobIdentity) throws SchedulerException {

        return scheduler.deleteJob(JobKey.jobKey(jobIdentity,group));

    }

    private QuartzJob selectJob(String jobIdentity) throws QuartzException {

        switch (jobIdentity){

            case PocJob.JOB_NAME: return new PocJob();
            case Poc2Job.JOB_NAME:return new Poc2Job();
        }

        throw new QuartzException("Invalid job name:"+jobIdentity);
    }

}
