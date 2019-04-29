package com.fxmm.custmoer.fxmmcustmoerserver.api;

import com.fxmm.custmoer.fxmmcustmoerserver.bean.UserBase;
import com.fxmm.custmoer.fxmmcustmoerserver.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/24 14:51
 * @Version: 1.0
 */
@RestController
public class CustomerApi {
    @Resource
    private CustomerService customerService;

    @Resource
    private aaa aaa;

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public UserBase queryUserInfo(String id) {
        UserBase userBase = customerService.queryUserById(id);
        return userBase;
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public List<UserBase> queryUserInfo() {
        List<UserBase> list = customerService.queryUserList();
        return list;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public Boolean addUser(String name,Integer age) {
        customerService.addUser(name,age);
        return true;
    }

    public CustomerApi() {
        System.out.println("CustomerApi++你是也是你好不发生+CustomerApi");
    }
}
