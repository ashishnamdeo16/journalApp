package com.crazym416.journalApp.repository;

import com.crazym416.journalApp.entity.ConfigJournalEntity;
import com.crazym416.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalEntity, ObjectId>{

}
