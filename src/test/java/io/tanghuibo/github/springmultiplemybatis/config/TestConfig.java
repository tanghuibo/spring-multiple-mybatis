package io.tanghuibo.github.springmultiplemybatis.config;

import io.tanghuibo.github.springmultiplemybatis.TestData;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author tanghuibo
 * @date 2020/6/27上午1:27
 */
@Configuration
public class TestConfig {



    @Bean("TestData")
    public TestData buildTestData(List<DataSource> dataSourceList,
                                  List<SqlSessionFactory> sqlSessionFactories,
                                  List<SqlSessionTemplate> sqlSessionTemplates) {
        System.out.println(dataSourceList);
        System.out.println(sqlSessionFactories);
        System.out.println(sqlSessionTemplates);
        return new TestData();
    }
}
