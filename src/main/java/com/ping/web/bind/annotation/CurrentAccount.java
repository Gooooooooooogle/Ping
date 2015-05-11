package com.ping.web.bind.annotation;

import com.ping.core.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定当前登录的用户(不同于@ModelAttribute)
 * @author tianym
 * @version 0.2
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface CurrentAccount {

    /**
     * 当前用户在request中的名字
     * @return
     */
    public String value() default Constant.CURRENT_ACCOUNT;

}
