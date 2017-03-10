package com.college.service;

/**
 * Created by G on 2017/3/10.
 */
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
     *
     * @param name
     * @param password
     * @return int      
     */
    public int registerMember(String name, String password);

    public int registerCollege();
}
