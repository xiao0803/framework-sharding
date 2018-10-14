package com.xlj.shardingjdbc.service;

import com.xlj.shardingjdbc.entity.UserEntity;
import com.xlj.shardingjdbc.mapper.UserMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Date;

/**
 * @author xlj
 * @since 0.0.1
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    private Long userId;

    @Transactional("mybatisTransactionManager")
    public void updateWithFail() {
        UserEntity user = new UserEntity();
        user.setCityId(1);
        user.setUserName("insertTest");
        user.setAge(10);
        user.setBirth(new Date());

        userMapper.insertSlave(user);

        userId = user.getUserId();

        user.setUserName("insertTest2");
        userMapper.update(user);
        throw new IllegalArgumentException("failed");
    }

    @Transactional("mybatisTransactionManager")
    public void updateWithoutInsertFail() {
        UserEntity one = userMapper.getLimitOne();
        UserEntity user = new UserEntity();
        user.setUserName("insertTest2");
        user.setUserId(one.getUserId());
        userId = one.getUserId();
        userMapper.update(user);
        throw new IllegalArgumentException("failed");
    }

    public Long getUserId() {
        return userId;
    }

}
