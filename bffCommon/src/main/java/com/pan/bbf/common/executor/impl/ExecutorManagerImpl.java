package com.pan.bbf.common.executor.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.pan.bbf.common.executor.ExecutorManager;


@Component
public class ExecutorManagerImpl implements ExecutorManager{
	
	//线程数上限
    private static int taskMaxNumber = Integer.MAX_VALUE;

    //线程数量下限
    private static int taskMinNumber = 0;

    //任务队列容量
    private static int queueCapacity = Integer.MAX_VALUE;

    private ExecutorService service;

    @Override
    public void init() {
        service = new ThreadPoolExecutor(taskMinNumber, taskMaxNumber, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(queueCapacity));
    }

    @Override
    public void shutdown() {
        service.shutdown();
    }

    @Override
    public ExecutorService getExecutorService() {
        return service;
    }
}
