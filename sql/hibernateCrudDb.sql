CREATE DATABASE IF NOT EXISTS hibernatecrudDb;

USE hibernatecrudDb;

create table company
(
  companyid int(100) auto_increment
    primary key,
  name varchar(50) null,
  hvhh varchar(50) null,
  areaofspace double(8,3) default '0.000' null,
  factualAddress varchar(255) null
)
;

create table payorder
(
  orderid int auto_increment
    primary key,
  user_id int null,
  company_id int null,
  score int default '0' null,
  paydate datetime null,
  year int not null,
  mount int not null,
  constraint payorder_company_companyid_fk
  foreign key (company_id) references hibernatecrudDb.company (companyid)
    on update cascade on delete cascade
)
;

create index payorder_company_companyid_fk
  on payorder (company_id)
;

create index payorder_user_userid_fk
  on payorder (user_id)
;

create table user
(
  userid int(100) auto_increment
    primary key,
  name varchar(50) null,
  srname varchar(50) null,
  patronymic varchar(50) null,
  village varchar(50) null,
  address varchar(255) null,
  pepolecount int(30) default '0' null,
  activ tinyint(1) default '1' null,
  disable_start date null,
  disable_end date null
)
;

alter table payorder
  add constraint payorder_user_userid_fk
foreign key (user_id) references hibernatecrudDb.user (userid)
  on update cascade on delete cascade
;

