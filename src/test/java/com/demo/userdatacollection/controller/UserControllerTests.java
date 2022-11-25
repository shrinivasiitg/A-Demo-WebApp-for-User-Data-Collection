package com.demo.userdatacollection.controller;

import com.demo.userdatacollection.dao.entity.UserEntity;
import com.demo.userdatacollection.dao.service.UserService;
import com.demo.userdatacollection.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

@SpringBootTest
@Slf4j
public class UserControllerTests {

    @MockBean
    private UserService mockUserService;

    @MockBean
    private Model mockModel;
    @Autowired
    private UserController userController;

    @Test
    void All_get_mapping_are_correct() {
        Assertions.assertEquals("signup", userController.signUpForm());
        Assertions.assertEquals("signin", userController.signInForm());
    }

    @Test
    void New_user_signup_should_not_have_an_existing_email() {
        UserModel userModel = new UserModel();
        userModel.setEmail("existingEmail");

        UserEntity userWithExistingEmail = singleEmptyRowOfUserEntity();
        userWithExistingEmail.setEmail("existingEmail");

        Mockito.when(mockUserService.getUserByEmail("existingEmail")).thenReturn(userWithExistingEmail);

        String actualView = userController.signUpFormSubmit(userModel, mockModel);

        log.info("Verify if the expected view was generated");
        Assertions.assertEquals("signin", actualView);

        log.info("Verify if the error message was generated");
        Mockito.verify(mockModel, Mockito.times(1)).addAttribute(ArgumentMatchers.eq("emailExistsErrorMessage"), Mockito.anyString());
    }

    @Test
    void New_user_signup_with_new_email_should_succeed() {
        UserModel userModel = emptyUserMode();
        userModel.setEmail("aNewEmail");

//        Mockito.when(mockUserService.getUserByEmail("aNewEmail")).thenReturn(singleEmptyRowOfUserEntity());
        Mockito.when(mockUserService.getUserByEmail("aNewEmail")).thenReturn(null);

        UserEntity newUserDao = singleEmptyRowOfUserEntity();
        newUserDao.setEmail("aNewEmail");
        Mockito.when(mockUserService.insertNewUser(Mockito.any())).thenReturn(newUserDao);

        String actualView = userController.signUpFormSubmit(userModel, mockModel);

        log.info("Verify if the expected view was generated");
        Assertions.assertEquals("user-details", actualView);
    }

    @Test
    void Existing_user_sign_in_should_not_happen_with_new_Email() {
        UserModel userModel = emptyUserMode();
        userModel.setEmail("aNewEmail");

        // Mockito.when(mockUserService.getUserByEmail("aNewEmail")).thenReturn(singleEmptyRowOfUserEntity());
        Mockito.when(mockUserService.getUserByEmail("aNewEmail")).thenReturn(null);

        String actualView = userController.signInFormSubmit(userModel, mockModel);

        log.info("Verify if the expected view was generated");
        Assertions.assertEquals("signup", actualView);

        log.info("Verify if the error message was generated");
        Mockito.verify(mockModel, Mockito.times(1)).addAttribute(ArgumentMatchers.eq("emailDoesNotExistErrorMessage"), Mockito.anyString());
    }

    @Test
    void Existing_user_signin_with_correct_existing_email_should_succeed() {
        UserModel userModel = new UserModel();
        userModel.setEmail("existingEmail");

        UserEntity userWithExistingEmail = singleEmptyRowOfUserEntity();
        userWithExistingEmail.setEmail("existingEmail");

        Mockito.when(mockUserService.getUserByEmail("existingEmail")).thenReturn(userWithExistingEmail);

        String actualView = userController.signInFormSubmit(userModel, mockModel);

        log.info("Verify if the expected view was generated");
        Assertions.assertEquals("user-details", actualView);
    }

    private UserModel emptyUserMode() {
        UserModel userModel = new UserModel();
        userModel.setFirstName("");
        userModel.setLastName("");
        userModel.setEmail("");
        userModel.setPassword("");
        userModel.setRepeatPassword("");
        return userModel;
    }

    private UserEntity singleEmptyRowOfUserEntity() {
        return new UserEntity(0, "", "", "", "", "");
    }
}
