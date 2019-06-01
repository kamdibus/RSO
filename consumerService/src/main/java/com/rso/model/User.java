package com.rso.model;

import com.rso.dto.UserEntityDto;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Document(collection = "users")
@Data
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    private String name;

    private String address;

    private String nipNumber;

    private String phoneNumber;

    private String emailAddress;

    private String accountNumber;

    private String peselNumber;

    private SimpleDateFormat joinDate;

    private UserType userType;

    private UserStatus userStatus;

    public User(String nipNumber) {
        this.nipNumber = nipNumber;
    }

    public User() {}

    public User(UserEntityDto newData) {
        this.name = newData.getName();
        this.address = newData.getAddress();
        this.nipNumber = newData.getNipNumber();
        this.phoneNumber = newData.getPhoneNumber();
        this.emailAddress = newData.getEmailAddress();
        this.accountNumber = newData.getAccountNumber();
        this.peselNumber = newData.getPeselNumber();
        this.joinDate = newData.getJoinDate();
    }
}
