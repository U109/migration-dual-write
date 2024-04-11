package com.zzz.migrationdualwrite.config;

import com.zzz.migrationdualwrite.datasource.DataSourceLocal;
import com.zzz.migrationdualwrite.datasource.DataSourceRemote;
import com.zzz.migrationdualwrite.interceptors.DynamicDataSourceInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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

//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dynamicDataSource) {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dynamicDataSource);
//        sqlSessionFactory.setPlugins(interceptors());
//        // ...其他配置
//        return sqlSessionFactory;
//    }
}
