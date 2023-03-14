package com.validation.customValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateTimeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeConstraint {
    String message() default "Format yyyyMMddHHmmss";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
