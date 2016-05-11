
/* Drop Tables */

DROP TABLE IF EXISTS housing_match.note;
DROP TABLE IF EXISTS housing_match.match_reservations;
DROP TABLE IF EXISTS housing_match.eligible_clients;


/* Create Tables */

CREATE TABLE housing_match.eligible_clients
(
	client_id uuid NOT NULL,
	survey_type varchar,
	survey_score int,
	program_type varchar,
	matched boolean,
	survey_date date,
	spdat_label varchar CHECK (spdat_label in ('youth','single adult','family')),
	PRIMARY KEY (client_id)
) WITHOUT OIDS;


CREATE TABLE housing_match.match_reservations
(
	reservation_id uuid NOT NULL,
	note_id varchar,
	match_date date,
	match_status varchar CHECK (match_status in ('accepted','rejected')),
	reservation_adults int,
	reservation_children int,
	manual_match boolean,
	inactive boolean,
	date_created date,
	date_updated date,
	user_id varchar,
	client_id uuid NOT NULL,
	housing_unit_id uuid NOT NULL,
	PRIMARY KEY (reservation_id)
) WITHOUT OIDS;


CREATE TABLE housing_match.note
(
	note_id varchar NOT NULL,
	note_string varchar,
	reservation_id uuid NOT NULL,
	PRIMARY KEY (note_id)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE housing_match.match_reservations
	ADD FOREIGN KEY (client_id)
	REFERENCES housing_match.eligible_clients (client_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE housing_match.note
	ADD FOREIGN KEY (reservation_id)
	REFERENCES housing_match.match_reservations (reservation_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

