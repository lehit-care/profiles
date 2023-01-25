package com.lehit.profiles.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@ConditionalOnProperty(prefix = "spring", name = "cache.type", havingValue = "redis")
@Configuration
@EnableCaching
public class CacheConfig {

}
