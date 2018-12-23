package com.sy.study.springboot.demo.test.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//声明可以使用该注解的目标
@Retention(RetentionPolicy.RUNTIME)//声明注解的保留限
public @interface Log {

    String module() default "";
    String remark() default "";
    OperateEnum operate();

    enum OperateEnum {
        add,edit,delete,query;
    }
}

