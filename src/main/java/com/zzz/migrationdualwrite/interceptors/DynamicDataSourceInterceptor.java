package com.zzz.migrationdualwrite.interceptors;

import com.zzz.migrationdualwrite.datasource.DynamicDataSourceContextHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import javax.sql.DataSource;
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
        Object returnObject = null;
        // 获取SQL语句
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        String sql = ms.getSqlSource().getBoundSql(invocation.getArgs()[1]).getSql();
        System.out.println(sql);
        // 通过方法名称或者其他方式获取需要切换的数据源标识
        String dataSourceKey = determineDataSource(ms);
// 确定是否需要向备份数据源插入
        boolean shouldInsertToSecondary = shouldRouteToSecondaryDataSource(sql);

        // 主数据源操作
        returnObject = invocation.proceed();

        // 如果应该插入备份数据源
        if (shouldInsertToSecondary) {
            DynamicDataSourceContextHolder.setDataSourceType("secondary"); // 切换至备份数据源
//            dataSourceRemote
            try {
                returnObject = invocation.proceed(); // 执行同样的操作
            } finally {
                DynamicDataSourceContextHolder.clearDataSourceType(); // 清除数据源设置
            }
        }

        return returnObject;
    }

    private boolean shouldRouteToSecondaryDataSource(String sql) {
        // 实现你的逻辑，这里是一个简单的例子，用来判定是否包含某个表名
        return sql.toLowerCase().contains("users");
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    private String determineDataSource(MappedStatement ms) {
        // 这里实现你的规则来切换不同的数据源，例如通过方法名或者注解等
        // ...
        return "local";
    }
}
