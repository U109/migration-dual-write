package com.zzz.migrationdualwrite.config;

import com.zzz.migrationdualwrite.interceptors.DynamicDataSourceInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Zzz
 * @date: 2024/4/11 16:52
 * @description:
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public DynamicDataSourceInterceptor dynamicDataSourceInterceptor() {
        return new DynamicDataSourceInterceptor();
    }

    @Bean
    public Interceptor[] interceptors() {
        return new Interceptor[]{dynamicDataSourceInterceptor()};
    }
}
