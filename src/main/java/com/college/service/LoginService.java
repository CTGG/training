package com.college.service;

import com.college.util.Type;
import org.springframework.stereotype.Component;

/**
 * Created by G on 2017/3/10.
 */

@Component
public interface LoginService {
    /**
     *
     * check whether the id is valid & password is correct
     *
     * @param id
     * @param password
     * @return boolean
     */

    public boolean isValid(int id, String password);


    /**
     * type of user
     * @param id
     * @return
     */
    public Type getUserType(int id);


    /**
     *Addition: generate a id for this member
     *
     * @param name
     * @param password
     * @param cardid  id of bank card
     * @return int     return the id of the user
     */
    public int register(Type type, String name, String password, String cardid);


}
