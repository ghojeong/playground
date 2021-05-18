package com.oauth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Login이 필요한 API일 경우 사용한다.
 */
@Target({METHOD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface LoginRequired {
}
