package com.smile.demo.batch;

import com.smile.demo.batch.job.ArticleQuartzJob;
import com.smile.demo.batch.utils.QuartzUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author smile
 */
@Component
public class QuartzJobInit implements CommandLineRunner {

    @Autowired
    private QuartzUtils quartzUtils;

    @Override
    public void run(String... args) throws Exception {
        quartzUtils.addSingleJob(ArticleQuartzJob.class, "articleJob", 60);
    }
}
