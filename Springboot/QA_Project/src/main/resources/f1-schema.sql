drop table if exists driver;

create table if not exists driver (
	id bigint,
	first_name varchar(255),
	surname varchar(255),
	age integer,
	nationality varchar(255),
	race_wins integer not null,
	best_championship_position varchar(255),
	primary key (id)
	);
	
	drop table if exists team;

create table if not exists team (
	id bigint,
	name varchar(255),
	team_principal varchar(255),
	engine_manufacturer varchar(255),
	best_championship_position varchar(255),
	constructors_championships integer,
	primary key (id)
	);