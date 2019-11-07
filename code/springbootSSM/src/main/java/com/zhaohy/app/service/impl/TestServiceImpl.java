package com.zhaohy.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaohy.app.dao.TestMapper;
import com.zhaohy.app.service.TestService;
@Service("TestService")
public class TestServiceImpl implements TestService {
	@Autowired
    private TestMapper testMapper;
	@Override
	public List<Map<String, Object>> getUser(Map<String, Object> paramsMap) {
		
		return testMapper.getUser(paramsMap);
	}

}
