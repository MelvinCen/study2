package com.melvin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melvin.mapper.UserMapper;
import com.melvin.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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

    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectByBatchId(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        users.forEach(System.out::println);

    }

    //条件查询 使用map
    @Test
    public void testSelectByBatchIds(){
        Map<String, Object> params = new HashMap<>();
        params.put("name","狂神");
        List<User> users = userMapper.selectByMap(params);
        users.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage(){
        Page<User> page = new Page<>(2,5);

        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
    }
    //根据id删除
    @Test
    public void testDeleteUserById(){

        int result = userMapper.deleteById(1356956575250403330L);
        if (result > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }

    }

}
