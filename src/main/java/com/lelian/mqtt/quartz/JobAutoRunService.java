package com.lelian.mqtt.quartz;

import com.lelian.mqtt.service.SLRemoteService;
import com.lelian.mqtt.service.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobAutoRunService implements ApplicationRunner {

    @Autowired
    public SchedulerManager schedulerManager;

    @Autowired
    ServiceApi serviceApi;

    @Autowired
    SLRemoteService slRemoteService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //初始化操作
        slRemoteService.initSLMap();
        //先执行一次操作
        serviceApi.refreshAccessToken();
        serviceApi.getRealTimeData();
        //每5个小时刷新一下token
        schedulerManager.startJob("0 0 0/5 * * ? *","tokenJob","tokenJobGroup", TokenScheduledJob.class);
        //每10分鐘执行一次
        schedulerManager.startJob("0 0/10 * * * ? *","realTimeJob","realTimeJobGroup", RealTimeScheduledJob.class);
    }

}
