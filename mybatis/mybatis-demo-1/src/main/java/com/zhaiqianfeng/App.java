package com.zhaiqianfeng;

import com.zhaiqianfeng.user.User;
import com.zhaiqianfeng.user.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        String config= "mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(config);
        //SqlSessionFactoryBuilder加载核心(+mapper)配置文件并创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);

        //创建SqlSession
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //获取mapper
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        User user=new User();
        user.setName("chris");

        //执行操作
        mapper.insertUser(user);
        sqlSession.commit(); //flush
        System.out.println("获取自增id:"+user.getId());

        user=mapper.getUserById(user.getId());
        System.out.println("刚刚入库的user对象"+user);

    }
}
