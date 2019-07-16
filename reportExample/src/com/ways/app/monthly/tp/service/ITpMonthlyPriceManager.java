package com.ways.app.monthly.tp.service;

import java.util.Map;

public interface ITpMonthlyPriceManager {

/**查询一汽大众成交价
 * @param paramsMap
 * @return
 */
public String findTpMonthlyPriceInfo(Map<String, Object> paramsMap);
}
