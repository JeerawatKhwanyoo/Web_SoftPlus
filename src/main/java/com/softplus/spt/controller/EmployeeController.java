package com.softplus.spt.controller;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.softplus.spt.service.EmployeeService;
import flexjson.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
   @Autowired
    EmployeeService employeeService;
   private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);


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
                   .deepSerialize(employeeService.findId(id)), headers, HttpStatus.OK);
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
                   .deepSerialize(employeeService.findAll()),headers, HttpStatus.OK);
       }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
       }

   }

}
