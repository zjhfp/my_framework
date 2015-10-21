package com.pan.bbf.init;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.pan.bbf.common.executor.impl.ExecutorManagerImpl;
import com.pan.bbf.common.utils.SpringHelper;

/**
 *
 * 初始化应用
 *
 */
public class InitBbfServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(InitBbfServlet.class);
	
	private ExecutorManagerImpl executorManager = (ExecutorManagerImpl) SpringHelper.getBean("executorManagerImpl");
	
	public void init(ServletConfig cfg) throws javax.servlet.ServletException {
        log.debug("Init bbf busniess system..... begin.....");
        executorManager.init();
        log.debug("Init bbf busniess system..... end.....");
    }
	
	@Override
    public void destroy() {
        log.debug("servlet destroy begin......");
        executorManager.shutdown();
        super.destroy();
        log.debug("servlet destroy end......");
    }
}
