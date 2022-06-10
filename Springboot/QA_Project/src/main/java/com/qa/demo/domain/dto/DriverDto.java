package com.qa.demo.domain.dto;

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


	public DriverDto(Long id, String first_name, String surname, int age, String nationality, int race_wins,
			String best_championship_position) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.surname = surname;
		this.age = age;
		this.nationality = nationality;
		this.race_wins = race_wins;
		this.best_championship_position = best_championship_position;
	}

//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getFirst_name() {
//		return first_name;
//	}
//
//	public void setFirst_name(String first_name) {
//		this.first_name = first_name;
//	}
//
//	public String getSurname() {
//		return surname;
//	}
//
//	public void setSurname(String surname) {
//		this.surname = surname;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public String getNationality() {
//		return nationality;
//	}
//
//	public void setNationality(String nationality) {
//		this.nationality = nationality;
//	}
//
//	public int getRace_wins() {
//		return race_wins;
//	}
//
//	public void setRace_wins(int race_wins) {
//		this.race_wins = race_wins;
//	}
//
//	public String getBest_championship_position() {
//		return best_championship_position;
//	}
//
//	public void setBest_championship_position(String best_championship_position) {
//		this.best_championship_position = best_championship_position;
//	}

}
