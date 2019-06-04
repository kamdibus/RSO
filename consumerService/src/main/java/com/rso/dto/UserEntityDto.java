package com.rso.dto;

import com.rso.model.UserStatus;
import com.rso.model.UserType;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class UserEntityDto {

    private String name;

    private String address;

    private String nip;

    private String phone;

    private String emailAddress;

    private String accountNumber;

    private String peselNumber;

    private SimpleDateFormat joinDate;

    private String type;

    private String userStatus;

    public UserEntityDto() {}

}
