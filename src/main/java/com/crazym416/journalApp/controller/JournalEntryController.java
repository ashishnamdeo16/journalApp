package com.crazym416.journalApp.controller;

import com.crazym416.journalApp.entity.JournalEntry;
import com.crazym416.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return entry;
    }

    @GetMapping("id/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id){
        return journalEntryService.getEntryById(id).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public boolean deleteById(@PathVariable ObjectId id){
        if(journalEntryService.getEntryById(id).isPresent()){
            journalEntryService.deleteEntrybyId(id);
            return true;
        }
        return false;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateById(@PathVariable ObjectId id,@RequestBody JournalEntry entry){
        JournalEntry oldEntry = journalEntryService.getEntryById(id).orElse(null);
        if(oldEntry != null ){
            oldEntry.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }



}
