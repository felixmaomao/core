package com.felix.core.annotations;

/**
 * Created by shenwei on 2018/1/18.
 */

import java.lang.annotation.*;

/**
 * @Author shenwei
 * @Date 2018/1/18 18:46
 * @Description token 校验注解
 */
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenValidated {

    boolean value() default false;
}
