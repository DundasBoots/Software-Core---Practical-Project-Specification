package com.qa.demo.domain.dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String team_name;
	private String engine_manufacturer;
	private int constructors_championships;
	private String team_principal;
	private String best_championship_position;

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Driver> drivers;
	
	public Team(Long id, String team_name, String engine_manufacturer, int constructors_championships,
			String team_principal, String best_championship_position) {
		super();
		this.id = id;
		this.team_name = team_name;
		this.engine_manufacturer = engine_manufacturer;
		this.constructors_championships = constructors_championships;
		this.team_principal = team_principal;
		this.best_championship_position = best_championship_position;
	}

	public Team(Long id, String team_name, String engine_manufacturer, int constructors_championships,
			String team_principal, String best_championship_position, List<Driver> drivers) {
		super();
		this.id = id;
		this.team_name = team_name;
		this.engine_manufacturer = engine_manufacturer;
		this.constructors_championships = constructors_championships;
		this.team_principal = team_principal;
		this.best_championship_position = best_championship_position;
		this.drivers = drivers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getEngine_manufacturer() {
		return engine_manufacturer;
	}

	public void setEngine_manufacturer(String engine_manufacturer) {
		this.engine_manufacturer = engine_manufacturer;
	}

	public int getConstructors_championships() {
		return constructors_championships;
	}

	public void setConstructors_championships(int constructors_championships) {
		this.constructors_championships = constructors_championships;
	}

	public String getTeam_principal() {
		return team_principal;
	}

	public void setTeam_principal(String team_principal) {
		this.team_principal = team_principal;
	}

	public String getBest_championship_position() {
		return best_championship_position;
	}

	public void setBest_championship_position(String best_championship_position) {
		this.best_championship_position = best_championship_position;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

}
