package io.tanghuibo.github.springmultiplemybatis.config;

import io.tanghuibo.github.springmultiplemybatis.annotation.SQLSessionFactoryInjection;
import io.tanghuibo.github.springmultiplemybatis.annotation.SQLSessionFactoryInjections;
import io.tanghuibo.github.springmultiplemybatis.bean.factory.DataSourceFactoryBean;
import io.tanghuibo.github.springmultiplemybatis.bean.factory.SqlSessionFactoryFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author tanghuibo
 * @date 2020/6/27上午12:02
 */
public class MybatisScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(SQLSessionFactoryInjection.class.getName()));
        registryMybatis(registry, mapperScanAttrs);
    }

    void registryMybatis(BeanDefinitionRegistry registry, AnnotationAttributes mapperScanAttrs) {
        if(mapperScanAttrs != null) {
            String name = mapperScanAttrs.getString("name");
            registryDataSource(name, registry);
            registrySqlSessionFactory(name, registry);
            registrySqlSessionTemplate(name, registry);
        }
    }

    private void registrySqlSessionTemplate(String namePrefix, BeanDefinitionRegistry registry) {
        String beanName = namePrefix + "SqlSessionTemplate";
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionTemplate.class);
        beanDefinitionBuilder.addConstructorArgReference(namePrefix + "SqlSessionFactory");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private void registrySqlSessionFactory(String namePrefix, BeanDefinitionRegistry registry) {
        String beanName = namePrefix + "SqlSessionFactory";
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionFactoryFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("namePrefix", namePrefix);
        beanDefinitionBuilder.addPropertyReference("dataSource", namePrefix + "DataSource");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private void registryDataSource(String namePrefix, BeanDefinitionRegistry registry) {
        String beanName = namePrefix + "DataSource";
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DataSourceFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("namePrefix", namePrefix);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    /**
     * {@link MybatisScannerRegistrar} for {@link SQLSessionFactoryInjections}.
     *
     */
    public static class RepeatingRegistrar extends MybatisScannerRegistrar {
        /**
         * {@inheritDoc}
         */
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            AnnotationAttributes mapperScansAttrs = AnnotationAttributes
                    .fromMap(importingClassMetadata.getAnnotationAttributes(SQLSessionFactoryInjections.class.getName()));
            if (mapperScansAttrs != null) {
                AnnotationAttributes[] annotations = mapperScansAttrs.getAnnotationArray("value");
                for (AnnotationAttributes annotation : annotations) {
                    registryMybatis(registry, annotation);
                }
            }
        }
    }
}
