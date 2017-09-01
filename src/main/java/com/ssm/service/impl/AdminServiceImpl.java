package com.ssm.service.impl;

import com.ssm.mapper.AdminMapper;
import com.ssm.model.Admin;
import com.ssm.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 管理员业务层实现
 * Created by acer on 2017/7/31.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    public Admin checkAdmin(String email, String password) {
        return adminMapper.checkAdmin(email,password);
    }
}
