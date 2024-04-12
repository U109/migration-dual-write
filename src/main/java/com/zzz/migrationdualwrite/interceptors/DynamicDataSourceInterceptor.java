package com.zzz.migrationdualwrite.interceptors;

import com.zzz.migrationdualwrite.config.MigrationDualWriteConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Zzz
 * @date: 2024/4/11 16:51
 * @description:
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Slf4j
public class DynamicDataSourceInterceptor implements Interceptor {

    @Autowired
    MigrationDualWriteConfig migrationDualWriteConfig;

    @Override
    public Object intercept(Invocation invocation) {
        Object proceed = null;
        try {
            proceed = invocation.proceed();
            migrationDualWriteConfig.executeSql(invocation);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return proceed;
    }
}
