package com.verizon.dispatch.route.config.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.verizon.dispatch.route.domain.Album;
import com.verizon.dispatch.route.repositories.redis.RedisAlbumRepository;

@Configuration
@Profile("redis")
public class RedisConfig {

    @Bean
    public RedisAlbumRepository redisRepository(RedisTemplate<String, Album> redisTemplate) {
        return new RedisAlbumRepository(redisTemplate);
    }

    @Bean
    public RedisTemplate<String, Album> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Album> template = new RedisTemplate<String, Album>();

        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisSerializer<Album> albumSerializer = new JacksonJsonRedisSerializer<Album>(Album.class);

        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(albumSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(albumSerializer);

        return template;
    }

}
