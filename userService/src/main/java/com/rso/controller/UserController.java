package com.rso.controller;

import com.rso.form.LoginForm;
import com.rso.form.UserForm;
import com.rso.model.User;
import com.rso.service.UserService;
import com.rso.util.CookieUtil;
import com.rso.util.JwtUtil;
import com.rso.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
        if (user == null || !user.getPassword().equals(loginForm.getPassword())){
            return ResponseEntity.ok("Wrong username or password");
        }
        String token = JwtUtil.generateToken(signingKey, user.getUsername());
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
        return ResponseEntity.ok(token);
    }

}
