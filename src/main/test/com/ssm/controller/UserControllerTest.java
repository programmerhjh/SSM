package com.ssm.controller;

import com.ssm.model.User;
import com.ssm.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by acer on 2017/6/24.
 */
public class UserControllerTest {
    @Resource
    private UserService userService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void showUser() throws Exception {
    }

}