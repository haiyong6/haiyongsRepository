package ssmTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ways.app.dao.UserMapper;

public class MybatisTest {
    @Test
    public void test() throws IOException {
        //1.创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 加载sqlMapConfig.xml文件
        InputStream ins = Resources.getResourceAsStream("sqlMapConfig.xml");
        
        
        //2.创建SqlSessionFactory
        SqlSessionFactory factory = builder.build(ins);
        
        //3.打开SqlSession
        SqlSession sqlSession = factory.openSession();
        
        //4.获取mapper接口的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        
        //操作
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userName", "zhaohy");
        paramsMap.put("sex", 1);
        paramsMap.put("job", "java软件工程师");
        paramsMap.put("tel", "189xxxx0598");
        paramsMap.put("email", "1025XXXX40@qq.com");
        paramsMap.put("hobby", "编程，运动");
        userMapper.saveUser(paramsMap);
        //6.提交事务
        sqlSession.commit();
        
        //7.关闭资源
        sqlSession.close();
    }
}
