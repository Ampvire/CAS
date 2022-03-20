//package ru.edu.cas.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@EnableJpaRepositories(basePackages = "ru.edu.cas.analitics.dao.repo")
//@Configuration
//public class MyConfig {
//
//    @Bean
//    public DataSource dataSource(){
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:sqlite:db/cas.db");
//        dataSource.setDriverClassName("org.sqlite.JDBC");
//        return dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
//
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//
//        factoryBean.setDataSource(dataSource());
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        factoryBean.setJpaVendorAdapter(vendorAdapter);
//
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
//        properties.setProperty("hibernate.show_sql", "true");
//        factoryBean.setJpaProperties(properties);
//
//        factoryBean.setPackagesToScan("ru.edu.cas.analitics.dao.entity");
//
//        return factoryBean;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}
