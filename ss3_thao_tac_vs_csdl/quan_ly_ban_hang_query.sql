create database quan_ly_ban_hang_query;
use quan_ly_ban_hang_query;
create table Customer(
	cID varchar(100) primary key,
    cName varchar(100),
    cAge int
);
create table OrderProduct(
	OrderID int auto_increment primary key,
    OrderDate date,
    Price double,
    cID varchar(100),
    foreign key(cID) references Customer(cID)
); 
create table Product(
	ProductID int auto_increment primary key,
    ProductName varchar(100),
    Price double
);
create table OderDetail(
	ProductID int,
    OrderID int,
    primary key(ProductID,OrderID),
    foreign key(OrderID) references OrderProduct(OrderID),
    foreign key(ProductID) references Product(ProductID)
);
alter table OderDetail add column quantity int not null;

-- 'Insert into'
Insert into Customer(cID,cName,cAge) values(1,'Minh Quan','10'),(2,'Ngoc Oanh','20'),(3,'Hong Ha','50');
Insert into orderproduct(OrderID,OrderDate,Price,cID) values(1,'2006-03-21','2000',1),(2,'2006-03-23','3000',2),(3,'2006-03-16','2500',1);
insert into product(ProductID,ProductName,Price) values(1,'Máy giặt','3'),(2,'Tủ lạnh','5'),(3,'Điều hòa','7'),(4,'Quạt','1'),(5,'Bếp điện','2');
Insert into oderdetail(OrderId,ProductId,quantity) 
values(1,1,3),(1,3,7),(1,4,2),(2,1,1),(3,1,8),(2,5,4),(2,3,3);
-- "1.Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order"
select OrderId,OrderDate,Price from OrderProduct;
-- "2.Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách"


select C.cName, P.ProductName,sum(Odetail.quantity) as totail
from Customer C
inner join OrderProduct Odproduct on Odproduct.cID = C.cID
join OderDetail Odetail on Odetail.orderID = Odproduct.orderID
join Product P on Odetail.ProductID  = P.ProductID
group by C.cName,P.ProductName;

-- "3.Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào"
select C.cID,C.cName from Customer C
where not exists(select * from orderproduct where C.cID =cID);
-- "4.Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn Giá bán của từng loại được tính = odQTY*pPrice)" 

select P.ProductID,O.OrderDate, sum(Ordetail.quantity * O.Price) as tongTien
from orderproduct O inner join oderdetail Ordetail on O.OrderID =Ordetail.OrderID
join Product P on Ordetail.ProductID  = P.ProductID
group by P.ProductID,O.OrderDate;