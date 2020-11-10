package com.smile.demo.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


/**
 * 定时任务demo
 * - @DisallowConcurrentExecution 当前任务未执行完前不会执行新的任务
 * @author smile
 */
@Component
@Slf4j
@DisallowConcurrentExecution
public class QuartzDemoJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("！！！开始执行--测试定时任务");
    }
}