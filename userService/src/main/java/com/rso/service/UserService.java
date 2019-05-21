package com.rso.service;

import com.rso.form.UserForm;
import com.rso.model.User;

import java.util.List;

public interface UserService {
    User save(UserForm userForm);

    User findByUsername(String username);
}

