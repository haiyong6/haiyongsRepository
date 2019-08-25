package com.zhaohy.app.dao;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    public int saveUser(Map<String,Object> paramsMap);

    /**
     * 根据用户名获取用户信息
     * @param paramsMap
     * @return
     */
	public List<Map<String, Object>> getUserInfoByUserName(Map<String, Object> paramsMap); 
}
