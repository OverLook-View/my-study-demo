package com.example.sy.elasticjob.demo.schedule;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 测试定时任务
 * @author: OverlookView
 * @create: 2018-12-23 18:39
 **/
@ElasticSimpleJob(cron = "0 * * * * ?", jobName = "testJob", shardingTotalCount = 2, jobParameter = "测试参数", shardingItemParameters = "0=A,1=B")
@Component
public class MySimpleJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format("------Thread ID: %s, 任务总片数: %s, "
                        + "当前分片项: %s.当前参数: %s,"
                        + "当前任务名称: %s.当前任务参数: %s"
                        + " 时间:" + new Date()
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()
        ));
    }
}
