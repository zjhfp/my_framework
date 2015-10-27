package com.pan.bbf.schedule;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * 
 * 定时任务管理及监控工具库
 *
 */
public class JobScheduleManager {

	private Logger log = Logger.getLogger(JobScheduleManager.class);
	
	private Scheduler scheduler;
	
	private static final String quartzFileName = "config/quartz.properties";
	
	@PostConstruct
	private void initScheduler() throws SchedulerException {
		this.scheduler = this.getScheduler();
	}
	
	public Scheduler getScheduler() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory(quartzFileName);
		Scheduler scheduler = sf.getScheduler();
		return scheduler;
	}
	
	/**
	 * <h1>当任务保存到系统后，进行停止服务，在启动服务时，需要调用当前方法</h1>
	 * @throws SchedulerException
	 */
	public void init() throws SchedulerException{
		scheduler.start();
	}
	
	/**
	 * <h1>启动任务,用Cron表达式</h1>
	 * @param jobDetailVo
	 * @throws SchedulerException
	 */
	public void runJobByCronSchedule(JobDetailVo jobDetailVo)
			throws SchedulerException {
		TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder
													.newTrigger()
													.withSchedule(CronScheduleBuilder.cronSchedule(jobDetailVo.getCronExpression()));
		if(StringUtils.isNotEmpty(jobDetailVo.getJobDetail().getName())&&StringUtils.isNotEmpty(jobDetailVo.getJobDetail().getGroup())){
			triggerBuilder.withIdentity(jobDetailVo.getJobDetail().getName(), jobDetailVo.getJobDetail().getGroup());
		}else if(StringUtils.isNotEmpty(jobDetailVo.getJobDetail().getName())&&StringUtils.isEmpty(jobDetailVo.getJobDetail().getGroup())){
			triggerBuilder.withIdentity(jobDetailVo.getJobDetail().getName());
		}
		
		if(log.isDebugEnabled()){
			log.debug("jobDetailVo:"+ToStringBuilder.reflectionToString(jobDetailVo));
		}
		
		if(jobDetailVo.getStartDate()!=null){
			triggerBuilder.startAt(jobDetailVo.getStartDate());
		}
		if(jobDetailVo.getEndDate()!=null){
			triggerBuilder.endAt(jobDetailVo.getEndDate());
		}
		Trigger trigger = triggerBuilder.build();
		JobDetail job = newJob(jobDetailVo.getJobDetail().getJobClass())
	            .withIdentity(jobDetailVo.getJobDetail().getName(), jobDetailVo.getJobDetail().getGroup())
	            .build();
		JobDataMap jobDataMap = jobDetailVo.getJobDetail().getJobDataMap();
		if(jobDataMap!=null&&jobDataMap.entrySet()!=null){
			for(Map.Entry<String, Object> entry:jobDataMap.entrySet()){
				job.getJobDataMap().put(entry.getKey(), entry.getValue());
			}
		}
		if(jobDetailVo.getTrigger()!=null){
			scheduler.scheduleJob(job, jobDetailVo.getTrigger());
		}else{
			scheduler.scheduleJob(job, trigger);
		}
		scheduler.start();
	}
	/**
	 * <h1>启动任务,直接输入参数</h1>
	 * @param jobDetailVo
	 * @throws SchedulerException
	 */
	public void runJobByScheduleInterval(JobDetailVo jobDetailVo)
			throws SchedulerException {
		SimpleScheduleBuilder simpleSchedule = null;
		if(jobDetailVo.getIntervalUnit().name().equals(IntervalUnit.SECOND)){
			simpleSchedule = simpleSchedule().withRepeatCount(jobDetailVo.getTriggerRepeatCount()).withIntervalInSeconds(jobDetailVo.getIntervalTime());
		}else if(jobDetailVo.getIntervalUnit().name().equals(IntervalUnit.HOUR)){
			simpleSchedule = simpleSchedule().withRepeatCount(jobDetailVo.getTriggerRepeatCount()).withIntervalInHours(jobDetailVo.getIntervalTime());
		}else if(jobDetailVo.getIntervalUnit().name().equals(IntervalUnit.MINUTE)){
			simpleSchedule = simpleSchedule().withRepeatCount(jobDetailVo.getTriggerRepeatCount()).withIntervalInMinutes(jobDetailVo.getIntervalTime());
		}else if(jobDetailVo.getIntervalUnit().name().equals(IntervalUnit.MILLISECOND)){
			simpleSchedule = simpleSchedule().withRepeatCount(jobDetailVo.getTriggerRepeatCount()).withIntervalInMilliseconds(jobDetailVo.getIntervalTime());
		}
		simpleSchedule.repeatForever();
		TriggerBuilder<SimpleTrigger> triggerBuilder = newTrigger()
                .startAt(futureDate(jobDetailVo.getInterval(), jobDetailVo.getIntervalUnit()))
                .withSchedule(simpleSchedule);
		if(StringUtils.isNotEmpty(jobDetailVo.getJobDetail().getName())&&StringUtils.isNotEmpty(jobDetailVo.getJobDetail().getGroup())){
			triggerBuilder.withIdentity(jobDetailVo.getJobDetail().getName(), jobDetailVo.getJobDetail().getGroup());
		}else if(StringUtils.isNotEmpty(jobDetailVo.getJobDetail().getName())&&StringUtils.isEmpty(jobDetailVo.getJobDetail().getGroup())){
			triggerBuilder.withIdentity(jobDetailVo.getJobDetail().getName());
		}
		Trigger trigger = triggerBuilder.build();
		JobDetail job = newJob(jobDetailVo.getJobDetail().getJobClass())
	            .withIdentity(jobDetailVo.getJobDetail().getName(), jobDetailVo.getJobDetail().getGroup())
	            .build();
		
		if(log.isDebugEnabled()){
			log.debug("jobDetailVo:"+jobDetailVo.toString()+"  JobDetail:"+ToStringBuilder.reflectionToString(job));
		}
		JobDataMap jobDataMap = jobDetailVo.getJobDetail().getJobDataMap();
		if(jobDataMap!=null&&jobDataMap.entrySet()!=null){
			for(Map.Entry<String, Object> entry:jobDataMap.entrySet()){
				job.getJobDataMap().put(entry.getKey(), entry.getValue());
			}
		}
		
		if(jobDetailVo.getTrigger()!=null){
			scheduler.scheduleJob(job, jobDetailVo.getTrigger());
		}else{
			scheduler.scheduleJob(job, trigger);
		}
		scheduler.start();
	}
	/**
	 * <h1>批量启动任务</h1>
	 * @param triggersAndJobs
	 * @throws SchedulerException
	 */
	public void runJobBySchedules(Map<JobDetail, Set<? extends Trigger>> triggersAndJobs)
			throws SchedulerException {
		scheduler.start();
		scheduler.scheduleJobs(triggersAndJobs, true);
	}
	
	/**
	 * <h1>恢复所有任务</h1>
	 * @throws SchedulerException
	 */
	public void resumeAll() throws SchedulerException {
		scheduler.resumeAll();
	}
	/**
	 * <h1>恢复指定的job任务</h1>
	 * @param jobKey
	 * @throws SchedulerException
	 */
	public void resumeJob(JobKey jobKey) throws SchedulerException {
		scheduler.resumeJob(jobKey);
	}
	/**
	 * <h1>批量恢复任务</h1>
	 * @param matcher
	 * @throws SchedulerException
	 */
	public void resumeJobs(GroupMatcher<JobKey> matcher) throws SchedulerException {
		scheduler.resumeJobs(matcher);
	}
	/**
	 * <h1>恢复指定的触发器</h1>
	 * @param triggerKey
	 * @throws SchedulerException
	 */
	public void resumeTrigger(TriggerKey triggerKey) throws SchedulerException {
		scheduler.resumeTrigger(triggerKey);
	}
	/**
	 * <h1>批量恢复触发器</h1>
	 * @param matcher
	 * @throws SchedulerException
	 */
	public void resumeTriggers(GroupMatcher<TriggerKey> matcher) throws SchedulerException {
		scheduler.resumeTriggers(matcher);
	}
	/**
	 * <h1>停止指定的任务</h1>
	 * @param jobKey
	 * @throws SchedulerException
	 */
	public void pauseJob(JobKey jobKey) throws SchedulerException {
		scheduler.pauseJob(jobKey);
	}
	/**
	 * <h1>批量暂停任务</h1>
	 * @param matcher
	 * @throws SchedulerException
	 */
	public void pauseJobs(GroupMatcher<JobKey> matcher) throws SchedulerException {
		scheduler.pauseJobs(matcher);
	}
	/**
	 * <h1>暂停知道的触发器</h1>
	 * @param triggerKey
	 * @throws SchedulerException
	 */
	public void pauseTrigger(TriggerKey triggerKey) throws SchedulerException {
		scheduler.pauseTrigger(triggerKey);
	}
	/**
	 * <h1>批量暂停触发器</h1>
	 * @param matcher
	 * @throws SchedulerException
	 */
	public void pauseTriggers(GroupMatcher<TriggerKey> matcher) throws SchedulerException {
		scheduler.pauseTriggers(matcher);
	}
	/**
	 * <h1>暂停所有的触发器</h1>
	 * @throws SchedulerException
	 */
	public void pauseAll() throws SchedulerException {
		scheduler.pauseAll();
	}
	
	public boolean isJobKey(JobKey jobKey) throws SchedulerException {
		return scheduler.checkExists(jobKey);
	}
	/**
	 * <h1>判断是否有触发器</h1>
	 * @param triggerKey
	 * @return
	 * @throws SchedulerException
	 */
	public boolean isTriggerKey(TriggerKey triggerKey) throws SchedulerException {
		return scheduler.checkExists(triggerKey);
	}

	/**
	 * <h1>查询是否有任务,并返回任务实例</h1>
	 * @param jobKey
	 * @return
	 * @throws SchedulerException
	 */
	public JobDetailImpl getJobDetail(JobKey jobKey) throws SchedulerException {
		return (JobDetailImpl) scheduler.getJobDetail(jobKey);
	}
	/**
	 * <h1>获取批量的任务</h1>
	 * @param matcher
	 * @return
	 * @throws SchedulerException
	 */
	public Set<JobKey> getJobDetail(GroupMatcher<JobKey> matcher) throws SchedulerException {
		return scheduler.getJobKeys(matcher);
	}
	/**
	 * <h1>删除任务</h1>
	 * @param jobKey
	 * @throws SchedulerException
	 */
	public void deleteJob(JobKey jobKey) throws SchedulerException {
		if(log.isDebugEnabled()){
			log.debug("jobKey:"+jobKey);
		}
		scheduler.deleteJob(jobKey);
	}
	/**
	 * <h1>获取触发器的状态</h1>
	 * @param triggerKey
	 * @return
	 * @throws SchedulerException
	 */
	public TriggerState getTriggerState(TriggerKey triggerKey) throws SchedulerException {
		return scheduler.getTriggerState(triggerKey);
	}
	/**
	 * <h1>停止任务</h1>
	 * @throws SchedulerException
	 */
	public void shutdown() throws SchedulerException {
		scheduler.shutdown(true);
	}
	/**
	 * <h1>获取所有组</h1>
	 * @return
	 * @throws SchedulerException
	 */
	public List<String> getJobGroupNames() throws SchedulerException{
		return scheduler.getJobGroupNames();
	}
	/**
	 * <h1>获取所有的触发器的组</h1>
	 * @return
	 * @throws SchedulerException
	 */
	public List<String> getTriggerGroupNames() throws SchedulerException{
		return scheduler.getTriggerGroupNames();
	}
	/**
	 * <h1>保存任务</h1>
	 * @param jobDetail
	 * @throws SchedulerException
	 */
	public void saveOrUpdateJob(JobDetailImpl jobDetail)
			throws SchedulerException {
		scheduler.addJob(jobDetail, true);
	}
	
	/**
	 * <h1>移除任务</h1>
	 * @param triggerKey
	 * @return
	 */
	public boolean removeTrigdger(TriggerKey triggerKey){       
        try {  
            scheduler.pauseTrigger(triggerKey);//停止触发器  
            return scheduler.unscheduleJob(triggerKey);//移除触发器  
        } catch (SchedulerException e) {  
            throw new RuntimeException(e);  
        }  
    }
}
