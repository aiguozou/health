package cn.skunk.log;

import java.lang.annotation.*;


/**
 * @author 82408
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default "";
}
