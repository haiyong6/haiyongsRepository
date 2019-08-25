package com.zhaohy.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhaohy.app.dao.UserMapper;
import com.zhaohy.app.service.UserService;
import com.zhaohy.app.utils.AppFrameworkUtil;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	//注入Mapper对象
    @Resource
    private UserMapper userMapper;
	@Override
	public String saveUser(Map<String, Object> paramsMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean success = false;
		String info = "注册失败！";
		int rt = 0;
        rt += userMapper.saveUser(paramsMap);
        if(rt > 0) {
        	success = true;
        	info = "注册成功！";
        }
        resultMap.put("success", success);
        resultMap.put("info", info);
		return AppFrameworkUtil.structureConfigParamsGroupJSONData(resultMap);
	}
	/**
	 * 根据用户名获取用户信息
	 */
	@Override
	public List<Map<String, Object>> getUserInfoByUserName(HttpServletRequest request, Map<String, Object> paramsMap) {
		return userMapper.getUserInfoByUserName(paramsMap);
	}

}
