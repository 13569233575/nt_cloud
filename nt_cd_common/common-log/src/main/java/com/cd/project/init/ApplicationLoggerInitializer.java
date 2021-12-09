package com.cd.project.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;

/**
 * @description: spring boot admin 直接加载日志
 * @author: zjbing
 * @create: 2019-07-22 13:27
 **/
@Slf4j
public class ApplicationLoggerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String appName = environment.getProperty("spring.application.name");
        String logBase = environment.getProperty("LOGGING_PATH", "/home/nt/sclog/logs");
        String logpath = environment.getProperty("LOGGING_PATH",System.getProperty("user.home")+ File.separator + "sclog");
        log.info("路径地址： " + logBase + " 机器本身路径地址： " + logpath);
        System.setProperty("logging.file.name", String.format("%s/%s/debug.log", logBase, appName));
        //System.setProperty("logging.file.name", String.format("%s/%s/debug.log", logpath, appName));
    }
}