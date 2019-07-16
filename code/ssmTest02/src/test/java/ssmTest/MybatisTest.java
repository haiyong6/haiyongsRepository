package ssmTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ways.app.common.dao.CommonMapper;

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
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
        
        //操作
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        List<Map<String, Object>> userList = commonMapper.getUserList(paramsMap);
        for(int i = 0; i < userList.size(); i++) {
            Map<String, Object> userMap = userList.get(i);
            System.out.println("用户名: " + userMap.get("USER_NAME"));
        }
        //6.提交事务
        sqlSession.commit();
        
        //7.关闭资源
        sqlSession.close();
        
    }
}
