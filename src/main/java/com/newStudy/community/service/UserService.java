package com.newStudy.community.service;

import com.newStudy.community.dao.UserMapper;
import com.newStudy.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shkstart
 * @create 2020-02-20-16:45
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }
}
