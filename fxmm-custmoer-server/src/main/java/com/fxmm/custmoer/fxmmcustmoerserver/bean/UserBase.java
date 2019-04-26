package com.fxmm.custmoer.fxmmcustmoerserver.bean;

import java.io.Serializable;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/24 17:24
 * @Version: 1.0
 */

public class UserBase implements Serializable {
    private String id;
    private String userName;
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
