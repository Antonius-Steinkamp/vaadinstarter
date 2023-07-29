package com.example.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Order(value=2)
@Component
@Log
public class SamplePersonRunner implements CommandLineRunner {

	private JdbcTemplate jdbcTemplate;
	
	public SamplePersonRunner(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void run(String... args) throws Exception {
		log.info("jdbcTemplate  is " + jdbcTemplate);
	}

}
