package com.example.application;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.extern.java.Log;

@Order(value=1)
@Component
@Log
public class TableLister implements CommandLineRunner {
	@PersistenceContext
	EntityManager entityManager;
	
	private DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
        log.info(TableLister.class.getSimpleName() + " run method Started !!");
        showTables();
	}

	@Bean
	public JdbcTemplate createTemplate() {
		return new JdbcTemplate(getDataSourceFromHibernateEntityManager());
	}
	
	private DataSource getDataSourceFromHibernateEntityManager() {
		if ( dataSource == null ) {
			EntityManagerFactory entityManagerFactory = entityManager.getEntityManagerFactory();
			Map<String,Object> properties = entityManagerFactory.getProperties();
			for ( String key: asSortedList(properties.keySet())) {
				log.info(key + ": " + properties.get(key));
			}
			EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManagerFactory;
			dataSource = info.getDataSource(); 
		}
		return dataSource;
	}

	public void showTables() throws Exception {
        DatabaseMetaData metaData = getDataSourceFromHibernateEntityManager().getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
        while (tables.next()) {
            String tableName=tables.getString("TABLE_NAME");
            log.info(tableName);
            ResultSet columns = metaData.getColumns(null,  null,  tableName, "%");
            while (columns.next()) {
                String columnName=columns.getString("COLUMN_NAME");
                log.info("\t" + columnName);
            }
        }
    }
	
	public static
	<T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  java.util.Collections.sort(list);
	  return list;
	}
}


