create database xuat_nhap_khau;
use xuat_nhap_khau;
create table phieu_xuat(
	soPX int auto_increment primary key,
    ngay_xuat date 
);
create table vat_tu(
	maVTU int auto_increment primary key,
    ten_vat_tu varchar(50)
);
create table chi_tiet_phieu_xuat(
	maVTU int,
    soPX int,
    DGXuat float,
    SLXuat int,
    primary key(maVTU,soPX),
    foreign key(maVTU) references vat_tu(maVTU),
    foreign key(soPX) references phieu_xuat(soPX)
);

create table phieu_nhap(
	soPN int auto_increment primary key,
    ngay_nhap date 
);
create table chi_tiet_phieu_nhap(
	maVTU int,
    soPN int,
    DGNhap float,
    SLNhap int,
    primary key(maVTU,soPN),
    foreign key(maVTU) references vat_tu(maVTU),
    foreign key(soPN) references phieu_nhap(soPN)
);

create table don_dat_hang(
	soDH int auto_increment primary key,
    ngayDH date
);
create table chi_tiet_don_dat_hang(
	maVTU int,
    soDH int,
    primary key(maVTU,soDH),
    foreign key(maVTU) references vat_tu(maVTU),
    foreign key(soDH) references don_dat_hang(soDH)
);

create table nha_cung_cap(
	maNCC int auto_increment primary key,
    tenNCC varchar(50),
    diaChi varchar(100)
);
create table phone_nha_cung_cap(
	maNCC int,
    phone varchar(11),
    primary key(maNCC,phone),
    foreign key(maNCC) references nha_cung_cap(maNCC)
);
alter table don_dat_hang add column  maNCC int ;
alter table don_dat_hang add foreign key(maNCC) references nha_cung_cap(maNCC);