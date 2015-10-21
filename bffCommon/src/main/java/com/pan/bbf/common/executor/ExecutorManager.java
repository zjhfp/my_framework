package com.pan.bbf.common.executor;

import java.util.concurrent.ExecutorService;

public interface ExecutorManager {
	
	void init();

    void shutdown();

    ExecutorService getExecutorService();
}
