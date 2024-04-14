package com.zzz.migrationdualwrite.datasource;

import com.zzz.migrationdualwrite.datasource.dynamic.DynamicDataSource;
import com.zzz.migrationdualwrite.enums.DataSourceType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhongzhen wrote on 2024/4/14
 * @version 1.0
 * @description:
 */
@Configuration
public class DataSourceDynamic {

    @Bean(name = DataSourceType.DYNAMIC_DATASOURCE)
    @Primary
    public DataSource dynamicDataSource(@Qualifier(DataSourceType.LOCAL_DATASOURCE) DataSource localDataSource,
                                        @Qualifier(DataSourceType.REMOTE_DATASOURCE) DataSource remoteDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //配置多数据源
        Map<Object, Object> dynamicDataSources = new HashMap<>();
        dynamicDataSources.put(DataSourceType.LOCAL_DATASOURCE, localDataSource);
        dynamicDataSources.put(DataSourceType.REMOTE_DATASOURCE, remoteDataSource);
        dynamicDataSource.setTargetDataSources(dynamicDataSources);
        // 设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(localDataSource);
        return dynamicDataSource;
    }

    /*
     * 数据库连接会话工厂
     * 将动态数据源赋给工厂
     * mapper存于resources/mapper目录下
     * 默认bean存于com.main.example.bean包或子包下，也可直接在mapper中指定
     */
    @Bean(name = "dynamicSqlSessionFactory")
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier(DataSourceType.DYNAMIC_DATASOURCE) DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dynamicDataSource);
//        sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage); //扫描bean
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));    // 扫描映射文件
        return sqlSessionFactory;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier(DataSourceType.DYNAMIC_DATASOURCE) DataSource dynamicDataSource) {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
