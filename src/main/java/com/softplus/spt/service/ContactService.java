package com.softplus.spt.service;

import com.softplus.spt.domain.Contact;

import java.util.List;

public interface ContactService {
    Contact findId(Long id);
    List<Contact> findAll();
    void saveContact(String json);

}
