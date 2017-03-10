package com.college.web;

import com.college.domain.Users;
import com.college.repository.UsersRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by G on 2017/3/5.
 */
@Controller
public class LoginController {
    @Resource
    private UsersRepo usersRepo;

    @RequestMapping("/login")
    public String index(ModelMap map) {


        // 加入一个属性，用来在模板中读取
        Users users = usersRepo.findById(1000000);
        map.addAttribute("host", users.getName());
        // return模板文件的名称，对应src/main/resources/templates/index.html
//        return "index";
        return "index";
    }


}
