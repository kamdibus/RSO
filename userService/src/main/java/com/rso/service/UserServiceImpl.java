package com.rso.service;

import com.rso.form.UserForm;
import com.rso.model.Role;
import com.rso.model.User;
import com.rso.repository.RoleRepository;
import com.rso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User save(UserForm userForm) {
        User user = new User(
                userForm.getUsername(), userForm.getPassword(), userForm.getEmail()
        );
        user.setRoles((Set<Role>) roleRepository.findById(userForm.getRole()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
