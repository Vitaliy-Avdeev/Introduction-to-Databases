select * from student where age between 10 and 12;

select name from student;

select * from student where name like '%d%';

select * from student where age < id;

select * from student order by age;

select count(name)
from student;

SELECT MIN(age) as minAge, MAX(age) as maxAge, AVG(age) as averageAge FROM student WHERE name != 'null';
SELECT * FROM student WHERE name != 'null' ORDER BY id DESC LIMIT 5;