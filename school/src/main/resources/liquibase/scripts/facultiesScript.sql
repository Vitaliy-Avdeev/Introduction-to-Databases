-- liquibase formatted sql
-- changeset Avd.Vitaliy:2-faculty-index
CREATE INDEX faculty_sh_idx ON public.faculty (name, color)