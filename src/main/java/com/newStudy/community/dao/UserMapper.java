package com.newStudy.community.dao;

import com.newStudy.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shkstart
 * @create 2020-02-17-10:47
 */
@Mapper
public interface UserMapper {
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);//返回插入的条数 //非Java定义的类型的形参，在配置文件中需要说明

    int updateStatus(int id, int status);//返回修改的条数

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);
}
