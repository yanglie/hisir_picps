package com.hisir.picps.common;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.Mapping;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@Mapping
public @interface ApiVersion {
	double min() default Double.MIN_VALUE;	// min supported version inclusive
	double max() default Double.MAX_VALUE;  // max supported version inclusive
}
