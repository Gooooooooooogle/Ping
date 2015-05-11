package com.ping.web.bind.annotation;

import com.ping.core.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �󶨵�ǰ��¼���û�(��ͬ��@ModelAttribute)
 * @author tianym
 * @version 0.2
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface CurrentAccount {

    /**
     * ��ǰ�û���request�е�����
     * @return
     */
    public String value() default Constant.CURRENT_ACCOUNT;

}
