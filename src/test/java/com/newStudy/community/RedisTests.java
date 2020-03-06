package com.newStudy.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shkstart
 * @create 2020-03-02-11:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    //对String类型的访问
    @Test
    public void testStrings(){
        String redisKey = "test:count";
        redisTemplate.opsForValue().set(redisKey,1);
        System.out.println(redisTemplate.opsForValue().get(redisKey));
        //对值的增加和减少
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));
    }

    //对hash
    @Test
    public void testHashes(){
        String redisKey = "test:user";

        redisTemplate.opsForHash().put(redisKey,"id",1);
        redisTemplate.opsForHash().get(redisKey,"id");
    }

    //编程式事务
    @Test
    public void testTransaction(){
        Object obj = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                String redisKey="test:tx";
                //启用事务
                redisOperations.multi();
                redisOperations.opsForSet().add(redisKey,"zhangsan");
                //事务没被提交前会暂存在消息队列中不会执行，等到提交事务后才会执行操作，所以查询不到数据。
                //查询无效
                System.out.println(redisOperations.opsForSet().members(redisKey));

                //提交事务
                return redisOperations.exec();
            }
        });
        System.out.println(obj);
    }
}
