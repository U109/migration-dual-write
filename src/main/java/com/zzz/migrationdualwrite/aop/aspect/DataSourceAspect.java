package com.zzz.migrationdualwrite.aop.aspect;

import com.zzz.migrationdualwrite.aop.annotations.TargetDataSource;
import com.zzz.migrationdualwrite.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author: Zzz
 * @date: 2024/4/11 16:35
 * @description:
 */
@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable {
        String dsId = targetDataSource.value();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            System.out.println("数据源[" + dsId + "]不存在，使用默认数据源 > " + point.getSignature());
        } else {
            System.out.println("Use DataSource : " + dsId + " > " + point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(dsId);
        }
    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        System.out.println("Revert DataSource : " + targetDataSource.value() + " > " + point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
