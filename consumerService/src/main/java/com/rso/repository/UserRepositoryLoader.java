package com.rso.repository;

import com.rso.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.userRepository.save(new User("123"));
        this.userRepository.save(new User("1234"));
        this.userRepository.save(new User("12345"));
        this.userRepository.save(new User("123456"));
    }
}