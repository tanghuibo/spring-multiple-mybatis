package io.tanghuibo.github.springmultiplemybatis.config;

import io.tanghuibo.github.springmultiplemybatis.TestData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author tanghuibo
 * @date 2020/6/27上午1:27
 */
@Configuration
public class TestConfig {



    @Bean("TestData")
    public TestData buildTestData(@Qualifier("oneDataSource") DataSource oneDataSource,
                                  @Qualifier("twoDataSource") DataSource twoDataSource,
                                  @Qualifier("threeDataSource") DataSource threeDataSource,
                                  @Qualifier("fourDataSource") DataSource fourDataSource) {
        System.out.println(oneDataSource);
        System.out.println(twoDataSource);
        System.out.println(threeDataSource);
        System.out.println(fourDataSource);
        return new TestData();
    }
}
