package com.zzz.migrationdualwrite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 张忠振
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MigrationDualWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MigrationDualWriteApplication.class, args);
    }
}
