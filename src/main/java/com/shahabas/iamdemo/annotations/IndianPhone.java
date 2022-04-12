package com.shahabas.iamdemo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.shahabas.iamdemo.CustomValidators.PhoneNumberValidator;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface IndianPhone {
	// define default course code
		public String value() default "+91";
		
		// define default error message
		public String message() default "Indian phone numbers should start with +91";
		
		// define default groups
		public Class<?>[] groups() default {};
		
		// define default payloads
		public Class<? extends Payload>[] payload() default {};
}
