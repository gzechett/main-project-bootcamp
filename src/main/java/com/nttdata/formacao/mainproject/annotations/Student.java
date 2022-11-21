package com.nttdata.formacao.mainproject.annotations;

import com.nttdata.formacao.mainproject.validators.StudentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = StudentValidator.class)
@Target( {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Student {

    String message() default "Error in the provided data, please review all fields and check if the schoolarYear matchs the age";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
