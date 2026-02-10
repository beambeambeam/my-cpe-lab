ALTER TABLE students ADD COLUMN phone VARCHAR(20);

UPDATE students
SET phone = '081-555-0101'
WHERE student_id = 1001;

ALTER TABLE courses ADD COLUMN description VARCHAR(200);

UPDATE courses
SET description = 'Intro to relational modeling'
WHERE course_id = 2001;

UPDATE grades
SET grade_value = 'B',
    grade_points = 3.00
WHERE grade_id = 4002;
