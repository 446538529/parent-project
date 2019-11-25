package com.test;

import com.redis.demo.config.RedisConfig;
import com.redis.demo.config.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RedisConfig.class})
public class TestRedis {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 字符串操作
     */
    @Test
    public void string(){
        //1.常用get set
        redisTemplate.opsForValue().set("ename","young");
        Object ename = redisTemplate.opsForValue().get("ename");
        System.out.println(ename);
        //2.incr ,可以用来维护用户在某个抽奖活动的剩余抽奖次数,每次递增指定数字
        redisTemplate.opsForValue().increment("leftCount",1);
        //3.setnx可以用来实现分布式锁
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lockKey", "lock");
        System.out.println(lock);
    }

    /**
     * list操作,可以实现简单队列,使用lpush和rpop
  */
    @Test
    public void list(){
        redisTemplate.opsForList().leftPush("user-list",new User("1","YT").toString());
        redisTemplate.opsForList().leftPush("user-list",new User("2","LSJ").toString());
        List range = redisTemplate.opsForList().range("user-list", 0, -1);
        System.out.println(range);
    }

    /**
     * set操作 无序不可重复集合
     * 可以用来存储每个标签对应的文章id
     * 也可以用来存储每个文章的已投票用户id，通过add返回值可以判断该值之前是否已经存在
     */
    @Test
    public void set(){
        List<User> list1= Arrays.asList(new User("1","张三"),new User("2","李四"));
        redisTemplate.opsForSet().add("set1",list1.toArray());
        Set<User> set1 = redisTemplate.opsForSet().members("set1");
        System.out.println(set1);
    }

}
