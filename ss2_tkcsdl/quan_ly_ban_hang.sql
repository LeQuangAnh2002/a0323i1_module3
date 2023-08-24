create database quan_ly_ban_hang;
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