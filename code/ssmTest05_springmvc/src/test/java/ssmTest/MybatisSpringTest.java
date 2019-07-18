package ssmTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ways.app.common.dao.CommonMapper;
import com.ways.app.common.service.CommonService;

public class MybatisSpringTest {
 
   // @Test
    public void test() {
        //1.加载spring配置
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.获取对象
        CommonMapper commonMapper = (CommonMapper) ac.getBean("commonMapper");
        //3.调用方法
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        List<Map<String, Object>> userList = commonMapper.getUserList(paramsMap);
        for(int i = 0; i < userList.size(); i++) {
            Map<String, Object> userMap = userList.get(i);
            System.out.println("用户名: " + userMap.get("USER_NAME"));
        }
    }
    
    @Test
    public void test1() {
      //1.加载spring配置
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.获取对象
        CommonService commonService = (CommonService) ac.getBean("commonService");
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userName", "zhaohy");
        paramsMap.put("sex", 1);
        paramsMap.put("hobby", "跑步打球运动");
        commonService.saveUser(paramsMap);
    }
}
