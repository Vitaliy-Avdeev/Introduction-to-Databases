create table Person(id int primary key, name varchar(50), age int, has_driver_license boolean);
create table Car(id int primary key, brand varchar(50), model varchar(50), price decimal(8, 2));
create table PersonCar
(person_id int, car_id int, primary key (person_id, car_id), foreign key (person_id) references Person (id), foreign key (car_id) references Car (id));