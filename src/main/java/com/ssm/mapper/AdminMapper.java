package com.ssm.mapper;

import com.ssm.model.Admin;
import com.ssm.model.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 管理员dao层
 * @time 2017年8月1日9:01:03
 */
@Repository
public interface AdminMapper extends Mapper<Admin>{

    /**
     * 管理员登陆
     * @param email
     * @param password
     * @return
     * @time 2017年8月1日9:01:33
     */
    Admin checkAdmin(@Param("email") String email,@Param("password") String password);

    /**
     * generator自动生成的方法
     * @param example
     * @return
     * @time 2017年8月1日9:02:04
     */
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}