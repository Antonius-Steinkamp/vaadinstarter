package com.example.application;

import com.example.application.data.service.SamplePersonRepository;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import lombok.extern.java.Log;

import java.util.Set;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SuppressWarnings("serial")
@SpringBootApplication
@Theme(value = "vaadinstarter", variant = Lumo.DARK)
@Push
@Log
public class Application implements AppShellConfigurator, CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(DataSource dataSource,
            SqlInitializationProperties properties, SamplePersonRepository repository) {
        // This bean ensures the database is only initialized when empty
        return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties) {
            @Override
            public boolean initializeDatabase() {
                if (repository.count() == 0L) {
                    return super.initializeDatabase();
                }
                return false;
            }
        };
    }

	@Override
	public void run(final String... args) throws Exception {
		final Set<Class<?>> findAllClassesUsingClassLoader = AccessingAllClassesInPackage.findAllClassesUsingClassLoader(Application.class.getPackageName());
		for (final Class<?> cls: findAllClassesUsingClassLoader) {
			if ( cls != null) {
				log.info(() -> cls.getSimpleName() + ": " + cls.descriptorString());
			}
		}
		
	}
}
