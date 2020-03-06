package com.newStudy.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**配置完后可以通过redisTemplate访问Redis
 * @author shkstart
 * @create 2020-03-02-11:29
 */
@Configuration
public class RedisConfig {
    //通过连接工厂
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        //RedisConnectionFactory已经被容器装配，Spring自动将factory注入进来
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        //将工厂设置给template，至此template就可以通过工厂创建与数据库的连接
        template.setConnectionFactory(factory);

        //指定序列化或者转化的方式java→redis数据库
        //设置key的序列化方式
        template.setKeySerializer(RedisSerializer.string());
        //设置value的序列化方式,json格式的数据是结构化的。
        template.setValueSerializer(RedisSerializer.json());
        //设置hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        //设置hash的value的序列化方式
        template.setHashValueSerializer(RedisSerializer.json());
        //设置完后，触发生效
        template.afterPropertiesSet();
        return template;
    }
}
