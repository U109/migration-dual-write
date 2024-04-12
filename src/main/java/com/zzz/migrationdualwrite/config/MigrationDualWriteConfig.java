package com.zzz.migrationdualwrite.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Zzz
 * @date: 2024/4/12 16:39
 * @description:
 */
@Configuration
@Slf4j
public class MigrationDualWriteConfig {

    @Autowired
    @Qualifier("remoteSqlSessionFactory")
    private SqlSessionFactory remoteSqlSessionFactory;

    public void executeSql(Invocation invocation) {
        SqlSession sqlSession = null;
        BoundSql boundSql = null;
        try {
            // 获取 SQLSessionFactory
            sqlSession = remoteSqlSessionFactory.openSession(false);
            // 获取SQL语句
            MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
            Object parameter = invocation.getArgs()[1];
            boundSql = ms.getBoundSql(parameter);
            // 执行 SQL
            sqlSession.update(ms.getId(), parameter);
            // 提交事务
            sqlSession.commit();
            log.info("remoteDataSource update success !");
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
                log.error("执行 SQL 时出错", e);
                if (boundSql != null){
                    log.info("源SQL : {} ", boundSql.getSql());
                }
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }


}
