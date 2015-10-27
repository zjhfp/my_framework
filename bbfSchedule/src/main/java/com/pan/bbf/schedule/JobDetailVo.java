package com.pan.bbf.schedule;

import java.util.Date;

import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;

public class JobDetailVo {

	/**
	 * <h1>JobDetail的任务实例</h1>
	 */
	private JobDetailImpl jobDetail;
	/**
	 * <h1>触发器</h1>
	 */
	private Trigger trigger;
	/**
	 * <h1>开始日期</h1>
	 */
	private Date startDate;
	/**
	 * <h1>截止日期</h1>
	 */
	private Date endDate;
	/**
	 * <h1>cron的表达式</h1>
	 */
	private String cronExpression;
	/**
	 * <h1>间隔次数</h1>
	 */
	private int interval;
	/**
	 * <h1>trigger的重复次数</h1>
	 */
	private int triggerRepeatCount;
	/**
	 * <h1>间隔时间</h1>
	 */
	private int intervalTime;
	/**
	 * <h1>间隔的Unit</h1>
	 */
	private IntervalUnit intervalUnit;

	/**
	 * jobDetail
	 *
	 * @return  the jobDetail
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public JobDetailImpl getJobDetail() {
		return jobDetail;
	}

	/**
	 * <h1>JobDetail的任务实例</h1>
	 * @param jobDetail the jobDetail to set
	 */
	
	public void setJobDetail(JobDetailImpl jobDetail) {
		this.jobDetail = jobDetail;
	}

	/**
	 * trigger
	 *
	 * @return  the trigger
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public Trigger getTrigger() {
		return trigger;
	}

	/**
	 * <h1>触发器</h1>
	 * @param trigger the trigger to set
	 */
	
	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	/**
	 * startDate
	 *
	 * @return  the startDate
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * <h1>开始日期</h1>
	 * @param startDate the startDate to set
	 */
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * endDate
	 *
	 * @return  the endDate
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * <h1>截止日期</h1>
	 * @param endDate the endDate to set
	 */
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * cronExpression
	 *
	 * @return  the cronExpression
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * <h1>cron的表达式</h1>
	 * @param cronExpression the cronExpression to set
	 */
	
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * interval
	 *
	 * @return  the interval
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public int getInterval() {
		return interval;
	}

	/**
	 * <h1>间隔次数</h1>
	 * @param interval the interval to set
	 */
	
	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * triggerRepeatCount
	 *
	 * @return  the triggerRepeatCount
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public int getTriggerRepeatCount() {
		return triggerRepeatCount;
	}

	/**
	 * <h1>trigger的重复次数</h1>
	 * @param triggerRepeatCount the triggerRepeatCount to set
	 */
	
	public void setTriggerRepeatCount(int triggerRepeatCount) {
		this.triggerRepeatCount = triggerRepeatCount;
	}

	/**
	 * intervalUnit
	 *
	 * @return  the intervalUnit
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public IntervalUnit getIntervalUnit() {
		return intervalUnit;
	}

	/**
	 * <h1>间隔时间</h1>
	 * @param intervalUnit the intervalUnit to set
	 */
	
	public void setIntervalUnit(IntervalUnit intervalUnit) {
		this.intervalUnit = intervalUnit;
	}

	/**
	 * intervalTime
	 *
	 * @return  the intervalTime
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public int getIntervalTime() {
		return intervalTime;
	}

	/**
	 * @param intervalTime the intervalTime to set
	 */
	
	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}
}
