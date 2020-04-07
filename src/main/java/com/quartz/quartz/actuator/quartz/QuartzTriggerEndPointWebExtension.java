package com.quartz.quartz.actuator.quartz;


import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;

@EndpointWebExtension(endpoint=QuartzTriggerEndPoint.class)
public class QuartzTriggerEndPointWebExtension {

	private QuartzTriggerEndPoint qurtzTriggerEndPoint ;

	public QuartzTriggerEndPointWebExtension(QuartzTriggerEndPoint qurtzTriggerEndPoint){
		this.qurtzTriggerEndPoint = qurtzTriggerEndPoint;
	}


}
