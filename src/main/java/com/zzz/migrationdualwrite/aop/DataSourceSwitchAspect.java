package com.zzz.migrationdualwrite.aop;

import com.zzz.migrationdualwrite.datasource.dynamic.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhongzhen wrote on 2024/4/14
 * @version 1.0
 * @description:
 */
@Aspect
@Order(-1)
@Component
public class DataSourceSwitchAspect {

    @Before("@annotation(dataSourceSwitch)")
    public void around(JoinPoint point, DataSourceSwitch dataSourceSwitch) throws Throwable {
        // 这里是方法执行之前可以执行的代码
        // 比如你可以根据 dataSourceSwitch 注解里的值来设置不同的数据源
        DataSourceContextHolder.setDataSourceType(dataSourceSwitch.value());
    }

    @After("@annotation(dataSourceSwitch)")
    public void restoreDataSource(JoinPoint point, DataSourceSwitch dataSourceSwitch) {
        // 将数据源置为默认数据源
        DataSourceContextHolder.clearDataSourceType();
    }

}

