alter table student
    add constraint age_constraint check (age > 16),
alter column name set not null,
add constraint name_unique unique (name);
alter table student alter column name set default 20;


CREATE TABLE IF NOT EXISTS Faculty
(id SERIAL PRIMARY KEY,  name VARCHAR(255) UNIQUE NOT NULL,
    color VARCHAR(50) NOT NULL);

CREATE TABLE IF NOT EXISTS Student
(id SERIAL PRIMARY KEY,  name VARCHAR(255) NOT NULL,
    age INT NOT NULL,  faculty_id INT, FOREIGN KEY (faculty_id) REFERENCES Faculty(id) ON DELETE SET NULL);