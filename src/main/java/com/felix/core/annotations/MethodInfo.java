package com.felix.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

/**
 * Created by shenwei on 2018/1/18.
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
public @interface MethodInfo {
    String author() default "felix";

    String date();

    String comments();
    
}
