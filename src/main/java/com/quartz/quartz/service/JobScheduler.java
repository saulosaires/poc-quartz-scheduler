package com.quartz.quartz.service;

import com.quartz.quartz.exception.QuartzException;
import org.quartz.SchedulerException;

import java.util.Date;

public interface JobScheduler {

    public boolean start() throws SchedulerException;
    public Date rescheduleJob(String group,String jobIdentity, Integer intervalInSeconds) throws SchedulerException, QuartzException;
    public boolean cancelJob(String group,String jobIdentity) throws SchedulerException;
}
