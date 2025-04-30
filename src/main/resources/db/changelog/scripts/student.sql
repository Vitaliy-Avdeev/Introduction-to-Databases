-- liquibase formatted sql
-- changeset Avd.Vitaliy:1 runOnChange="true"
CREATE TABLE users (
                         id SERIAL,
                         email TEXT
);

CREATE INDEX student_name_index ON public.student (name);