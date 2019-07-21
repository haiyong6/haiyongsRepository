package com.ways.app.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ways.app.dao.UserMapper;
import com.ways.app.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	//注入Mapper对象
    @Resource
    private UserMapper userMapper;
	@Override
	public void saveUser(Map<String, Object> paramsMap) {
		userMapper.saveUser(paramsMap);
	}

}
