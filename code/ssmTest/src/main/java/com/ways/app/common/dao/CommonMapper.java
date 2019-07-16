package com.ways.app.common.dao;

import java.util.List;
import java.util.Map;

public interface CommonMapper {

    public List<Map<String, Object>> getUserList(Map<String,Object> paramsMap); 
}
