package com.college.domain;

import com.college.util.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by G on 2017/3/10.
 */
@Entity(name = "log")
public class Log implements Serializable{
    @Id
    private int id;
    @Id
    private Timestamp opetime;

    private String operation;

    private Type type;

    public Log() {
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
