package com.softplus.spt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softplus.spt.domain.Contact;
import com.softplus.spt.repostirory.ContactRepository;
import flexjson.JSONDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServicelmp implements ContactService {
    private final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);


    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact findId(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }




    @Override
    public void saveContact(String json) {
        ObjectMapper objectMapper = new ObjectMapper();



        try{
//            Contact contactList = new JSONDeserializer<Contact>()
//                    .use("",Contact.class)
//                    .deserialize(json);
//            LOGGER.info("{}",json);

            Contact contact = objectMapper.readValue(json,Contact.class);
            LOGGER.info("{}",contact.getEmail());


            contactRepository.save(contact);


        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }



}

