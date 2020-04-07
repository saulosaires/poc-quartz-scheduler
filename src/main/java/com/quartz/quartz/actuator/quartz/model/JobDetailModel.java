package com.quartz.quartz.actuator.quartz.model;

import org.quartz.JobDetail;

import java.util.List;

public class JobDetailModel extends JobModel{
	private String group;
	private List<TriggerDetailModel> triggers;

	public JobDetailModel(JobDetail jobDetail) {
		super(jobDetail);

	}

	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public List<TriggerDetailModel> getTriggers() {
		return triggers;
	}
	public void setTriggers(List<TriggerDetailModel> triggers) {
		this.triggers = triggers;
	}
}
