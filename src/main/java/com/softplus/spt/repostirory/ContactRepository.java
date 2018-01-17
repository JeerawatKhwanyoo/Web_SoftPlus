package com.softplus.spt.repostirory;

import com.softplus.spt.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaSpecificationExecutor<Contact>, JpaRepository<Contact,Long>,

    PagingAndSortingRepository<Contact,Long>, CrudRepository<Contact,Long> {

//    List<Contact> findByDescriptionContainsAndFirstName(@Param("description") String description,
//                                                        @Param("firstName") String firstName
//    );




}




