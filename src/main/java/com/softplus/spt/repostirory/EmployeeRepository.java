package com.softplus.spt.repostirory;

import com.softplus.spt.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaSpecificationExecutor<Employee>, JpaRepository<Employee,Long>,

    PagingAndSortingRepository<Employee,Long>, CrudRepository<Employee,Long> {

    List<Employee> findByDescriptionContainsAndFirstName(@Param("description") String description,
                                                         @Param("firstName") String firstName
    );

}




