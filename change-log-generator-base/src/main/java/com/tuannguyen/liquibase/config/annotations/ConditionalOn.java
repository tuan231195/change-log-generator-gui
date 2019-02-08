package com.tuannguyen.liquibase.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Predicate;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Repeatable(ConditionalList.class)
public @interface ConditionalOn
{
	String field() default "";

	String[] value() default "";

	Class<? extends Predicate> predicateClass() default Predicate.class;
}
