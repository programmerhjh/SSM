package com.ssm.dao;

import com.ssm.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by acer on 2017/6/24.
 */
@Repository
public interface UserMapper {
    User selectUserById(int id);
}
