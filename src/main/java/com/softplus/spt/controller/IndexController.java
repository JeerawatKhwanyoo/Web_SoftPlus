package com.softplus.spt.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/index")
    public ResponseEntity<String> test(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8");
        return new ResponseEntity<>("TEST PAGE",headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/home",produces = "text/html", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/contact",produces = "text/html", method = RequestMethod.GET)
    public String contact(){
        return "contact";
    }

    @RequestMapping(value = "/peopleDeveloper", produces = "text/html", method = RequestMethod.GET)
    public String peopleDeveloper(){
        return "peopleDeveloper";
    }

    @RequestMapping(value = "/ourcompany", produces = "text/html", method = RequestMethod.GET)
    public String ourCompany(){
        return "ourcompany";
    }

    @RequestMapping(value = "/coOperation", produces = "text/html", method = RequestMethod.GET)
    public String coOperation(){
        return "coOperation";
    }

}
