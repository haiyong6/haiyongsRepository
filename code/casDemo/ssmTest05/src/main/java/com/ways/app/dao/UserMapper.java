package com.ways.app.dao;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    public void saveUser(Map<String,Object> paramsMap);

    /**
     * 获取用户
     * @param paramsMap
     * @return
     */
	public List<Map<String, Object>> getUserListByName(Map<String, Object> paramsMap); 
}
