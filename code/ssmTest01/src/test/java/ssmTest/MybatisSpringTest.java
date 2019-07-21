package ssmTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ways.app.dao.UserMapper;

public class MybatisSpringTest {
 
    @SuppressWarnings("resource")
	@Test
    public void test() {
        //1.加载spring配置
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.获取对象
        UserMapper commonMapper = (UserMapper) ac.getBean("userMapper");
        //3.调用方法
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userName", "zhaohy1");
        paramsMap.put("sex", 1);
        paramsMap.put("job", "java软件工程师");
        paramsMap.put("tel", "189xxxx0598");
        paramsMap.put("email", "1025XXXX40@qq.com");
        paramsMap.put("hobby", "编程，运动");
        commonMapper.saveUser(paramsMap);
    }
}
