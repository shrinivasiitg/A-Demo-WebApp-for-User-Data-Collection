package com.demo.userdatacollection.controller;

import com.demo.userdatacollection.dao.service.UserService;
import com.demo.userdatacollection.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Controller for user activity
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new-user-signup")
    public String signupFormSubmit(@ModelAttribute User user) {
        log.info(String.format("signing up the user=%s", user));

        com.demo.userdatacollection.dao.entity.User userDao = new com.demo.userdatacollection.dao.entity.User();
        userDao.setFirstName(user.getFirstName());
        userDao.setLastName(user.getLastName());
        userDao.setEmail(user.getEmail());

        com.demo.userdatacollection.dao.entity.User newUser = userService.insertNewUser(userDao);
        log.info(String.format("A new user has been created in DB having userId=%s and details=%s", newUser.getUserId(), newUser));

        user.setUserId("" + newUser.getUserId());
        return "signup-result";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }
}
