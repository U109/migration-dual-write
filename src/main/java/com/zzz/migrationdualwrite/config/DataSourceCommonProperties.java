package com.zzz.migrationdualwrite.config;

/**
 * @author zhangzhongzhen wrote on 2024/4/12
 * @version 1.0
 * @description:
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:datasource_common_config.properties") // or any other valid path
@ConfigurationProperties(prefix = "datasource.common")
@Data
public class DataSourceCommonProperties {

    private String username;
    private String password;

}
