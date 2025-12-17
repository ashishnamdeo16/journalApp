package com.crazym416.journalApp.controller;

import com.crazym416.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clear")
public class UtilityController {

    @Autowired
    private AppCache appCache;

    @GetMapping("/cache")
    public boolean clearCache(){
        appCache.init();
        return true;
    }
}
