package com.example.application.data.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity()
@Table(name = "SAMPLE_PERSON")
@ToString
public class Person extends AbstractEntity {

	@NotNull
	@Getter
	@Setter
    private String firstName;
	
	@NotNull
	@Getter
	@Setter
    private String lastName;
	
    @Email(message = "Should be valid")
	@Getter
	@Setter
    private String email;
    
	@Getter
	@Setter
    private String phone;
	
    @Past
	@Getter
	@Setter
    private LocalDate dateOfBirth;
    
	@Getter
	@Setter
    private String occupation;
	
	@Getter
	@Setter
    private String role;
	
	@Getter
	@Setter
    private boolean important;

}
