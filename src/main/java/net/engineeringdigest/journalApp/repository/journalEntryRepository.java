package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface journalEntryRepository extends MongoRepository<journalEntry, ObjectId> {
    // No custom queries needed for default functionality
}
