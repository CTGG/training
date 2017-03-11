package com.college.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by G on 2017/3/11.
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/card", method = RequestMethod.GET)
    public String showLogin(ModelMap map) {
        return "403";
    }

}
