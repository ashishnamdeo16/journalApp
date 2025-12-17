package com.crazym416.journalApp.cache;

import com.crazym416.journalApp.entity.ConfigJournalEntity;
import com.crazym416.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        quotes_api
    }

    public Map<String,String> appCache;

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalEntity> all = configJournalAppRepository.findAll();
        for(ConfigJournalEntity configJournalEntity : all){
            appCache.put(configJournalEntity.getKey(),configJournalEntity.getValue());
        }
    }


}
