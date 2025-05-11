package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntry;
import net.engineeringdigest.journalApp.repository.journalEntryRepository;
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
    private journalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;
    @Transactional
    public void saveEntry(journalEntry journalEntry ,String userName) {
         User user=userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
         journalEntry saved=journalEntryRepository.save(journalEntry);
         user.getJournalEntries().add(saved);
         userService.saveEntry(user);

    }

    public  void  saveEntry(journalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<journalEntry> getAll() {

        return journalEntryRepository.findAll();

    }

    public Optional<journalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user=userService.findByUserName(userName);
            boolean remove = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(remove) {
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
                removed = true;
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException( " An error occured while deleting the entry"+ e);
        }
        return removed;
    }
}
