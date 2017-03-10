package com.college.domain;

import com.college.util.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by G on 2017/3/10.
 */
@Entity (name = "users")
public class Users implements Serializable{
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String password;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;


    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
