package com.qa.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDto {

	private Long id;
	private String name;
	private String engine_manufacturer;
	private int constructors_championships;
	private String team_principal;
	private String best_championship_position;
}
