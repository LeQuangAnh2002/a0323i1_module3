use quan_ly_sinh_vien;
-- '1.Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.'
select * from subject having Credit = (select max(Credit) from subject ) ;
-- '2.Hiển thị các thông tin môn học có điểm thi lớn nhất.'
select S.sub_name,S.credit,S.status, max(M.mark) as diem_cao_nhat from subject S
join mark M on M.sub_id = S.sub_id
;
-- '3.Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần'
select S.student_name,avg(M.mark) as diem_trung_binh from student S
join mark M on  M.student_id = S.student_id
group by S.student_name
order by diem_trung_binh desc;
 