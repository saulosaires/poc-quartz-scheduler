package com.quartz.quartz.actuator.quartz;

import java.util.Set;

import com.quartz.quartz.actuator.quartz.model.GroupModel;
import com.quartz.quartz.actuator.quartz.model.JobDetailModel;
import com.quartz.quartz.actuator.quartz.model.JobModel;
import com.quartz.quartz.actuator.quartz.service.TriggerModelBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;

@Endpoint(id = "quartz-jobs")
public class QuartzJobEndPoint {

	private Scheduler scheduler;

	private TriggerModelBuilder triggerModelBuilder = new TriggerModelBuilder();

	public QuartzJobEndPoint(Scheduler scheduler){
		this.scheduler = scheduler;
	}

	@ReadOperation
	public GroupModel<JobModel> listJobs() throws SchedulerException {
		try {

			Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());

			if (jobKeys == null || jobKeys.isEmpty()) {
				return new GroupModel<>();
			}

			GroupModel<JobModel> jobGroupModel = new GroupModel<>();

			jobKeys.forEach(key->{
				JobModel model = createJobModel(key);
				jobGroupModel.add(key.getGroup(), model);
			});

			return jobGroupModel;

		} catch (SchedulerException e) {
			throw e;
		}
	}

	@ReadOperation
	public JobDetailModel getJobDetail(@Selector String group, @Selector String name) throws SchedulerException{

		JobDetail jobDetail = scheduler.getJobDetail(new JobKey(name, group));
		JobDetailModel model = new JobDetailModel(jobDetail);

		model.setGroup(jobDetail.getKey().getGroup());
		model.setTriggers(triggerModelBuilder.buildTriggerDetailModel(scheduler, jobDetail.getKey()));

		return model;
	}

	private JobModel createJobModel(JobKey key){
		try {
			JobDetail jobDetail = scheduler.getJobDetail(key);
			if (jobDetail == null) {
				return null;
			}

			return  new JobModel(jobDetail);

		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}

	}

}
