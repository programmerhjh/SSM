package com.ssm.service;

import com.ssm.model.Admin;

/**
 * Created by acer on 2017/7/31.
 */
public interface AdminService {
    Admin checkAdmin(String email, String password);
}
