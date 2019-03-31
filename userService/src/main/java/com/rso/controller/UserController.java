package com.rso.controller;

import com.rso.util.CookieUtil;
import com.rso.form.LoginForm;
import com.rso.form.UserForm;
import com.rso.model.User;
import com.rso.service.UserService;
import com.rso.util.JwtUtil;
import com.rso.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody @Valid UserForm userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()){
            return ResponseEntity.ok(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(userService.save(userForm));
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid LoginForm loginForm, BindingResult bindingResult, HttpServletResponse httpServletResponse){
        User user = userService.findByUsername(loginForm.getUsername());
        if (user == null || user.getPassword() != loginForm.getPassword()){
            System.out.println("elo");
        }
        String token = JwtUtil.generateToken(signingKey, user.getUsername());
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
        return ResponseEntity.ok(token);
    }

}
