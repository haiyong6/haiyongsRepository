package com.ways.app.dao.impl;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ways.app.dao.UserMapper;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

	@Override
	public void saveUser(Map<String, Object> paramsMap) {
        this.getSqlSession().insert("com.ways.app.dao.UserMapper.saveUser", paramsMap);
        //不需要事务提交 mybatis帮忙提交了
    }

}
