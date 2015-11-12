package com.icoderman.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("com.icoderman")
public class JPATestConfig {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
		edb.setType(EmbeddedDatabaseType.H2);
		edb.addScript("sql/schema.sql");
		edb.addScript("sql/test-data.sql");
		EmbeddedDatabase embeddedDatabase = edb.build();
		return embeddedDatabase;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean emf(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPersistenceUnitName("user");
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan("com.icoderman");
		return emf;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
		return adapter;
	}


	@Configuration
	@EnableTransactionManagement
	public static class TransactionConfig implements TransactionManagementConfigurer {
		@Inject
		private EntityManagerFactory emf;

		public PlatformTransactionManager annotationDrivenTransactionManager() {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(emf);
			return transactionManager;
		}
	}
}
