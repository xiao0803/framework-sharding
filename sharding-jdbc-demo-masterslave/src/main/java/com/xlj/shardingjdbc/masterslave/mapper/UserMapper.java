package com.xlj.shardingjdbc.masterslave.mapper;

import com.xlj.shardingjdbc.masterslave.entity.UserEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author xlj
 * @since 0.0.1
 */
@Mapper
public interface UserMapper {

    void createIfNotExistsTable();

    void truncateTable();

    Long insert(UserEntity model);

    UserEntity find(Long userId);

    void delete(Long userId);

    void dropTable();

}
