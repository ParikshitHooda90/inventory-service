package org.ps.ecp.ecom.inventory.config;

import org.ps.ecp.ecom.inventory.pojo.InventoryCache;
import org.ps.ecp.ecom.inventory.pojo.SessionInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfig {

    //Creating Connection with Redis
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    //Creating RedisTemplate for Entity 'Employee'
    @Bean
    public RedisTemplate<String, InventoryCache> redisTemplate(){
        RedisTemplate<String, InventoryCache> template = new RedisTemplate<>();
        template.setEnableDefaultSerializer(true);
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

}
