package com.gsafety.dawn.community.backend.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Administrator on 2017/3/2.
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan({"com.gsafety.dawn.community","com.gsafety.odata.*"})
public class Application {

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
         SpringApplication.run(Application.class, args); //NOSONAR
    }

    /**
     * Command line runner command line runner.
     *
     * @param ctx the ctx
     * @return the command line runner
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> logger.info("Spring Boot ApplicationContext started:" + ctx.getId());
    }
}
