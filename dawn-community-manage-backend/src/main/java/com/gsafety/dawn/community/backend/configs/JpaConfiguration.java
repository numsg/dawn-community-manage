package com.gsafety.dawn.community.backend.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Jpa configuration.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.gsafety.dawn.community")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.gsafety.dawn.community"})
public class JpaConfiguration {
}
