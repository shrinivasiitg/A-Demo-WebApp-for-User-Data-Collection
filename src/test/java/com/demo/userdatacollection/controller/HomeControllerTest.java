package com.demo.userdatacollection.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

@SpringBootTest
@Slf4j
public class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Test
    public void Get_mapping_for_home_page_is_correct() {
        ModelAndView mav = homeController.homePage();
        Assertions.assertEquals("home", mav.getViewName());
    }
}
