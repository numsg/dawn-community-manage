package com.gsafety.dawn.community.backend.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

/**
 * Created by numsg on 2017/3/2.
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan({"com.gsafety.dawn.community.*", "com.numsg.odata.*"})
public class Application {

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> logger.info("Spring Boot ApplicationContext started:" + ctx.getId());
    }
}
