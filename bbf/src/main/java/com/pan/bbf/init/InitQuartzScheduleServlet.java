package com.pan.bbf.init;


import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.pan.bbf.common.utils.SpringHelper;
import com.pan.bbf.schedule.JobDetailVo;
import com.pan.bbf.schedule.JobScheduleManager;

public class InitQuartzScheduleServlet extends HttpServlet {
    
    private static Logger logger = LoggerFactory.getLogger(InitQuartzScheduleServlet.class);

    private static final long serialVersionUID = 1L;
    private static final String CONFIG_PATH = "/config/quartzTask.properties";
    private static final String TASK_LIST = "tasklist";
    private Properties prop;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        ClassPathResource cr = new ClassPathResource(CONFIG_PATH);
        prop = new Properties();
        //新建或启动任务
        JobScheduleManager jobScheduleManager = (JobScheduleManager) SpringHelper.getBean("jobScheduleManager");
        try {
            prop.load(cr.getInputStream());
            //获取所有任务列表
            String[] tasklist = StringUtils.split(prop.getProperty(TASK_LIST), ',');
            for (String taskKey : tasklist) {
                //如果secheduler没有启动则先初始化
                if (!jobScheduleManager.getScheduler().isStarted()) {
                    jobScheduleManager.init();
                }
                JobKey jobKey = new JobKey(taskKey);
                startJob(jobKey, jobScheduleManager, taskKey);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    private void startJob(JobKey jobKey, JobScheduleManager jobScheduleManager, String taskKey) throws Exception {
        JobDetailImpl jobDetail = null;
        if (jobScheduleManager.isJobKey(jobKey)) {
            jobDetail = jobScheduleManager.getJobDetail(jobKey);
            if (jobDetail != null) {
                TriggerKey triggerKey = new TriggerKey(taskKey);
                jobScheduleManager.resumeTrigger(triggerKey);
            } else {
                createJob(jobKey, jobScheduleManager, taskKey);
            }
        } else {
            createJob(jobKey, jobScheduleManager, taskKey);
        }

    }

    @SuppressWarnings("unchecked")
    private void createJob(JobKey jobKey, JobScheduleManager jobScheduleManager, String taskKey) throws Exception {
        //定时参数
        String cron = prop.getProperty(taskKey + "_cron");
        //定时任务类
        String jobClass = prop.getProperty(taskKey + "_job");
        //创建任务
        JobDetailVo jobDetailVo = new JobDetailVo();

        JobDetailImpl jobDetail = new JobDetailImpl();
        //设置任务名
        jobDetail.setName(taskKey);
        //设置任务组
        jobDetail.setGroup(taskKey);
        //设置任务标识
        jobDetail.setKey(jobKey);
        //设置需要执行的job类
        jobDetail.setJobClass((Class<? extends Job>) Class.forName(jobClass));
        //将任务存入任务参数中
        jobDetailVo.setJobDetail(jobDetail);
        //设置定时所需参数 
        jobDetailVo.setCronExpression(cron);
        //往控制器中添加任务
        //执行任务
        jobScheduleManager.runJobByCronSchedule(jobDetailVo);
    }
}

