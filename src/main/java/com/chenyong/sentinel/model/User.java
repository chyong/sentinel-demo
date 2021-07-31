package com.chenyong.sentinel.model;


import java.io.Serializable;

/**
 * Created by Song on 2017/2/15.
 * Model 用户
 */

public class User implements Serializable {
    private long id;

    private String name;

    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
