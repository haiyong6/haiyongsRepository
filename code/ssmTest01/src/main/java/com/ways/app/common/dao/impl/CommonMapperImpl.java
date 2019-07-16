package com.ways.app.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ways.app.common.dao.CommonMapper;

public class CommonMapperImpl extends SqlSessionDaoSupport implements CommonMapper {

    @Override
    public List<Map<String, Object>> getUserList(Map<String, Object> paramsMap) {
        return this.getSqlSession().selectList("com.ways.app.common.dao.CommonMapper.getUserList", paramsMap);
        //不需要事务提交 mybatis帮忙提交了
    }

}
