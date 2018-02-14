DROP SCHEMA IF EXISTS "survey" cascade;
CREATE SCHEMA "survey";

DROP TABLE IF EXISTS "survey".picklist_group; 
DROP TABLE IF EXISTS "survey".picklist_value; 
DROP TABLE IF EXISTS "survey".question_group; 
DROP TABLE IF EXISTS "survey".question; 
DROP TABLE IF EXISTS "survey".survey; 
DROP TABLE IF EXISTS "survey".survey_section; 
DROP TABLE IF EXISTS "survey".section_question_mapping; 
DROP TABLE IF EXISTS "survey".response; 
DROP TABLE IF EXISTS "survey".section_score; 
DROP TABLE IF EXISTS "survey".client_survey_submission

CREATE TABLE survey.picklist_group
(
  id uuid NOT NULL,
  picklist_group_name character varying(256),
  created_at timestamp without time zone,
  updated_at timestamp without time zone,
  user_id character varying(256),
  is_active boolean,
  CONSTRAINT pick_list_group_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE survey.picklist_value
(
  id uuid NOT NULL,
  picklist_value_code character varying(256),
  value_text character varying(256),
  created_at date,
  updated_at date,
  user_id character varying(256),
  is_active boolean,
  picklist_group_id uuid,
  CONSTRAINT picklist_value_pk PRIMARY KEY (id),
  CONSTRAINT picklist_group_id_fk FOREIGN KEY (picklist_group_id)
      REFERENCES survey.picklist_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE survey.question_group
(
  id uuid NOT NULL,
  question_group_name character varying(256),
  created_at date,
  updated_at date,
  user_id character varying(256),
  is_active boolean,
  CONSTRAINT question_group_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE survey.question
(
  id uuid NOT NULL,
  project_group_id character varying(256),
  question_description character varying(256),
  display_text character varying(256),
  question_data_type character varying(256),
  question_type character varying(256),
  correct_value_for_assessment character varying(256),
  hud_question boolean,
  locked boolean,
  question_weight integer,
  created_at date,
  updated_at date,
  user_id character varying(256),
  is_active boolean,
  question_group_id uuid,
  picklist_group_id uuid,
  is_copy_question_id boolean,
  CONSTRAINT question_pk PRIMARY KEY (id),
  CONSTRAINT picklist_group_id FOREIGN KEY (picklist_group_id)
      REFERENCES survey.picklist_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT question_group_id FOREIGN KEY (question_group_id)
      REFERENCES survey.question_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);



CREATE TABLE survey.survey
(
  id uuid NOT NULL,
  project_group_id character varying(256),
  survey_title character varying(256),
  survey_owner character varying(256),
  locked boolean,
  is_copy_survey_id boolean,
  tag_value character varying(256),
  created_at timestamp without time zone,
  updated_at timestamp without time zone,
  user_id character varying(256),
  is_active boolean DEFAULT true,
  CONSTRAINT survey_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE survey.survey_section
(
  section_text character varying(256),
  section_detail text,
  section_weight integer,
  section_order integer,
  created_at date,
  updated_at date,
  user_id character varying(256),
  is_active boolean,
  id uuid NOT NULL,
  survey_id uuid,
  CONSTRAINT survey_section_pk PRIMARY KEY (id),
  CONSTRAINT survey_id_fk FOREIGN KEY (survey_id)
      REFERENCES survey.survey (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE survey.section_question_mapping
(
  id uuid NOT NULL,
  is_required boolean,
  created_at date,
  updated_at date,
  user_id character varying(256),
  is_active boolean,
  question_id uuid,
  question_group_id uuid,
  section_id uuid,
  CONSTRAINT section_question_mapping_pk PRIMARY KEY (id),
  CONSTRAINT question_group_id_fk FOREIGN KEY (question_group_id)
      REFERENCES survey.question_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT question_id_fk FOREIGN KEY (question_id)
      REFERENCES survey.question (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT suervey_section_id_fk FOREIGN KEY (section_id)
      REFERENCES survey.survey_section (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE survey.response
(
  id uuid NOT NULL,
  response_text character varying(256),
  question_score integer,
  app_id character varying(256),
  effective_date date,
  created_at date,
  updated_at date,
  user_id character varying(256),
  is_active boolean,
  section_id uuid,
  question_id uuid,
  client_id uuid,
  survey_id uuid,
  refused boolean,
  CONSTRAINT response_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE survey.section_score
(
  id uuid NOT NULL,
  section_score integer,
  created_at date,
  updated_at date,
  user_id character varying(256),
  section_id uuid,
  survey_id uuid,
  client_id uuid,
  is_active boolean,
  CONSTRAINT section_score_fk PRIMARY KEY (id),
  CONSTRAINT section_id_fk FOREIGN KEY (section_id)
      REFERENCES survey.survey_section (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT survey_id_fk FOREIGN KEY (survey_id)
      REFERENCES survey.survey (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE survey.client_survey_submission
(
  id uuid default uuid_generate_v4() NOT NULL,
  client_id UUID,
  survey_id UUID,
  submission_id UUID,
  global_enrollment_id UUID,
  project_group_code character varying(256),
  created_at date,
  updated_at date,
  user_id character varying(256),
  is_active boolean,
  deleted boolean,
  CONSTRAINT client_submission_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);