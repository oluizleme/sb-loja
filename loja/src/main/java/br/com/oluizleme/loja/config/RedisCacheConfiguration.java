package br.com.oluizleme.loja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableCaching
public class RedisCacheConfiguration extends CachingConfigurerSupport {
    @Value("${cache.prefix}")
    private String prefix;
    @Autowired
    private CacheConfigurationProperties cacheConfigurationProperties = null;

    private org.springframework.data.redis.cache.RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
        return org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(timeoutInSeconds))
                .computePrefixWith(cacheName -> prefix + ":" + cacheName + ":");
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory) {
        Map<String, org.springframework.data.redis.cache.RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        if (Objects.nonNull(cacheConfigurationProperties.getCachesTTL())) {
            for (Map.Entry<String, String> cacheNameAndTimeout : cacheConfigurationProperties.getCachesTTL().entrySet()) {
                cacheConfigurations.put(cacheNameAndTimeout.getKey(),
                        createCacheConfiguration(Long.parseLong(cacheNameAndTimeout.getValue())));
            }
        }

        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(createCacheConfiguration(Long.parseLong(cacheConfigurationProperties.getDefaultTTL())))
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(cacheConfigurationProperties.getHost());
        redisStandaloneConfiguration.setPort(Integer.parseInt(cacheConfigurationProperties.getPort()));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
}
