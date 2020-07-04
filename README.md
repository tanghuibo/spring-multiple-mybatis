# springboot 多数据源配置

## 配置步骤

### 使用 `SQLSessionFactoryInjection` 注入 `DataSource`, `SqlSessionFactory`, `SqlSessionTemplate`

事例

```java
@Configuration
@SQLSessionFactoryInjection(name = "one")
public class OneSessionFactoryConfig {
}
```

配置信息

```yaml
spring:
  datasource:
    one:
      url: jdbc:mysql://localhost:3306/mysql_emoji_test
      username: root
      password: 123456
      driver-class-name: com.mysql.cj.jdbc.Driver
      type: org.springframework.jdbc.datasource.SimpleDriverDataSource
mybatis:
  one:
    mapper-locations: classpath:mapping/one/**Mapper.xml
```

得到 `oneDataSource`, `oneSqlSessionFactory`, `oneSqlSessionTemplate`

### 使用 `MapperScan` 注入 mapper

事例

```java
@Configuration
@MapperScan(basePackages = "io.tanghuibo.github.springmultiplemybatis.mapper.one", sqlSessionTemplateRef = "oneSqlSessionTemplate")
public class MybatisConfig {
}
```

得到 `oneMapper`