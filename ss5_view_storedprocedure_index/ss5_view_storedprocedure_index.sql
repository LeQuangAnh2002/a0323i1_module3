create database data_products;
use data_products;
create table Product(
product_id int auto_increment primary key,
product_code varchar(52) ,
product_name varchar(50),
product_price int ,
product_amount int,
product_description varchar(50),
product_status varchar(50));


use data_products;
insert into Product(product_code,product_name,product_price,product_amount,product_description,product_status) values
('A01','iPhone5',2300,10,'Hàng mới','còn hàng'),
('A02','iPhone6',3500,11,'Hàng cũ','còn hàng'),
('A03','iPhone7',1000,12,'Hàng mới','còn hàng'),
('A04','iPhone8',5400,13,'Hàng cũ','còn hàng'),
('A05','iPhone9',61200,14,'Hàng mới','hết hàng'),
('A06','iPhone10',7200,15,'Hàng cũ','còn hàng'),
('A07','iPhone11',8400,167,'Hàng mới','hêt hàng'),
('A08','iPhone12',9500,17,'Hàng cũ','còn hàng');

-- "Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)"
Create unique index index_product on product(product_code);
explain select * from product where product_code = 'A06';
Create unique index index_prodouctName on product(product_name ,product_price);
explain select * from product where product_name = 'iPhone10' and product_price = '7200';

-- "Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
-- Tiến hành sửa đổi view
-- Tiến hành xoá view"
create view view_product as
select product_code, product_name, product_price, product_status 
from product;
select * from view_product;
-- Tiến hành sửa đổi view
create or replace view view_products as
select product_code, product_name, product_price, product_status 
from product
where product_name = 'iPhone9';
select * from view_products;
-- Tiến hành xóa view
drop view view_products;

-- "Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
-- Tạo store procedure thêm một sản phẩm mới
-- Tạo store procedure sửa thông tin sản phẩm theo id
-- Tạo store procedure xoá sản phẩm theo id"
DELIMITER //
Create procedure show_product()
Begin
Select * from product;
end //
DELIMITER // ;

-- "gọi show_product"
call show_product();

-- them mot san pham

DELIMITER // 
create Procedure Add_Product
(in product_id int , in product_code varchar(52) ,in product_name varchar(50),in product_price int ,in product_amount int,in product_description varchar(50),in product_status varchar(50))
Begin
insert into product
values(product_id,product_code,product_name,product_price,product_amount,product_description,product_status);
End//
DELIMITER ;

call Add_Product('9','A003','den',100,1,'tot','ton nhieu');

select * from Product;

-- sua thong tin san pham theo id
DELIMITER // 
create procedure update_product
(in p_product_id int,in p_product_code varchar(52) ,in p_product_name varchar(50),in p_product_price int ,in p_product_amount int,in p_product_description varchar(50),in p_product_status varchar(50))
Begin 
update Product set
product_code = p_product_code,
product_name = p_product_name,
product_price = p_product_price,
product_amount = p_product_amount,
product_description = p_product_description,
product_status = p_product_status
where product_id = p_product_id;
End //
DELIMITER ;

call update_product('9','A123','Iphone15',200,10,'ko tốt','hết hàng');
select * from Product;

-- xoa san pham theo id
DELIMITER // 
create procedure delete_product(in p_product_id int)
Begin
  DELETE FROM Product WHERE product_id = p_product_id;
End //
DELIMITER ;
call delete_product('9');