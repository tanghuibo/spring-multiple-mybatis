package io.tanghuibo.github.springmultiplemybatis.bean.factory;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author tanghuibo
 * @date 2020/6/29上午12:04
 */
public class SqlSessionFactoryFactoryBean implements FactoryBean<SqlSessionFactory>, EnvironmentAware {

    /**
     * 名称
     */
    private String namePrefix;

    private Environment environment;

    private DataSource dataSource;

    @Override
    public SqlSessionFactory getObject() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(getMapperLocation()));
        return bean.getObject();
    }

    private String getMapperLocation() {
        return environment.getProperty("mybatis." + namePrefix + ".mapper-locations");
    }

    @Override
    public Class<?> getObjectType() {
        return SqlSessionFactory.class;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}