package com.gsafety.dawn.community.backend.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by numsg on 2017/3/3.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.gsafety.dawn.community")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.gsafety.dawn.community"})
public class JpaConfiguration {
}
