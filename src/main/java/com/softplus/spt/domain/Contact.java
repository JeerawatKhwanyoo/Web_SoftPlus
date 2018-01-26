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
    private String tel;
    private String msg;


    private Contact() {}

    public Contact(String name, String email, String tel, String msg) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.msg = msg;

    }
}