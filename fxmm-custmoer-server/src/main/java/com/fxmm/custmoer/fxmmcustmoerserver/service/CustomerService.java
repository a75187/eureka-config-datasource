package com.fxmm.custmoer.fxmmcustmoerserver.service;

import com.fxmm.custmoer.fxmmcustmoerserver.bean.UserBase;
import com.fxmm.custmoer.fxmmcustmoerserver.dao.CustomerDao;
import com.fxmm.custmoer.fxmmcustmoerserver.multidatasocre.UseMasterDb;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/24 17:25
 * @Version: 1.0
 */
@Service
public class CustomerService {
    @Resource
    private CustomerDao customerDao;

    @UseMasterDb
    public UserBase queryUserById(String id) {
        return customerDao.queryUserById(id);
    }


    @Transactional
    public void addUser(String name, Integer age) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        customerDao.addUser(map);
    }

    public List<UserBase> queryUserList() {
        return customerDao.queryUserList();
    }
}
