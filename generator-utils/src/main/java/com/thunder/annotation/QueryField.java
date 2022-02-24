package com.thunder.annotation;

import lombok.Getter;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
/**
 * 注解描述 查询注解，value:字段名,Type查询类型
 * 
 * @author: chenmingjun
 * @date: 2021/11/16
 */
public @interface QueryField {
    String value() default "";

    QueryType type() ;

    enum QueryType {
        EQ(0), LIST(1), LIKE(2), MIN(3), MAX(4);

        @Getter
        private Integer type;

        QueryType(Integer type) {
            this.type = type;
        }
    }
}
