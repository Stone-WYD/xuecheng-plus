package com.wyd.xuecheng.media.service.jobhandler;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: xuecheng-plus-media
 * @author: Stone
 * @create: 2024-04-10 11:34
 */
@Component
@Slf4j
public class SampleJob {

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("testJob")
    public void testJob() throws Exception {
        log.info("开始执行xxl-job任务测试内容.....");
    }

}