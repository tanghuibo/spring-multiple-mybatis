package io.tanghuibo.github.springmultiplemybatis;

import io.tanghuibo.github.springmultiplemybatis.config.MybatisScannerRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MybatisScannerRegistrar.class)
public class SpringMultipleMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMultipleMybatisApplication.class, args);
	}

}
