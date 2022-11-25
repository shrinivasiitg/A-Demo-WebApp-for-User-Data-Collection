package com.demo.userdatacollection.model;

import lombok.Data;

@Data
public class User {

    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
}
