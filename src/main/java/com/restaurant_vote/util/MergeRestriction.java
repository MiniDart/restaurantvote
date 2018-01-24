package com.restaurant_vote.util;
import java.lang.annotation.*;
@Target(value=ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
@Inherited
public @interface MergeRestriction {
    String[] roles() default {};
}
