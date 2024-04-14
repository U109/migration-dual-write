package com.zzz.migrationdualwrite.aop;

import com.zzz.migrationdualwrite.enums.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangzhongzhen wrote on 2024/4/14
 * @version 1.0
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceSwitch {
    String value() default DataSourceType.DEFAULT_DATASOURCE;
}