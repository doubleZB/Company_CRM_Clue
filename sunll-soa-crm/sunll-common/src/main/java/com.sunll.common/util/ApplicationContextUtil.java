package com.sunll.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by sunll on 2016/2/22.
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext appCtx;

    @Override

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }

    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }


    public static <T> T getBean(Class<T> clz) {
        return (T) appCtx.getBean(clz);
    }

}
