package com.zzz.migrationdualwrite.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Zzz
 * @date: 2024/4/11 16:34
 * @description: 用于指定方法应当使用的数据源
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TargetDataSource {

    String value();

}
