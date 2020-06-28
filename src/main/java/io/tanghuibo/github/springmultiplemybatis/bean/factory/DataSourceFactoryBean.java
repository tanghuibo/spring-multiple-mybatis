package io.tanghuibo.github.springmultiplemybatis.bean.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author tanghuibo
 * @date 2020/6/29上午12:04
 */
public class DataSourceFactoryBean implements FactoryBean<DataSource>, EnvironmentAware {

    /**
     * 名称
     */
    private String namePrefix;


    private Environment environment;

    @Override
    public DataSource getObject() throws ClassNotFoundException {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create()
                .url(getProperty("url"))
                .username(getProperty("username"))
                .password(getProperty("password"));
        String driverClassName = getProperty("driver-class-name");
        if(!StringUtils.isEmpty(driverClassName)) {
            dataSourceBuilder.driverClassName(driverClassName);
        }

        String type = getProperty("type");

        if(!StringUtils.isEmpty(type)) {
            dataSourceBuilder.type((Class<? extends DataSource>)Class.forName(type));
        }
        return dataSourceBuilder.build();
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