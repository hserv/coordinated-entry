package com.hserv.coordinatedentry.housingmatching.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.hserv.coordinatedentry.housingmatching.validator.ClientValidator;


@Retention(RUNTIME)
@Target({ FIELD,ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Documented
@Constraint(validatedBy=ClientValidator.class)
public @interface ValidateClient {

	 String message() default "Invalid Client Identification";
	 String clientIdField() default "";
	 String clientDedupIdField() default "";
	 String linkField() default "";
	 Class[] groups() default {};
	 Class[] payload() default {};
}