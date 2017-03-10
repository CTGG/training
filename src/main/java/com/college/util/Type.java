package com.college.util;

/**
 * Created by G on 2017/3/10.
 */
public enum Type {
    MEMBER,COLLEGE,MANAGER;

    public String toString(Type type){
        switch (type){
            case MEMBER:
                return "会员";
            case COLLEGE:
                return "机构";
            case MANAGER:
                return "总经理";
            default:
                return "错误用户类型";
        }
    }
}
