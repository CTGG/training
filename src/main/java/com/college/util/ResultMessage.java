package com.college.util;

/**
 * Created by G on 2017/3/11.
 */
public class ResultMessage {
    private ResultType type;
    private String description;

    public ResultMessage(ResultType type) {
        this.type = type;
    }

    public ResultMessage(ResultType type, String description) {
        this.type = type;
        this.description = description;
    }
}
