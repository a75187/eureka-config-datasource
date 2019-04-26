package com.fxmm.custmoer.fxmmcustmoerserver.dao;

import com.fxmm.custmoer.fxmmcustmoerserver.bean.UserBase;

import java.util.HashMap;
import java.util.List;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/24 17:28
 * @Version: 1.0
 */

public interface CustomerDao {

    UserBase queryUserById(String id);

    void addUser(HashMap<String, Object> map);

    List<UserBase> queryUserList();
}
