package com.quartz.quartz.actuator.quartz;

import java.util.Set;

import com.quartz.quartz.actuator.quartz.model.GroupModel;
import com.quartz.quartz.actuator.quartz.model.TriggerDetailModel;
import com.quartz.quartz.actuator.quartz.service.TriggerModelBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;


@Endpoint(id = "quartz-triggers")
public class QuartzTriggerEndPoint {
	private Scheduler scheduler;
	private TriggerModelBuilder triggerModelBuilder = new TriggerModelBuilder();

	public QuartzTriggerEndPoint(Scheduler scheduler){
		this.scheduler = scheduler;
	}

	@ReadOperation
	public GroupModel<TriggerDetailModel> listTriggers() throws SchedulerException {
		try {

			Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());

			if (triggerKeys == null || triggerKeys.isEmpty()) {
				return new GroupModel<>();
			}

			GroupModel<TriggerDetailModel> groupModel = new GroupModel<>();
			triggerKeys.forEach(key->{addTriggerDetailModel(groupModel, key);});

			return groupModel;

		} catch (SchedulerException e) {
			throw e;
		}
	}

	private void addTriggerDetailModel(GroupModel<TriggerDetailModel> groupModel, TriggerKey key){

		TriggerDetailModel model;
		try {
			model = triggerModelBuilder.buildTriggerDetailModel(scheduler, key);
			groupModel.add(key.getGroup(), model);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}


}
