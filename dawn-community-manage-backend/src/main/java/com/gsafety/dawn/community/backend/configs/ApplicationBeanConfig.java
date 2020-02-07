package com.gsafety.dawn.community.backend.configs;

import com.gsafety.java.common.utils.HttpClientUtil;
import com.gsafety.java.common.utils.HttpClientUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * The type Application bean config.
 */
@Configuration
public class ApplicationBeanConfig {
    /**
     * App init bean app init.
     *
     * @return the app init
     */
    @Bean
    public AppInit appInitBean() {
        return new AppInit();
    }

    /**
     * 配置httpclient帮助类bean到容器
     *
     * @return the http client util
     */
    @Bean
    public HttpClientUtil configHttpClientUtil() {
        return new HttpClientUtilImpl();
    }
}
