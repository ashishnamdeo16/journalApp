package com.crazym416.journalApp.scheduler;

import com.crazym416.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheScheduler {

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 * * ? * *")
    public void clearAppCache(){
        appCache.init();
    }

}
