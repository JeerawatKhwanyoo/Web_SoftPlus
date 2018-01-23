package com.softplus.spt.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Contact {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String email;
    private String msg;


    private Contact() {}

    public Contact(String name, String email, String msg) {
        this.name = name;
        this.email = email;
        this.msg = msg;

    }
}