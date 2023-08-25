CREATE DATABASE quan_ly_sinh_vien;
USE quan_ly_sinh_vien;
CREATE TABLE class
(
    class_id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class_name VARCHAR(60) NOT NULL,
    start_date DATETIME NOT NULL,
    `status`  BIT
);
CREATE TABLE Student
(
    student_id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(30) NOT NULL,
    address  VARCHAR(50),
    phone  VARCHAR(20),
    `status` BIT,
    class_id  INT  NOT NULL,
    FOREIGN KEY (class_id) REFERENCES class (class_id)
);
CREATE TABLE subject
(
    sub_id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sub_name VARCHAR(30) NOT NULL,
    credit INT NOT NULL DEFAULT 1 CHECK ( Credit >= 1 ),
    Status BIT DEFAULT 1
);

CREATE TABLE mark
(
    mark_id    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sub_id     INT NOT NULL,
    student_id INT NOT NULL,
    mark FLOAT  DEFAULT 0 CHECK ( Mark BETWEEN 0 AND 100),
    exam_times INT DEFAULT 1,
    UNIQUE (sub_id, student_id),
    FOREIGN KEY (sub_id) REFERENCES subject (sub_id),
    FOREIGN KEY (student_id) REFERENCES Student (student_id)
);
  -- 'Insert into dữ liệu vào quan_ly_sinh_vien' 
INSERT INTO Class(class_name,start_date,status)
VALUES ( 'A1', '2008-12-20', 1),( 'A2', '2008-12-22', 1),('B3', current_date, 0);

INSERT INTO Student (student_name, address, phone, Status, class_id)
VALUES ('Hung', 'Ha Noi', '0912113113', 1, 1),('Hoa', 'Hai phong','0905869396', 1, 1), ('Manh', 'HCM', '0123123123', 0, 2);

INSERT INTO subject(sub_name,credit,Status)
VALUES ( 'CF', 5, 1),( 'C', 6, 1),( 'HDJ', 5, 1),('RDBMS', 10, 1);
INSERT INTO Mark (sub_id, student_id, mark, exam_times)
VALUES (1, 1, 8, 1),(1, 2, 10, 2),(2, 1, 12, 1);


-- 'query'
-- '1.Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’'
select * from student where student_name like 'h%';
-- '2.Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12'
select * from class where month(start_date)  = 12;
-- '3.Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.'
select * from subject where credit between 3 and 5;
-- '4.Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2.'
set sql_safe_updates=0;
update Student set class_id = 2 where student_name ='Hung';
-- '5.Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.'
select S.student_name, Sub.sub_name,mark from mark M
join Student S on S.student_id = M.student_id
join subject Sub on Sub.sub_id = M.sub_id
order by  mark desc, student_name asc;


