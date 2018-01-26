package com.softplus.spt.service;

import com.softplus.spt.domain.Contact;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContactService {
    Contact findId(Long id);
    List<Contact> findAll();
    void saveContact(String username,String mail, String tel,String message);

}
