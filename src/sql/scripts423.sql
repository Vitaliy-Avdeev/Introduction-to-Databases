SELECT student.name,student.age, faculty_id, faculty.name
from student
         INNER JOIN faculty on student.faculty_id = faculty.id;


SELECT student.name, avatar.file_path, avatar.file_size, avatar.media_type ,student_id
from avatar
         INNER JOIN student on avatar.student_id = student.id;