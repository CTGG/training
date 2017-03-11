package com.college.web;

import com.college.repository.UsersRepo;
import com.college.service.LoginService;

import com.college.util.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by G on 2017/3/5.
 */
@Controller
public class LoginController {
    @Resource
    private UsersRepo usersRepo;

    @Resource
    private LoginService loginService;

    @GetMapping("/")
    public String loginForm(Model model){
        model.addAttribute("login", new LoginMessage());
        return "login/index";
    }

//
//
//    @RequestMapping(value = "/")
//    public String showLogin() {
//        return "login/index";
//    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginCheck(@ModelAttribute LoginMessage loginMessage, ModelMap map) {
        //TODO test getid&getpassword
        int id = loginMessage.getId();
        String password = loginMessage.getPassword();
        boolean valid = loginService.isValid(id, password);
        if (valid){
            Type type = loginService.getUserType(id);
            switch (type){
                case MEMBER:
                    return "home/member";
                case MANAGER:
                    return "home/manager";
                case COLLEGE:
                    return "home/college";
                default:
                    return "error";
            }
        }
        return "error";
    }

}
