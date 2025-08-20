package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation used to indicate a method requires automatic filling of functional fields
 */

// @Target: specifies where the annotation can be used
@Target(ElementType.METHOD)
// @Retention: can be understood as the retention period (lifecycle)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {

    // Database Operation Types: Update, Insert
    OperationType value();
}
