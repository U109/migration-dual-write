package com.zzz.migrationdualwrite.datasource;

import javax.sql.DataSource;

/**
 * @author: Zzz
 * @date: 2024/4/11 16:36
 * @description:
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        CONTEXT_HOLDER.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }

    // 此处可以实现是否存在当前数据源的方法
    // 此处提供了数据源是否存在的实现
    public static boolean containsDataSource(String dataSourceId) {
        return false;
    }
//
//    // 设置数据源
//    public static void addDataSource(String key, DataSource dataSource) {
//        CONTEXT_HOLDER.put(key, dataSource);
//    }
}
