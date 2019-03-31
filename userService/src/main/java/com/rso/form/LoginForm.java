package com.rso.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class LoginForm {
    @NotEmpty(message = "This field is required")
    @Size(min=5)
    private String username;

    @NotEmpty(message = "This field is required")
    @Size(min=5)
    private String password;

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String passwordConfirmation){
        this.password = passwordConfirmation;
    }

}
