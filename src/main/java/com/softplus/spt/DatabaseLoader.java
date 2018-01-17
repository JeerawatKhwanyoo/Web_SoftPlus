package com.softplus.spt;

import com.softplus.spt.domain.Contact;
import com.softplus.spt.repostirory.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ContactRepository repository;

    @Autowired
    public DatabaseLoader(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
//    this.repository.save(new Contact("Frodo", "Baggins", "ring bearer"));
//    this.repository.save(new Contact("Jeerawat", "khwanyoo", "developer trainee"));
//    this.repository.save(new Contact("Aphisit", "Namracha", "software developer"));
//    this.repository.save(new Contact("Micle", "Angle", "Programmer"));
//    this.repository.save(new Contact("Rockstar", "Mawto", "ComputerScine"));
}
}