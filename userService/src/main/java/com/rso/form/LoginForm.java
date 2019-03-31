package com.rso.form;

import javax.validation.constraints.Size;

public class LoginForm {
    @Size(min=5)
    private String username;

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
