package com.rso.model;

import com.rso.dto.UserEntityDto;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public User(long id) {
        this.id = id;
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
