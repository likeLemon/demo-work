package com.lywq.demo.common.tasks;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author lywq WED
 * @title: tesk
 * @projectName demo
 * @description: 创建定时任务
 * @date 2019/11/8 19:54
 */
@Component
@EnableScheduling // 开始定时任务的注解
public class TimingTask {

//    @Scheduled(fixedRate = 5000)
//    public void job1() throws MalformedURLException {
//        System.out.println("定时任务1" + new Date());
//        Integer num = 0;
//        String url = "http://jx.xsxmovie.cn/images/url.html?userId="+num;
//        HttpClient4Util.doGet(url);
//    }
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void job2(){
//        System.out.println("定时任务2" + new Date());
//    }

}
