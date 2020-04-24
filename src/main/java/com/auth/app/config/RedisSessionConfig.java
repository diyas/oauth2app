package com.auth.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;


@Configuration
@PropertySource("classpath:application.yml")
@EnableRedisHttpSession
public class RedisSessionConfig {

    @Autowired
    private Environment environment;

    private JedisPoolConfig jedisPoolConfig() {
        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.parseInt(environment.getRequiredProperty("spring.redis.max-total")));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(environment.getRequiredProperty("spring.redis.max-idle")));
        jedisPoolConfig.setMinIdle(Integer.parseInt(environment.getRequiredProperty("spring.redis.min-idle")));
        jedisPoolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(Integer.parseInt(environment.getRequiredProperty("spring.redis.min-evictable-idle-time-millis"))).toMillis());
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(Integer.parseInt(environment.getRequiredProperty("spring.redis.time-between-eviction-runs-millis"))).toMillis());
        jedisPoolConfig.setBlockWhenExhausted(Boolean.valueOf(environment.getRequiredProperty("spring.redis.block-when-exhausted")));
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        jedisConnectionFactory.setPort(Integer.parseInt(environment.getRequiredProperty("spring.redis.port")));
        jedisConnectionFactory.setHostName(environment.getRequiredProperty("spring.redis.host"));
        jedisConnectionFactory.setUsePool(Boolean.valueOf(environment.getRequiredProperty("spring.redis.use-pool")));
        return jedisConnectionFactory;
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return redisTemplate;
    }

}
