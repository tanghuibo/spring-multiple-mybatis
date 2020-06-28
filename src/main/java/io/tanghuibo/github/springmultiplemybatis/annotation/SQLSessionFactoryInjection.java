package io.tanghuibo.github.springmultiplemybatis.annotation;

import io.tanghuibo.github.springmultiplemybatis.config.MybatisScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tanghuibo
 * @date 2020/6/28下午11:48
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MybatisScannerRegistrar.class)
public @interface SQLSessionFactoryInjection {

    /**
     * 名称
     */
    String name();

}
