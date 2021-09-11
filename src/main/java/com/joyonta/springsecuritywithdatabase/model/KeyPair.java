package com.joyonta.springsecuritywithdatabase.model;

import lombok.Data;

@Data
public class KeyPair {
    private String apiurl;
    private String methodname;

    public KeyPair(String apiurl, String methodname) {
        this.apiurl = apiurl;
        this.methodname = methodname;
    }
}