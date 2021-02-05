package com.melvin;

import com.melvin.mapper.UserMapper;
import com.melvin.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("李四");
        user.setAge(10);
        user.setEmail("132sdad56@test.com");
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId(1356956575250403332L);
        user.setName("Andy");
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    //测试乐观锁
    @Test
    public void optimisticLocker(){
        User user = userMapper.selectById(1L);
        user.setName("阿拉");
        user.setEmail("45611@qq.com");
        userMapper.updateById(user);
    }

    //模拟多线程测试乐观锁
    @Test
    public void optimisticLocker2(){
        //线程1
        User user = userMapper.selectById(1L);
        user.setName("阿拉1111");
        user.setEmail("45611@qq.com");
        //线程2
        User user2 = userMapper.selectById(1L);
        user2.setName("阿拉22222");
        user2.setEmail("45611@qq.com");
        userMapper.updateById(user2);


        userMapper.updateById(user);
    }

}
