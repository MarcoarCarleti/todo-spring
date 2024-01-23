package br.com.marcocarleti.todoapplication.tasks.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfig {

	@Autowired
	private DataSourceConfig dataSourceConfig;
	
	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
		liquibase.setDataSource(dataSourceConfig.dataSource()); // Configure o seu DataSource aqui
		return liquibase;
	}
}
