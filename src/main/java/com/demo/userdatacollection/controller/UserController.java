package com.demo.userdatacollection.controller;

import com.demo.userdatacollection.dao.entity.UserEntity;
import com.demo.userdatacollection.dao.service.UserService;
import com.demo.userdatacollection.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;


/**
 * Controller for user activity
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signUpForm() {
        return "signup";
    }

    @PostMapping("/new-user-signup")
    public String signUpFormSubmit(@ModelAttribute UserModel userModel, Model model) {
        if (StringUtils.isEmpty(userModel.getEmail())) {
            String emailCantBeEmptyErrorMessage = String.format(
                    "Please use a non-empty email to SignUp as a new user",
                    userModel.getEmail());
            log.info(emailCantBeEmptyErrorMessage);
            model.addAttribute("emailCantBeEmptyErrorMessage", emailCantBeEmptyErrorMessage);
            return "signup";
        }

        log.info(String.format("signing up the user=%s", userModel));

        if (doesEmailExist(userService.getUserByEmail(userModel.getEmail()))) {
            String emailExistsErrorMessage = String.format(
                    "The email=%s exists in the database, so can not sign-up as new user. " +
                            "Please use this email to signin as an existing user",
                    userModel.getEmail());
            log.info(emailExistsErrorMessage);
            model.addAttribute("emailExistsErrorMessage", emailExistsErrorMessage);
            return "signin";
        }

        UserEntity userDao = new UserEntity();
        userDao.setFirstName(userModel.getFirstName());
        userDao.setLastName(userModel.getLastName());
        userDao.setEmail(userModel.getEmail());

        log.info(String.format("Inserting a new user=%s", userDao));
        UserEntity newUser = userService.insertNewUser(userDao);
        log.info(String.format("A new user has been created in DB having userId=%s and details=%s", newUser.getUserId(), newUser));

        // TODO
        // Can userId be a useful field?
        // userModel.setUserId("" + newUser.getUserId());
        return "user-details";
    }

    @GetMapping("/signin")
    public String signInForm() {
        return "signin";
    }

    @PostMapping("/existing-user-signin")
    public String signInFormSubmit(@ModelAttribute UserModel userModel, Model model) {
        log.info(String.format("signing in the existing user=%s", userModel));

        UserEntity existingUserInDB = userService.getUserByEmail(userModel.getEmail());
        if (doesEmailExist(existingUserInDB) == false) {
            String emailDoesNotExistErrorMessage = String.format(
                    "The email=%s does not exist in the database. So, please use this email to SignUp as a new user",
                    userModel.getEmail());
            log.info(emailDoesNotExistErrorMessage);
            model.addAttribute("emailDoesNotExistErrorMessage", emailDoesNotExistErrorMessage);
            return "signup";
        }

        // TODO
        // need to check if password matches in future version
        doesPasswordMatch(existingUserInDB);

        userModel.setFirstName(existingUserInDB.getFirstName());
        userModel.setLastName(existingUserInDB.getLastName());
        return "user-details";
    }

    /**
     * Checks if the email in the Model object is present in the DB
     *
     * @param user
     * @return
     */
    private boolean doesEmailExist(UserEntity user) {
        // return StringUtils.isNotEmpty(user.getEmail());
        return Objects.nonNull(user);

    }

    /**
     * Needs to be implemented.
     *
     * @param user
     * @return
     */
    private boolean doesPasswordMatch(UserEntity user) {
        return true;
    }

}
