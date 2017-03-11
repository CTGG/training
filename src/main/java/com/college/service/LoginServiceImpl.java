package com.college.service;

import com.college.domain.Users;
import com.college.repository.UsersRepo;
import com.college.util.Type;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * Created by G on 2017/3/10.
 */
public class LoginServiceImpl implements LoginService{
    @Resource
    UsersRepo usersRepo;

    //tested
    @Override
    public boolean isValid(int id, String password) {
        Users users = usersRepo.findById(id);
        if (users == null){
            return false;
        }else {
            if (users.getPassword().equals(password))
            return true;
            return false;
        }

    }

    @Override
    public Type getUserType(int id) {
        Users users = usersRepo.findById(id);
        return users.getType();
    }

    @Override
    public int register(Type type, String name, String password, String cardid){
        Users users = new Users();
        users.setName(name);
        users.setPassword(password);
        users.setCardid(cardid);
        users.setType(type);
        Users newuser = usersRepo.save(users);
        return newuser.getId();
    }




}
