package com.learn.test.test2repo.config.db1;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.learn.test.test2repo.repo.db1",
		"com.learn.test.test2repo.entity.db1" }, entityManagerFactoryRef = "postgresEntityManagerFactoryFipData", transactionManagerRef = "postgresTransactionManager")
public class UserPostgresConfig extends HikariConfig {

	@Autowired
	private Environment environment;

	@Primary
	@Bean(name = "postgresDataSourceFipData")
	@ConfigurationProperties(prefix = "postgresql.datasource")
	public DataSource postgresDataSourceFipData() {
		return new HikariDataSource();
	}

	@Primary
	@Bean(name = "postgresEntityManagerFactoryFipData")
	@PersistenceUnit(unitName = "postgresData")
	public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactoryFipData(
			EntityManagerFactoryBuilder builder, @Qualifier("postgresDataSourceFipData") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSource)
				.packages("com.learn.test.test2repo.entity.db1").persistenceUnit("postgresData").build();

//			HashMap<String, Object> properties = new HashMap<>();
//			properties.put("spring.jpa.show-sql", environment.getProperty("spring.datasource.postgres.jpa.show-sql"));
//			properties.put("hibernate.hbm2ddl-auto",
//					environment.getProperty("spring.datasource.postgres.jpa.hibernate.ddl-auto"));
//			em.setJpaPropertyMap(properties);
		return em;
	}

	@Primary
	@Bean(name = "postgresTransactionManager")
	public PlatformTransactionManager postgresTransactionManager(
			@Qualifier("postgresEntityManagerFactoryFipData") EntityManagerFactory postgresEntityManagerFactoryFipData) {
		return new JpaTransactionManager(postgresEntityManagerFactoryFipData);
	}

}
