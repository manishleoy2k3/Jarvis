package com.qaninjas.keyword;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Keywords {

	String description();
	
	String category() default "";
	
	String author();
	
	String tag() default "";
}
