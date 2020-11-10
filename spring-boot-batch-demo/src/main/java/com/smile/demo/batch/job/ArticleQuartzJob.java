package com.smile.demo.batch.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author smile
 */
@Component
@Slf4j
@DisallowConcurrentExecution
public class ArticleQuartzJob extends QuartzJobBean {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobLocator jobLocator;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Job job = jobLocator.getJob("articleJob");
            jobLauncher.run(job, new JobParametersBuilder()
                    .addString("executedTime", "2020-11-10 16:21:01")
                    .toJobParameters());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("任务[articleJob]启动失败，错误信息:{}", e.getMessage());
        }
    }
}