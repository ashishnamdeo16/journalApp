package com.crazym416.journalApp.repository;

import com.crazym416.journalApp.entity.JournalEntry;
import com.crazym416.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String user);
}
