package com.smile.demo.batch.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * batch 任务监听器
 * @author smile
 */
@Component
@Slf4j
public class JobListener extends JobExecutionListenerSupport {

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("任务[{}]执行成功，参数：[{}]", jobExecution.getJobInstance().getJobName(),
					jobExecution.getJobParameters().getString("executedTime"));
		} else {
			log.info("任务[{}]执行失败", jobExecution.getJobInstance().getJobName());
			// TODO something
		}
	}
}