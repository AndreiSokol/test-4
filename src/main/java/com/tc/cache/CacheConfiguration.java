package com.tc.cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfiguration {

    @Bean()
    public CacheManager<Integer, Object> CacheManager(CacheProperties tcCacheProperties) {
        return new CacheManager<>(tcCacheProperties.getSize());
    }

}