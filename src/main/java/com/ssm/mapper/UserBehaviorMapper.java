package com.ssm.mapper;

import com.ssm.model.UserBehavior;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户行为dao层
 * Created by acer on 2017/7/10.
 */

@Repository
public interface UserBehaviorMapper extends Mapper<UserBehavior> {
}
