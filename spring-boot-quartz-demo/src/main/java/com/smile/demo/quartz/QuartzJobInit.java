package com.smile.demo.quartz;

import com.smile.demo.quartz.job.QuartzDemoJob;
import com.smile.demo.quartz.utils.QuartzUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * 初始化定时任务
 * @author smile
 */
@Component
public class QuartzJobInit implements CommandLineRunner {

    @Autowired
    private QuartzUtils quartzUtils;


    @Override
    public void run(String... args) throws Exception {
        // 每十秒执行一次
        quartzUtils.addSingleJob(QuartzDemoJob.class, "demo-job", 10);
    }
}
