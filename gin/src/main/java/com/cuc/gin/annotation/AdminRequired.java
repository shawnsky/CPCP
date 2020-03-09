package com.cuc.gin.annotation;

import java.lang.annotation.*;

/**
 * @author : Chen X.T.
 * @since : 2020/3/9, 周一
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminRequired {
}
