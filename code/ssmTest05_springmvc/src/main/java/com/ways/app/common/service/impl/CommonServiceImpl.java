package com.ways.app.common.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ways.app.common.dao.CommonMapper;
import com.ways.app.common.service.CommonService;
@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
    //注入Mapper对象
    @Resource
    private CommonMapper commonMapper;

    @Override
    public void saveUser(Map<String, Object> paramsMap) {
        
        commonMapper.saveUser(paramsMap);
       // int i = 100/0;
       // commonMapper.saveUser(paramsMap);
    }
    
    
    
}
