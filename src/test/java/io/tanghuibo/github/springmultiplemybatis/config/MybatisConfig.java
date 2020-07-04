package io.tanghuibo.github.springmultiplemybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanghuibo
 * @date 2020/7/4下午11:51
 */
@Configuration
@MapperScan(basePackages = "io.tanghuibo.github.springmultiplemybatis.mapper.one", sqlSessionTemplateRef = "oneSqlSessionTemplate")
@MapperScan(basePackages = "io.tanghuibo.github.springmultiplemybatis.mapper.two", sqlSessionTemplateRef = "twoSqlSessionTemplate")
@MapperScan(basePackages = "io.tanghuibo.github.springmultiplemybatis.mapper.three", sqlSessionTemplateRef = "threeSqlSessionTemplate")
public class MybatisConfig {
}
