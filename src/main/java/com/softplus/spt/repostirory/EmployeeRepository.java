package com.softplus.spt.repostirory;

import com.softplus.spt.domain.EmoloyeeId;
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
public interface EmployeeRepository extends JpaSpecificationExecutor<Employee>, JpaRepository<Employee,EmoloyeeId>,
        PagingAndSortingRepository<Employee,EmoloyeeId>, CrudRepository<Employee,EmoloyeeId> {



    @Query("select e from Employee  e where e.id.ouCode =:ouCode ")
    List<Employee>findById_ouCode(
            @Param("ouCode") String ouCode
    );
}
