-- Schema: housing

 DROP SCHEMA housing;

CREATE SCHEMA housing
  AUTHORIZATION postgres;

  
set schema 'housing';
/* Drop Tables */

DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS match_reservations;
DROP TABLE IF EXISTS eligible_clients;
DROP TABLE IF EXISTS client_info;
DROP TABLE IF EXISTS housing_inventory;
DROP TABLE IF EXISTS housing_unit_address;
DROP TABLE IF EXISTS survey_response;




/* Create Tables */

CREATE TABLE client_info
(
	id uuid NOT NULL,
	first_name char(50),
	middle_name char(50),
	last_name char(50),
	name_suffix char(50),
	ssn char(9),
	dob date,
	gender varchar,
	other_gender char(10),
	ethnicity varchar,
	race varchar,
	contact_number varchar,
	contact_email varchar,
	veteran_status boolean,
	date_created date,
	date_updated date,
	user_id uuid,
	CONSTRAINT client_pk PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE eligible_clients
(
	client_id uuid NOT NULL,
	spdatScore int,
	category varchar,
	matched boolean,
	survey_date date,
	spdat_label varchar,
	id uuid NOT NULL,
	PRIMARY KEY (client_id)
) WITHOUT OIDS;


CREATE TABLE housing_inventory
(
	housing_unit_id uuid NOT NULL,
	project_id varchar,
	address_id uuid NOT NULL,
	beds_current int,
	beds_capacity int,
	family_unit boolean,
	in_service boolean,
	vacant boolean,
	inactive boolean,
	date_created date,
	date_updated date,
	user_id varchar,
	PRIMARY KEY (housing_unit_id)
) WITHOUT OIDS;


CREATE TABLE housing_unit_address
(
	address_id uuid NOT NULL,
	address_line1 varchar,
	address_line2 varchar,
	address_city varchar,
	address_state varchar,
	zip_code int,
	inactive boolean,
	date_created date,
	date_updated date,
	user_id varchar,
	PRIMARY KEY (address_id)
) WITHOUT OIDS;


CREATE TABLE match_reservations
(
	reservation_id uuid NOT NULL,
	housing_unit_id uuid NOT NULL,
	client_id uuid NOT NULL,
	note_id varchar,
	match_date date,
	match_status varchar CHECK (match_status in ('accepted','rejected','nodetermination')),
	reservation_adults int,
	reservation_children int,
	manual_match boolean,
	inactive boolean,
	date_created date,
	date_updated date,
	PRIMARY KEY (reservation_id)
) WITHOUT OIDS;


CREATE TABLE note
(
	note_id varchar NOT NULL,
	note_string varchar,
	reservation_id uuid NOT NULL,
	PRIMARY KEY (note_id)
) WITHOUT OIDS;


CREATE TABLE survey_response
(
	survey_question_id varchar(10),
	response_value varchar(50),
	response_subassessment varchar(50),
	question_score int,
	client_id varchar(10),
	app_id varchar(10),
	effective_date date,
	date_created date,
	date_updated date,
	user_id varchar(10)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE eligible_clients
	ADD FOREIGN KEY (id)
	REFERENCES client_info (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE match_reservations
	ADD FOREIGN KEY (client_id)
	REFERENCES eligible_clients (client_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE match_reservations
	ADD FOREIGN KEY (housing_unit_id)
	REFERENCES housing_inventory (housing_unit_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE housing_inventory
	ADD FOREIGN KEY (address_id)
	REFERENCES housing_unit_address (address_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE note
	ADD FOREIGN KEY (reservation_id)
	REFERENCES match_reservations (reservation_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;





