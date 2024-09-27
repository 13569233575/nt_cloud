package com.cd.project.config;


import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 本地配置处理
 */
@Slf4j
public class LocalConfigInfoProcessor {

    public static void init(){
        log.info("init local config...");
        log.info("current LOG.PATH : {}",System.getProperty("LOG.PATH"));
    }

    static {
        System.setProperty("LOG.PATH", System.getProperty("user.home")+ File.separator + "sclog");
        //System.setProperty("LOG.PATH", "E:/WebStorm/sclog/logs");
    }
}
