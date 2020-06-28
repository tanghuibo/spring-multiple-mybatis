package io.tanghuibo.github.springmultiplemybatis.config;

import io.tanghuibo.github.springmultiplemybatis.annotation.SQLSessionFactoryInjection;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanghuibo
 * @date 2020/6/28下午11:57
 */
@Configuration
@SQLSessionFactoryInjection(name = "two")
public class TwoSessionFactoryConfig {
}
