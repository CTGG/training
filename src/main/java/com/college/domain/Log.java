package com.college.domain;

import com.college.util.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by G on 2017/3/13.
 */
@Entity(name = "log")
@IdClass(LogId.class)
public class Log implements Serializable {

    @Id
    @Column(name = "id")
    private int id;
    private String operation;
    @Id
    @Column(name = "opetime")
    private Timestamp opetime;
    private Type type;

    public Log() {
    }

    public Log(int id, String operation, Type type) {
        this.id = id;
        this.operation = operation;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Timestamp getOpetime() {
        return opetime;
    }

    public void setOpetime(Timestamp opetime) {
        this.opetime = opetime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}


