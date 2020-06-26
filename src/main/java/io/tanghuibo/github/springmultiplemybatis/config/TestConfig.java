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


    @Bean("threeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.three")
    public DataSource buildDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("TestData")
    public TestData buildTestData(@Qualifier("oneDataSource") DataSource dataSource1,
                            @Qualifier("twoDataSource") DataSource dataSource2,
                            @Qualifier("threeDataSource") DataSource dataSource3) {
        System.out.println(dataSource1);
        System.out.println(dataSource2);
        System.out.println(dataSource3);
        return new TestData();
    }
}
