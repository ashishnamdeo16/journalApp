package com.crazym416.journalApp.controller;

import com.crazym416.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> JournalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(JournalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry){
        JournalEntries.put(entry.getId(),entry);
        return true;
    }

    @GetMapping("id/{id}")
    public JournalEntry getEntryById(@PathVariable long id){
        return JournalEntries.get(id);
    }

    @DeleteMapping("id/{id}")
    public JournalEntry deleteById(@PathVariable long id){
        return JournalEntries.remove(id);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateById(@PathVariable long id,@RequestBody JournalEntry entry){
        return JournalEntries.put(entry.getId(),entry);
    }



}
