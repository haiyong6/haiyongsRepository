package com.zhaohy.app.dao;

import java.util.List;
import java.util.Map;

public interface TestMapper {

	List<Map<String, Object>> getUser(Map<String, Object> paramsMap);

}
