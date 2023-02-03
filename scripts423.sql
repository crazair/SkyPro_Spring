SELECT student.name, student.age, f.name FROM student LEFT JOIN faculty f on f.id = student.faculty_id;

SELECT student.name FROM student INNER JOIN avatar on student.id = avatar.student_id;