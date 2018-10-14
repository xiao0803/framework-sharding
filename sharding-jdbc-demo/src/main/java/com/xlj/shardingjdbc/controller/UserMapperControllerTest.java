package com.xlj.shardingjdbc.controller;

import com.xlj.shardingjdbc.costants.SqlConstants;
import com.xlj.shardingjdbc.entity.UserEntity;
import com.xlj.shardingjdbc.mapper.UserMapper;
import com.xlj.shardingjdbc.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@RestController
//@RequestMapping("/userMapperControllerTest")
public class UserMapperControllerTest {

	private final Logger logger = LoggerFactory.getLogger(UserMapperControllerTest.class);
	
    @Autowired
    private UserMapper userMapper;

    @Resource
    private DataSource mybatisDataSource;

    @Resource
    private UserService userService;

    private UserEntity user;

    public void setup() throws Exception {
        create();
        clear();
        init();
    }

    private void clear() {
        userMapper.deleteAll();
    }

    private void init() {
        user = new UserEntity();
        user.setUserId(2L);
        user.setCityId(1);
        user.setUserName("insertTest");
        user.setAge(10);
        user.setBirth(new Date());
    }

    private void create() throws SQLException {
        executeUpdate(mybatisDataSource, SqlConstants.USER_CREATE_SQL);
    }

    private void executeUpdate(final DataSource dataSource, final String sql) throws SQLException {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
    }

    private void testUp() throws Exception {
        if (userMapper.insert(user) > 0) {
        	logger.info("新增成功");
		} else {
			logger.info("新增失败");
		}
    }

    private void tearDown() throws Exception {
        if (userMapper.delete(1L) > 0) {
        	logger.info("单条删除成功");
		} else {
			logger.info("单条删除失败");
		}
    }
    
    @RequestMapping("/requestTest")
    public void requestTest() {
    	String aString = "requestTest";
    	String bString = aString + "成功";
    	logger.info(bString);
    }

    @RequestMapping("/getAll")
    public void getAll() throws Exception {
    	setup();
        testUp();
        List<UserEntity> all = userMapper.getAll();
        if (all.size() > 0) {
        	logger.info("all查询成功");
		} else {
			logger.info("all查询失败");
		}
        //tearDown();
    }

    @RequestMapping("/getOneSlave")
    public void getOneSlave() throws Exception {
    	setup();
        UserEntity user = new UserEntity();
        user.setCityId(1);
        user.setUserName("insertTest");
        user.setAge(10);
        user.setBirth(new Date());
        userMapper.insertSlave(user);
        Long userId = user.getUserId();
        logger.info("Generated Key--userId:" + userId + " mod:" + userId % 2);
        UserEntity one = userMapper.getOne(userId);
        logger.info(one.getUserName());
    }

    @RequestMapping("/getOne")
    public void getOne() throws Exception {
    	setup();
        testUp();
        UserEntity one = userMapper.getOne(1L);
        logger.info(one.getUserName());
        tearDown();
    }

    @RequestMapping("/update")
    public void update() throws Exception {
    	setup();
        testUp();
        UserEntity user = new UserEntity();
        user.setUserId(1L);
        user.setUserName("insertTest2");
        UserEntity one = userMapper.getOne(1L);
        logger.info(one.getUserName());
        tearDown();
    }

    @RequestMapping("/updateFailure")
    public void updateFailure() {
        try {
        	setup();
            userService.updateWithFail();
        } catch (Exception e) {
            System.out.println("rollback");
        }
        UserEntity one = userMapper.getOne(userService.getUserId());
        logger.info(one.getUserName());
    }

    @RequestMapping("/updateWithoutInsertFailure")
    public void updateWithoutInsertFailure() {
        try {
        	setup();
            UserEntity user = new UserEntity();
            user.setCityId(1);
            user.setUserName("insertTest");
            user.setAge(10);
            user.setBirth(new Date());
            userMapper.insertSlave(user);
            userService.updateWithoutInsertFail();
        } catch (Exception e) {
			e.printStackTrace();
			logger.info("updateWithoutInsertFailure异常：" + e.getMessage(), e);
        }
        UserEntity one = userMapper.getOne(userService.getUserId());
        logger.info(one.getUserName());
    }

}