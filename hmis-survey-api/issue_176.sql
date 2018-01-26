DROP TABLE IF EXISTS survey.client_survey_submission;
CREATE TABLE survey.client_survey_submission
(
  id uuid NOT NULL DEFAULT uuid_generate_v4(),
  client_id uuid,
  survey_id uuid,
  submission_id uuid,
  global_enrollment_id uuid,
  project_group_code character varying(256),
  created_at date,
  updated_at date,
  user_id character varying(256),
  is_active boolean,
  deleted boolean,
  CONSTRAINT client_submission_pk PRIMARY KEY (id),
  CONSTRAINT unique_set UNIQUE (client_id, submission_id, survey_id)
)
WITH (
  OIDS=FALSE
);
insert  into survey.client_survey_submission (client_id, submission_id, survey_id,project_group_code,created_at,updated_at,user_id,is_active,deleted )select distinct on (client_id, submission_id, survey_id) client_id, submission_id, survey_id ,project_group_code,created_at,updated_at,user_id,is_active,deleted from survey.response where client_id IS NOT NULL and submission_id IS NOT NULL;
