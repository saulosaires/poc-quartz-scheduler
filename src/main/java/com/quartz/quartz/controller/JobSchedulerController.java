package com.quartz.quartz.controller;


import com.quartz.quartz.exception.QuartzException;
import com.quartz.quartz.service.JobScheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("scheduler")
public class JobSchedulerController {

    @Autowired
    private JobScheduler jobScheduler;


    @PostMapping(value = "/startjobs")
    public String startJobs() throws SchedulerException {

        jobScheduler.start();

        return "startJobs";
    }

    @PostMapping(value = "/reschedule/{group}/{jobName}")
    public String rescheduleJob(@PathVariable("group") String group,
                                @PathVariable("jobName") String jobName,
                                @RequestParam(value = "IntervalInSecond", required = true) Integer IntervalInSecond) throws SchedulerException, QuartzException {

        jobScheduler.rescheduleJob(group,jobName,IntervalInSecond);

        return "rescheduleJob";
    }

    @PostMapping(value = "/cancel/{group}/{jobName}")
    public String cancelJob(@PathVariable("group") String group,@PathVariable("jobName") String jobName) throws SchedulerException {

        jobScheduler.cancelJob(group,jobName);

        return "cancelJob";
    }

}
