package io.tanghuibo.github.springmultiplemybatis.config;

import io.tanghuibo.github.springmultiplemybatis.annotation.SQLSessionFactoryInjection;
import io.tanghuibo.github.springmultiplemybatis.bean.factory.DataSourceFactoryBean;
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
        if(mapperScanAttrs != null) {
            registryDataSource(mapperScanAttrs.getString("name"), registry);
        }
    }

    private void registryDataSource(String namePrefix, BeanDefinitionRegistry registry) {
        String beanName = namePrefix + "DataSource";
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DataSourceFactoryBean.class);
        beanDefinitionBuilder.addPropertyValue("namePrefix", namePrefix);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
