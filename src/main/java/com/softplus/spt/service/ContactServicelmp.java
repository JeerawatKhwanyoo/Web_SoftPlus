package com.softplus.spt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softplus.spt.domain.Contact;
import com.softplus.spt.repostirory.ContactRepository;
import flexjson.JSONDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
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
    public void saveContact(String username,String mail,String tel, String msg) {



        try{
//


            contactRepository.save(new Contact(username,mail,tel,msg));
//            LOGGER.debug("saveSuccess {}",contact.getId());
           //upload file

//            String filename = uploadfile.getOriginalFilename();
//            String directory = env.getProperty("netgloo.paths.uploadedFiles");
//            String filepath = Paths.get(directory, filename).toString();
//
//            BufferedOutputStream stream =
//                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
//            stream.write(uploadfile.getBytes());
//            stream.close();
//            LOGGER.debug("uploadSuccess {}",filepath);


            //sendmail


        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }



}

