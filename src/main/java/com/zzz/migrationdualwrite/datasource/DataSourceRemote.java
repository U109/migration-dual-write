package com.zzz.migrationdualwrite.datasource;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = {"com.zzz.migrationdualwrite.mapper"})
public class DataSourceRemote {

    @Value("${spring.datasource.remote.jdbc-url}")
    private String url;
    @Value("${spring.datasource.remote.username}")
    private String username;
    @Value("${spring.datasource.remote.password}")
    private String password;
    @Value("${spring.datasource.remote.driver-class-name}")
    private String driverClassName;

    /**
     * 创建remote_db数据源
     */
    @Bean(name = "remoteDataSource")
//    @Primary
    public DataSource createRemoteDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        DruidXADataSource dds = new DruidXADataSource();
        dds.setUsername(username);
        dds.setPassword(password);
        dds.setUrl(url);
        dds.setDriverClassName(driverClassName);
        ds.setXaDataSource(dds);
        ds.setUniqueResourceName("remoteDataSource");
        // Set Atomikos transaction log path and name
        Properties properties = new Properties();
        properties.setProperty("com.atomikos.icatch.log_base_dir", "/transactions-log/remote/");
        properties.setProperty("com.atomikos.icatch.log_base_name", "remoteDataSource");
        ds.setXaProperties(properties);
        return ds;
    }
}
