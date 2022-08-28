package br.com.oluizleme.loja.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@PropertySource(value = "classpath:cache.properties")
@ConfigurationProperties(prefix = "cache")
@Data
public class CacheConfigurationProperties {
    private String port;
    private String host;
    private String defaultTTL;
    private Map<String, String> cachesTTL;

}
