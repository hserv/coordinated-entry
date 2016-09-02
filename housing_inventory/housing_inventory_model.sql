DROP SCHEMA IF EXISTS housing_inventory cascade;
CREATE SCHEMA housing_inventory;

DROP TABLE IF EXISTS housing_inventory.housing_inventory;
DROP TABLE IF EXISTS housing_inventory.housing_unit_eligibility;
DROP TABLE IF EXISTS housing_inventory.housing_unit_address;
DROP TABLE IF EXISTS housing_inventory.housing_unit_assignment;
 

CREATE TABLE housing_inventory.housing_inventory
(
  housing_unit_id uuid NOT NULL,
  date_created timestamp(6) without time zone,
  date_updated timestamp(6) without time zone,
  inactive boolean,
  beds_capacity integer,
  beds_current integer,
  family_unit boolean,
  in_service boolean,
  project_id character varying(255),
  user_id character varying(255),
  vacant boolean,
  created_by character varying(255),
  last_modified_by character varying(255),
  alias_name character varying(250),
  schema_year integer,
  project_group_code character varying(200),
  CONSTRAINT housing_inventory_pkey PRIMARY KEY (housing_unit_id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE housing_inventory.housing_unit_address
(
  address_id uuid NOT NULL,
  date_created timestamp(6) without time zone,
  date_updated timestamp(6) without time zone,
  inactive boolean,
  address_city character varying(255),
  addressline_1 character varying(255),
  addressline_2 character varying(255),
  address_state character varying(255),
  zip_code integer,
  housing_inventory_id uuid,
  created_by character varying(255),
  last_modified_by character varying(255),
  CONSTRAINT housing_unit_address_pkey PRIMARY KEY (address_id),
  CONSTRAINT fk_housing_unit_id FOREIGN KEY (housing_inventory_id)
      REFERENCES housing_inventory.housing_inventory (housing_unit_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE housing_inventory.housing_unit_assignment
(
  assignment_id uuid NOT NULL,
  date_created timestamp(6) without time zone,
  date_updated timestamp(6) without time zone,
  inactive boolean,
  checkout_date timestamp(6) without time zone,
  client_id character varying(255),
  household_id character varying(255),
  housing_inventory_id uuid,
  created_by character varying(255),
  last_modified_by character varying(255),
  CONSTRAINT housing_unit_assignment_pkey PRIMARY KEY (assignment_id),
  CONSTRAINT fk_housing_unit_id FOREIGN KEY (housing_inventory_id)
      REFERENCES housing_inventory.housing_inventory (housing_unit_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE housing_inventory.housing_unit_eligibility
(
  eligibility_id uuid NOT NULL,
  housing_unit_id uuid,
  project_id uuid,
  eligibility text,
  date_created timestamp(6) without time zone,
  date_updated timestamp(6) without time zone,
  user_id character varying(255),
  created_by character varying(255),
  last_modified_by character varying(255),
  inactive boolean,
  project_group_code character varying(200),
  CONSTRAINT housing_eligibility PRIMARY KEY (eligibility_id)
)
WITH (
  OIDS=FALSE
);