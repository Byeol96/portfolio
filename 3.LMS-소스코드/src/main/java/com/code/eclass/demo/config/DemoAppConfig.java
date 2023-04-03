package com.code.eclass.demo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.code.eclass.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig implements WebMvcConfigurer{
	
	@Autowired
	private Environment env;	
		
	private Logger logger=Logger.getLogger(getClass().getName());
	
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
        .addResourceHandler("/resources/**").addResourceLocations("/resources/"); 
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
		
	}
	
	
	
	@Bean
	public DataSource comboPooledDataSource() {
		
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info(">>>>>>>>>>>>>jdbc.url===="+env.getProperty("jdbc.url"));
		logger.info(">>>>>>>>>>>>>jdbc.user===="+env.getProperty("jdbc.user"));
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		securityDataSource.setInitialPoolSize(changeProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(changeProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(changeProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(changeProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}

	private int changeProperty(String propertyKey) {
		
		return Integer.valueOf(env.getProperty(propertyKey));
	}
	
	/*
	 * <!-- Hibernate session factory setting up --> <bean id="sessionFactory"
	 * class="org.springframework.orm.hibernate5.LocalSessionFactoryBean"> <property
	 * name="dataSource" ref="myDataSource" /> <property name="packagesToScan"
	 * value="com.code.springdemo.entity" /> <property name="hibernateProperties">
	 * <props> <prop
	 * key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop> <prop
	 * key="hibernate.show_sql">true</prop> </props> </property> </bean>
	 */
	
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		LocalSessionFactoryBean sessionFactory  = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(comboPooledDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(properties);
		return sessionFactory;
	}
	
	
	/*
	 * <!-- Hibernate transaction manager setting up --> <bean
	 * id="myTransactionManager"
	 * class="org.springframework.orm.hibernate5.HibernateTransactionManager">
	 * <property name="sessionFactory" ref="sessionFactory"/> </bean>
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	/*
	 * <!-- @Transactional : transactional annotation configuration -->
	 * <tx:annotation-driven transaction-manager="myTransactionManager" />
	 */


}
