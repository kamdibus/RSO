package com.rso.dto;

import com.rso.model.UserStatus;
import com.rso.model.UserType;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class UserEntityDto {

    private String name;

    private String address;

    private String nipNumber;

    private String phoneNumber;

    private String emailAddress;

    private String accountNumber;

    private String peselNumber;

    private SimpleDateFormat joinDate;

    private String userType;

    private String userStatus;

    public UserEntityDto() {}
}
