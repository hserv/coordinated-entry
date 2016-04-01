
/* Drop Tables */

DROP TABLE IF EXISTS match_reservations;
DROP TABLE IF EXISTS client_contact_info;
DROP TABLE IF EXISTS housing_inventory;
DROP TABLE IF EXISTS housing_unit_address;
DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS user_views;




/* Create Tables */

CREATE TABLE client_contact_info
(
	client_id varchar NOT NULL,
	client_phone_number varchar,
	client_email varchar,
	PRIMARY KEY (client_id)
) WITHOUT OIDS;


CREATE TABLE housing_inventory
(
	housing_unit_id varchar NOT NULL,
	project_id varchar,
	address_id varchar,
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
	address_id varchar NOT NULL,
	housing_unit_id varchar,
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
	reservation_id varchar NOT NULL,
	housing_unit_id varchar,
	client_id varchar,
	note_id varchar,
	match_date date,
	match_status varchar CHECK (match_status in ('accepted','rejected','nodetermination')),
	reservation_adults int,
	reservation_children int,
	manual_match boolean,
	inactive boolean,
	date_created date,
	date_updated date,
	user_id varchar,
	PRIMARY KEY (reservation_id)
) WITHOUT OIDS;


CREATE TABLE note
(
	note_id varchar NOT NULL,
	note_string varchar,
	PRIMARY KEY (note_id)
) WITHOUT OIDS;


CREATE TABLE user_views
(
	reservation_id varchar,
	user_id varchar NOT NULL,
	date_viewed date,
	PRIMARY KEY (user_id)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE match_reservations
	ADD FOREIGN KEY (client_id)
	REFERENCES client_contact_info (client_id)
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


ALTER TABLE match_reservations
	ADD FOREIGN KEY (note_id)
	REFERENCES note (note_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE match_reservations
	ADD FOREIGN KEY (user_id)
	REFERENCES user_views (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



