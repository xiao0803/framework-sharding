package com.xlj.shardingjdbc.mapper;

import com.xlj.shardingjdbc.costants.SqlConstants;
import com.xlj.shardingjdbc.entity.UserEntity;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户DAO
 *
 * @author xlj
 * @since 0.0.1
 */
public interface UserMapper {

    @Select(SqlConstants.GET_ALL_SQL)
    @Results(id = "userResultMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "cityId", column = "city_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "age", column = "age"),
            @Result(property = "birth", column = "birth")
    })
    List<UserEntity> getAll();

    @Select(SqlConstants.GET_ONE_SQL)
    @ResultMap(value = "userResultMap")
    UserEntity getOne(Long userId);

    @Select(SqlConstants.GET_LIMIT_ONE_SQL)
    @ResultMap(value = "userResultMap")
    UserEntity getLimitOne();

    @Insert(SqlConstants.INSERT_SQL)
    int insert(UserEntity user);

    @Insert(SqlConstants.INSERT_SLAVE_SQL)
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertSlave(UserEntity user);

    @Update(SqlConstants.UPDATE_SQL)
    int update(UserEntity user);

    @Delete(SqlConstants.DELETE_SQL)
    int delete(Long userId);

    @Delete(SqlConstants.DELETE_ALL_SQL)
    void deleteAll();

}
