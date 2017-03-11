package com.college.service;

import com.college.domain.Users;
import com.college.repository.UsersRepo;
import com.college.util.Type;

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
        Users users = findById(id);
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
        Users users = findById(id);
        return users.getType();
    }

    @Override
    public int register(Type type, String name, String password, String cardid){
        Users users = new Users();
        users.setName(name);
        users.setPassword(password);
        users.setCardid(cardid);
        users.setType(type);
        Users newuser = save(users);
        return newuser.getId();
    }

    @Override
    public void modifyInfo(int id, String newname, String newpassword, String newcardid) {
        Users users = findById(id);
        users.setName(newname);
        users.setPassword(newpassword);
        users.setCardid(newcardid);
        save(users);
    }

    private Users findById(int id){
        return usersRepo.findById(id);
    }

    private Users save(Users users){
        return usersRepo.save(users);
    }

}
