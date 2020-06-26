package io.tanghuibo.github.springmultiplemybatis.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;

/**
 * @author tanghuibo
 * @date 2020/6/27上午12:02
 */
public class MybatisScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        registryDataSource("one", registry);
        registryDataSource("two", registry);


    }

    private void registryDataSource(String namePrefix, BeanDefinitionRegistry registry) {
        String beanName = namePrefix + "DataSource";
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DataSourceFactoryBean.class);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {


    }

    static class DataSourceFactoryBean implements FactoryBean<DataSource>, EnvironmentAware {

        private Environment environment;

        @Override
        @ConfigurationProperties("spring.datasource.one")
        public DataSource getObject() throws Exception {
            DataSource dataSource = DataSourceBuilder.create().build();
            return dataSource;
        }

        @Override
        public Class<?> getObjectType() {
            return DataSource.class;
        }


        @Override
        public void setEnvironment(Environment environment) {
            this.environment = environment;
        }
    }
}
