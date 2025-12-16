package com.crazym416.journalApp.service;

import com.crazym416.journalApp.entity.JournalEntry;
import com.crazym416.journalApp.entity.User;
import com.crazym416.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.updateUser(user);
    }


    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    @Transactional
    public void deleteEntrybyId(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
            if(user.getJournalEntries().removeIf(x -> x.getId().equals(id))){
                userService.updateUser(user);
                journalEntryRepository.deleteById(id);
            }
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }


}
