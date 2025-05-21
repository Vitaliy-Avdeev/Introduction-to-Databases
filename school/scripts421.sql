ALTER TABLE student
    ADD CONSTRAINT uc_student_name UNIQUE (name);

ALTER TABLE student
    ADD CONSTRAINT chk_student_age CHECK (age >= 16);

ALTER TABLE faculty
    ADD CONSTRAINT uc_faculty_name_color UNIQUE (name, color);

ALTER TABLE student
    MODIFY name VARCHAR(255) NOT NULL;

ALTER TABLE faculty
    MODIFY name VARCHAR(255) NOT NULL,
    MODIFY color VARCHAR(255) NOT NULL;

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;
