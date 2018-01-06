package com.softplus.spt;

import com.softplus.spt.domain.Employee;
import com.softplus.spt.repostirory.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository repository;

    @Autowired
    public DatabaseLoader(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
    this.repository.save(new Employee("Frodo", "Baggins", "ring bearer"));
    this.repository.save(new Employee("Jeerawat", "khwanyoo", "developer trainee"));
    this.repository.save(new Employee("Aphisit", "Namracha", "software developer"));
    this.repository.save(new Employee("Micle", "Angle", "Programmer"));
    this.repository.save(new Employee("Rockstar", "Mawto", "ComputerScine"));
}
}