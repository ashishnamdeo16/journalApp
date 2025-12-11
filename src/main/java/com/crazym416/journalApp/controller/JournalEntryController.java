package com.crazym416.journalApp.controller;

import com.crazym416.journalApp.entity.JournalEntry;
import com.crazym416.journalApp.entity.User;
import com.crazym416.journalApp.service.JournalEntryService;
import com.crazym416.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        if(user != null){
            List<JournalEntry> entries = user.getJournalEntries();
            return new ResponseEntity<>(entries, HttpStatus.OK); // return journal entries list
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // user not found
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry,@PathVariable String userName){
        try{
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry,userName);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id,@PathVariable String userName){
        if(journalEntryService.getEntryById(id).isPresent()){
            journalEntryService.deleteEntrybyId(id,userName);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id,
                                        @RequestBody JournalEntry entry,
                                        @PathVariable String userName){
        System.out.println("PUT called for user: " + userName + " with entryId: " + id);
        JournalEntry oldEntry = journalEntryService.getEntryById(id).orElse(null);
        if(oldEntry != null ){
            oldEntry.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
