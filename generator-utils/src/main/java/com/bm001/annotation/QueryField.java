package com.bm001.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.Getter;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
/**
 * 注解描述 标注字段为Like,value为Like的字段名 使用WrapperUtil进行转化时会将该字段的值自动转化为Like
 * 
 * @author: chenmingjun
 * @date: 2021/11/16
 */
public @interface QueryField {
    String value() default "";

    QueryType TYPE() default QueryType.EQ;

    enum QueryType {
        EQ(0), LIST(1), LIKE(2), MIN(3), MAX(4);

        @Getter
        private Integer type;

        QueryType(Integer type) {
            this.type = type;
        }
    }
}
