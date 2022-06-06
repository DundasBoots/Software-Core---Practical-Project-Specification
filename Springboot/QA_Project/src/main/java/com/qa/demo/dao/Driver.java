package com.qa.demo.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Driver {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String first_name;
	private String surname;
	private int age;
	private String nationality;
	private int race_wins;
	private String best_championship_position;
}
