package com.baizhi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//加载时机 运行时
@Retention(RetentionPolicy.RUNTIME)
//加载位置
@Target(ElementType.FIELD)
public @interface MyAnnotation {
    public String name();
}
