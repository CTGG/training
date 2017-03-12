package com.college.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by G on 2017/3/13.
 */
public class LogId implements Serializable {
    private int id;
    private Timestamp opetime;

    public LogId() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getOpetime() {
        return opetime;
    }

    public void setOpetime(Timestamp opetime) {
        this.opetime = opetime;
    }
}
