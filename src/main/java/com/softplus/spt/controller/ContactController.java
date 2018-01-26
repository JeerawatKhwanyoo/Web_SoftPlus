package com.softplus.spt.controller;

import com.softplus.spt.service.ContactService;
import flexjson.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.text.resources.FormatData;

import java.io.*;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
@RequestMapping("/contact")
public class ContactController {


    @Autowired
    ContactService contactService;
    private final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);



    @RequestMapping(value = "/saveContact", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8",produces = "text/html; charset=utf-8")
    public ResponseEntity<String> saveContact(MultipartHttpServletRequest multipartRequest){// @RequestBody String json

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        try{
            LOGGER.info("saveContact");
            MultipartFile multipathFile = multipartRequest.getFile("file");
            String username = multipartRequest.getParameter("name");
            String mail = multipartRequest.getParameter("email");
            String tel = multipartRequest.getParameter("tel");
            String message = multipartRequest.getParameter("msg");
            byte[] bytes = multipathFile.getBytes();
            OutputStream os = new FileOutputStream("resume.pdf");
            os.write(bytes);
            LOGGER.info("Write bytes to file.");

            os.close();

            contactService.saveContact(username, mail,tel, message);
            new SendMailController().sendEmail(username,mail,tel, message,"resume.pdf");
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


//        @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//        @ResponseBody
//        public ResponseEntity<?> uploadFile(
//                @RequestParam("uploadfile") MultipartFile uploadfile ) {
//
//            try {
//                LOGGER.info("upload {}",uploadfile);
//                // Get the filename and build the local file path
//                String filename = uploadfile.getOriginalFilename();
//                String directory = env.getProperty("netgloo.paths.uploadedFiles");
//                String filepath = Paths.get(directory, filename).toString();
//
//
//
//                // Save the file locally
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
//                stream.write(uploadfile.getBytes());
//                stream.close();
//            }
//            catch (Exception e) {
//                LOGGER.error("test");
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        } // method uploadFile

}
