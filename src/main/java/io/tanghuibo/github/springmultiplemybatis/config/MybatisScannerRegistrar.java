package io.tanghuibo.github.springmultiplemybatis.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
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
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registryDataSource("one", registry);
        registryDataSource("two", registry);
    }

    private void registryDataSource(String namePrefix, BeanDefinitionRegistry registry) {
        String beanName = namePrefix + "DataSource";
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DataSourceFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("namePrefix", namePrefix);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    static class DataSourceFactoryBean implements FactoryBean<DataSource>, EnvironmentAware {

        /**
         * 名称
         */
        private String namePrefix;


        private Environment environment;

        @Override
        public DataSource getObject() {
            return DataSourceBuilder.create()
                    .url(getProperty("url"))
                    .username(getProperty("username"))
                    .password(getProperty("password"))
                    .driverClassName(getProperty("driver-class-name"))
                    .build();
        }

        @Override
        public Class<?> getObjectType() {
            return DataSource.class;
        }

        private String getProperty(String key) {
            return environment.getProperty("spring.datasource." + namePrefix + "." + key);
        }

        @Override
        public void setEnvironment(Environment environment) {
            this.environment = environment;
        }

        public void setNamePrefix(String namePrefix) {
            this.namePrefix = namePrefix;
        }
    }
}
