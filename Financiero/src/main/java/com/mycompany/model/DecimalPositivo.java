package com.mycompany.model;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@NotNull
@DecimalMin("0")
public @interface DecimalPositivo {

	@OverridesAttribute(constraint = DecimalMin.class, name = "message")
	String message() default "{com.mycompany.NumeroDecimal.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
