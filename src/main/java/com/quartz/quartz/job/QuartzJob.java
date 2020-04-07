package com.quartz.quartz.job;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;

public interface QuartzJob extends Job {

     SimpleTrigger jobTrigger();
     SimpleTrigger jobTrigger(Integer intervalInSeconds);
     JobDetail jobDetail();
}
