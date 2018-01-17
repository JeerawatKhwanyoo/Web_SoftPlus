package com.softplus.spt.controller;

import com.softplus.spt.service.ContactService;
import flexjson.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/contact")
public class ContactController {
   @Autowired
   ContactService contactService;
   private final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);



    @RequestMapping(value = "/saveContact", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> saveBusinessSubType(@RequestBody String json)
    {
        LOGGER.info("{}",json);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try{
            LOGGER.info("saveContact");
           contactService.saveContact(json);
            return new ResponseEntity<String>(new JSONSerializer().deepSerialize("true"), headers, HttpStatus.OK);

        }catch(Exception e){
            LOGGER.error("saveContact error msg : {}",e);
            return new ResponseEntity<String>("{\"ERROR\":" +e.getMessage()+ "\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




   @RequestMapping(value = "/findById", headers = "Accept=aplication/json", produces = "text/html;charset=utf-8")
    public @ResponseBody
    ResponseEntity<String> findById(@RequestParam(value = "id",required = true)Long id
   ){
       HttpHeaders headers = new HttpHeaders();
       headers.add("Content-Type", "application/json; charset=utf-8");
       try{
           return new ResponseEntity<String>(new JSONSerializer()
                   .exclude("*.class")
                   .prettyPrint(true)
                   .deepSerialize(contactService.findId(id)), headers, HttpStatus.OK);
       }catch(Exception e){
        e.printStackTrace();
        throw new RuntimeException(e.getMessage());
       }


   }

   @RequestMapping(value = "/findAll" , headers = "Accept=application/json", produces = "text/html;charset=utf-8")
    public ResponseEntity<String> findAll(){
       HttpHeaders headers = new HttpHeaders();
       headers.add("Content-Type","application/json; charset=utf-8");
       try{
           return new ResponseEntity<String>(new JSONSerializer()
                   .exclude("*.class")
                   .prettyPrint(true)
                   .deepSerialize(contactService.findAll()),headers, HttpStatus.OK);
       }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
       }

   }









}
