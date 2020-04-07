package com.quartz.quartz.actuator.quartz.model;


import org.quartz.JobDetail;

public class JobModel {
	private String name;
	private boolean isConcurrentDisallowed;
	private boolean isDurable;
	private String jobClass;

	public JobModel(JobDetail jobDetail) {

		setName(jobDetail.getKey().getName());
		setDurable(jobDetail.isDurable());
		setConcurrentDisallowed(jobDetail.isConcurrentExectionDisallowed());
		setJobClass(jobDetail.getJobClass().getName());

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isConcurrentDisallowed() {
		return isConcurrentDisallowed;
	}

	public void setConcurrentDisallowed(boolean concurrentDisallowed) {
		isConcurrentDisallowed = concurrentDisallowed;
	}

	public boolean isDurable() {
		return isDurable;
	}

	public void setDurable(boolean durable) {
		isDurable = durable;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
}
