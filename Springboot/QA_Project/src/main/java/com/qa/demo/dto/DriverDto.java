package com.qa.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverDto {

	private Long id;
	private String first_name;
	private String surname;
	private int age;
	private String nationality;
	private int race_wins;
	private String best_championship_position;
}
