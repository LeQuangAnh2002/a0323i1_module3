create database student_management;
use student_management;
create table Class(
 id int primary key auto_increment,
 name char(50) 
);
insert into Class(name) values ('A0323i1'),('A0423i2'),('A1222i1');
select * from class;



use student_management;
create table Teacher(
id int primary key auto_increment,
name char(50) ,
age int,
country char(50)
);

insert into teacher(name,age,country) values('huyền','20','HN');
insert into teacher(name,age,country) values('bình','22','HCM');
insert into teacher(name,age,country) values('ly','19','Nha Trang');

select * from teacher;
