package com.crazym416.journalApp.service;

import com.crazym416.journalApp.entity.JournalEntry;
import com.crazym416.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public boolean deleteEntrybyId(ObjectId id){
        if(journalEntryRepository.existsById(id)){
            journalEntryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }


}
