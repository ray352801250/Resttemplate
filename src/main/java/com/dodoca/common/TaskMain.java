package com.dodoca.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaskMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TimeTask timeTask = applicationContext.getBean(TimeTask.class);
        timeTask.updateRedis();
    }
}
