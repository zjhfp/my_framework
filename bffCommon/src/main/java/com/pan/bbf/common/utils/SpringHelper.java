package com.pan.bbf.common.utils;

import org.apache.log4j.Logger;
import org.omg.CORBA.SystemException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * 
 * 可使用当前类型，获取在spring配置文件中的bean(需要在web容器中加载)
 *
 */
public class SpringHelper implements ApplicationContextAware{
	
	private static final Logger log = Logger.getLogger(SpringHelper.class);
	
	private static ApplicationContext applicationContext;
	
	private static AbstractApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
        context = (AbstractApplicationContext) arg0;
	}

	/**
    * 取得存储在静态变量中的ApplicationContext.
    */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
    * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @throws SystemException 
    */
    public static Object getBean(String beanName) throws BeansException {
        if (log.isDebugEnabled()) {
            log.debug("beanName:" + beanName);
        }
        return applicationContext.getBean(beanName);
    }

    /**
    * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
    */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return (T) applicationContext.getBeansOfType(clazz);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        if (log.isDebugEnabled()) {
            log.debug("beanName:" + beanName + " requiredType:" + requiredType);
        }
        return (T) applicationContext.getBean(beanName, requiredType);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在common-config.xml中定义SpringContextHolder");
        }
    }

    /**
    * 清除applicationContext静态变量.
    */
    public static void cleanApplicationContext() {
        applicationContext = null;
    }

    public static void createSyncService(String beanName) throws NoSuchBeanDefinitionException {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        try {
            //Register the bean in the IoC
            BeanDefinition definition = BeanDefinitionBuilder.rootBeanDefinition(getBean(beanName).getClass())
                    .getBeanDefinition();
            beanFactory.registerBeanDefinition(beanName, definition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createSyncService(DefaultListableBeanFactory beanFactory, String beanName)
            throws NoSuchBeanDefinitionException {
        try {
            //Register the bean in the IoC
            BeanDefinition definition = BeanDefinitionBuilder.rootBeanDefinition(getBean(beanName).getClass())
                    .getBeanDefinition();
            beanFactory.registerBeanDefinition(beanName, definition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
