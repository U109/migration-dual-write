package com.zzz.migrationdualwrite.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zzz.migrationdualwrite.interceptors.DynamicDataSourceInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author zhangzhongzhen wrote on 2024/3/2
 * @version 1.0
 * @description:
 */
@Configuration
@MapperScan(basePackages = {"com.zzz.migrationdualwrite.mapper"}, sqlSessionTemplateRef = "localSqlSessionTemplate")
public class DataSourceLocal {

    @Value("${spring.datasource.local.jdbc-url}")
    private String url;
    @Value("${spring.datasource.local.username}")
    private String username;
    @Value("${spring.datasource.local.password}")
    private String password;
    @Value("${spring.datasource.local.driver-class-name}")
    private String driverClassName;

    @Autowired
    private DynamicDataSourceInterceptor dynamicDataSourceInterceptor;

    /**
     * 创建local_db数据源
     */
    @Bean(name = "localDataSource")
    @Primary
    public DataSource createLocalDataSource() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("driverClassName", driverClassName);
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        return DruidDataSourceFactory.createDataSource(properties);
    }

    @Bean(name = "localSqlSessionFactory")
    @Primary
    public SqlSessionFactory localSqlSessionFactory(@Qualifier(value = "localDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置别名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.zzz.migrationdualwrite.mapper.local");
        //设置驼峰
        org.apache.ibatis.session.Configuration c = new org.apache.ibatis.session.Configuration();
        c.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(c);
        //设置映射接口的xml配置文件
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/local/*.xml"));
        sqlSessionFactoryBean.setPlugins(dynamicDataSourceInterceptor);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 创建SqlSessionTemplate
     */
    @Bean(name = "localSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate localSqlSessionTemplate(@Qualifier("localSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
