package com.rso.model;

import com.rso.dto.UserEntityDto;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Document(collection = "users")
@Data
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    private String name;

    private String address;

    private String nip;

    private String phone;

    private String emailAddress;

    private String accountNumber;

    private String peselNumber;

    private SimpleDateFormat joinDate;

    private UserType type;

    private UserStatus userStatus;

    public User(String nipNumber) {
        this.nip = nipNumber;
    }

    public User(long id) {
        this.id = id;
    }

    public User() {}

    public User(UserEntityDto newData) {
        this.name = newData.getName();
        this.address = newData.getAddress();
        this.nip = newData.getNipNumber();
        this.phone = newData.getPhoneNumber();
        this.emailAddress = newData.getEmailAddress();
        this.accountNumber = newData.getAccountNumber();
        this.peselNumber = newData.getPeselNumber();
        this.joinDate = newData.getJoinDate();
    }
}
