package com.softplus.spt.domain;

import javax.persistence.Column;

public class EmoloyeeId  implements java.io.Serializable{
    private String ouCode;

    public EmoloyeeId() {
    }

    public EmoloyeeId(String ouCode) {
        this.ouCode = ouCode;

    }

    @Column(name = "OU_CODE", nullable = false, length = 3)
    public String getOuCode() {
        return this.ouCode;
    }

    public void setOuCode(String ouCode) {
        this.ouCode = ouCode;
    }



}
