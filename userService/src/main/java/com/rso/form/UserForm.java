package com.rso.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserForm {
    @NotEmpty(message = "This field is required")
    @Size(min=5)
    private String username;

    @NotEmpty(message = "This field is required")
    @Size(min=5)
    private String password;

    @NotEmpty(message = "This field is required")
    @Size(min=5)
    private String passwordConfirmation;

    @NotEmpty(message = "This field is required")
    @Email
    public String email;

    public Long role;

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

    public String getPasswordConfirmation(){
        return this.passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation){
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Long getRole(){
        return this.role;
    }

    public void setRole(Long role){
        this.role = role;
    }
}
