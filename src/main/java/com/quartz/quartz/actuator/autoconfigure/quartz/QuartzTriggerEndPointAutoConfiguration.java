package com.quartz.quartz.actuator.autoconfigure.quartz;

import com.quartz.quartz.actuator.quartz.QuartzTriggerEndPoint;
import com.quartz.quartz.actuator.quartz.QuartzTriggerEndPointWebExtension;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Scheduler.class, SchedulerFactory.class})
@AutoConfigureAfter(QuartzAutoConfiguration.class)
public class QuartzTriggerEndPointAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public QuartzTriggerEndPoint quartzTriggerEndPoint(Scheduler scheduler){
		return new QuartzTriggerEndPoint(scheduler);
	}

	@Bean
	@ConditionalOnBean(QuartzTriggerEndPoint.class)
	public QuartzTriggerEndPointWebExtension quartzTriggerEndPointWebExtension(QuartzTriggerEndPoint quartzTriggerEndPoint){
		return new QuartzTriggerEndPointWebExtension(quartzTriggerEndPoint);
	}
}
