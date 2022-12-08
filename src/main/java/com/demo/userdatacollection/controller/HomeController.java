package com.demo.userdatacollection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for the home/landing page
 */
@Controller
public class HomeController {

    @GetMapping(value={"", "/", "home", "/home"})
    public ModelAndView homePage() {
        Map<String, String> model = new HashMap<>();
        model.put("firstName", "Shrinivas");
        model.put("lastName", "Acharya");
        model.put("email", "shrinivas@gmail.com");
        model.put("country", "Canada");

        ModelAndView modelAndView = new ModelAndView("home", model);

        return modelAndView;
    }
}
