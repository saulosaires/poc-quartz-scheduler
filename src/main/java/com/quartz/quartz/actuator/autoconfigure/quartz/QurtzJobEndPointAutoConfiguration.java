package com.quartz.quartz.actuator.autoconfigure.quartz;

import com.quartz.quartz.actuator.quartz.QuartzJobEndPoint;
import com.quartz.quartz.actuator.quartz.QuartzJobEndPointWebExtension;
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
@ConditionalOnClass({ Scheduler.class, SchedulerFactory.class })
@AutoConfigureAfter(QuartzAutoConfiguration.class)
public class QurtzJobEndPointAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public QuartzJobEndPoint quartzJobEndPoint(Scheduler scheduler) {
		return new QuartzJobEndPoint(scheduler);
	}

	@Bean
	@ConditionalOnBean(QuartzJobEndPoint.class)
	public QuartzJobEndPointWebExtension quartzJobEndPointWebExtension(QuartzJobEndPoint quartzJobEndPoint) {
		return new QuartzJobEndPointWebExtension(quartzJobEndPoint);
	}

}
