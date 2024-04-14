package com.zzz.migrationdualwrite.datasource.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author zhangzhongzhen wrote on 2024/4/14
 * @version 1.0
 * @description:
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("change datasource : {}", DataSourceContextHolder.getDataSourceType());
        return DataSourceContextHolder.getDataSourceType();
    }

}
