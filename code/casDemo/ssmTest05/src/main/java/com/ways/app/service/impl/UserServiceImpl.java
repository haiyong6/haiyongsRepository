package com.ways.app.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	
	/**
	 * 获取用户
	 */
	@Override
	public List<Map<String, Object>> getUserListByName(HttpServletRequest request, Map<String, Object> paramsMap) {
		return userMapper.getUserListByName(paramsMap);
	}

}
