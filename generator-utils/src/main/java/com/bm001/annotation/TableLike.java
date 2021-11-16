package com.bm001.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
/**
 * 注解描述 标注字段为Like,value为Like的列名
 *         使用WrapperUtil进行转化时会将该字段的值自动转化为Like
 * @author: chenmingjun
 * @date: 2021/11/16
 */
public @interface TableLike {
    String value() default "";
}
