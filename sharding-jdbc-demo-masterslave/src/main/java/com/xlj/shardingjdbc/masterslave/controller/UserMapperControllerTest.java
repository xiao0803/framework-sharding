package com.xlj.shardingjdbc.masterslave.controller;

import com.xlj.shardingjdbc.masterslave.entity.UserEntity;
import com.xlj.shardingjdbc.masterslave.mapper.UserMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import java.sql.SQLException;
import java.util.Date;

/**
 * @author xlj
 * @since 0.0.1
 */
//@RestController
//@RequestMapping("/userMapperControllerTest")
public class UserMapperControllerTest {

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(UserMapperControllerTest.class);

    @Resource
    private UserMapper userMapper;

    public void setup() throws Exception {
        create();
        clear();
    }

    private void create() throws SQLException {
        userMapper.createIfNotExistsTable();
    }

    private void clear() {
        userMapper.truncateTable();
    }


    @RequestMapping("/insert")
    public void insert() throws Exception {
    	setup();
        UserEntity user = new UserEntity();
        user.setCityId(1);
        user.setUserName("insertTest");
        user.setAge(10);
        user.setBirth(new Date());
        userMapper.insert(user);
        Long userId = user.getUserId();
        log.info("Generated Key--userId:" + userId);
        userMapper.delete(userId);
    }

    /*
    INSERT INTO t_user_0(user_id,city_id,user_name,age,birth) values(138734796783222784,1,'insertTest',10,'2017-11-18 00:00:00');
     */
    @RequestMapping("/find")
    public void find() throws Exception {
    	setup();
        UserEntity userEntity = userMapper.find(138734796783222784L);
        log.info("user:{}", userEntity);
    }

}
