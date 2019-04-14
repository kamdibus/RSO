package com.rso.validator;

import com.rso.form.UserForm;
import com.rso.model.User;
import com.rso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm userForm = (UserForm) o;

        if (userService.findByUsername(userForm.getUsername()) != null) {
            errors.rejectValue("username", "Someone already has that username");
        }

        if (!userForm.getPasswordConfirmation().equals(userForm.getPassword())) {
            errors.rejectValue("passwordConfirmation", "These passwords don't match.");
        }
    }
}
