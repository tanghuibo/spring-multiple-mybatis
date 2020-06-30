package io.tanghuibo.github.springmultiplemybatis.annotation;

import java.lang.annotation.*;

/**
 * @author tanghuibo
 * @date 2020/6/28下午11:48
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SQLSessionFactoryInjections {


    SQLSessionFactoryInjection[] value();

}
