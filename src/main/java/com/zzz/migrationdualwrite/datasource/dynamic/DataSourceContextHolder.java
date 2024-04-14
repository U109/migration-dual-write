package com.zzz.migrationdualwrite.datasource.dynamic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangzhongzhen wrote on 2024/4/14
 * @version 1.0
 * @description:
 */
@Slf4j
public class DataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        log.info("切换数据源：{}", dataSourceType);
        CONTEXT_HOLDER.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}

