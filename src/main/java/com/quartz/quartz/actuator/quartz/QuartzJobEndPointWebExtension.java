package com.quartz.quartz.actuator.quartz;

import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;

@EndpointWebExtension(endpoint=QuartzJobEndPoint.class)
public class QuartzJobEndPointWebExtension {

	private QuartzJobEndPoint quartzJobEndPoint ;

	public QuartzJobEndPointWebExtension(QuartzJobEndPoint quartzJobEndPoint){
		this.quartzJobEndPoint = quartzJobEndPoint;
	}


}
