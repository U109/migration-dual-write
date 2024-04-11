package com.zzz.migrationdualwrite.interceptors;

import com.zzz.migrationdualwrite.datasource.DynamicDataSourceContextHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author: Zzz
 * @date: 2024/4/11 16:51
 * @description:
 */
@Intercepts({
        @Signature(type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class DynamicDataSourceInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];

        // 通过方法名称或者其他方式获取需要切换的数据源标识
        String dataSourceKey = determineDataSource(ms);

        // 判断当前数据源是否存在，并进行切换
        if (DynamicDataSourceContextHolder.containsDataSource(dataSourceKey)) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSourceKey);
        }

        try {
            // 继续执行原方法
            return invocation.proceed();
        } finally {
            // 清除数据源设置
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以接受配置参数
    }

    private String determineDataSource(MappedStatement ms) {
        // 这里实现你的规则来切换不同的数据源，例如通过方法名或者注解等
        // ...
        return "local";
    }
}
