SELECT s.name AS student_name, s.age, f.name AS faculty_name
FROM student s JOIN faculty f ON s.faculty_id = f.id;

SELECT s.name, s.age FROM student s join public.avatar a on s.id = a.student_id
WHERE a.student_id IS NOT NULL;