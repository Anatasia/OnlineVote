create database vote;

use vote;

create table voteuser(
	uid int primary key auto_increment,
	uname varchar(100),
	pwd varchar(100)
);

create table votesubject(
	vsid int primary key auto_increment,
 	title varchar(3000),
 	stype int
);

create table voteoption(
	voteid int primary key auto_increment,
	voteoption varchar(3000) not null,
	vsid int not null,
	voteorder int not null
);

alter table voteoption
	add constraint fk_voteoption_vsid
		foreign key(vsid) references votesubject(vsid);

create table voteitem(
	viid int primary key auto_increment,
	voteid int not null,
	vsid int not null,
	uid int not null
);

alter table voteitem
	add constraint fk_voteitem_voteid
		foreign key(voteid) references voteoption(voteid);

alter table voteitem
	add constraint fk_voteitem_vsid
		foreign key(vsid) references votesubject(vsid);

alter table voteitem
	add constraint fk_voteitem_uid
		foreign key(uid) references voteuser(uid);

 insert into voteuser values(1,'aa','aa');
 insert into voteuser values(2,'bb','bb');
 insert into voteuser values(3,'cc','cc');

insert into votesubject values(001,'favourite subject',1);
insert into votesubject values(002,'favourite book',2);
insert into votesubject values(003,'favourite place',3);

insert into voteoption values(0001,'English',001,1);
insert into voteoption values(0002,'Math',001,2);
insert into voteoption values(0003,'Chinese',001,3);
insert into voteoption values(0004,'Harry Potter',002,1);
insert into voteoption values(0005,'The little Prince',002,2);
insert into voteoption values(0006,'The Old Man and the Sea',002,3);
insert into voteoption values(0007,'Beijing',003,1);
insert into voteoption values(0008,'Shanghai',003,2);
insert into voteoption values(0009,'Chengdu',003,3);

insert into voteitem values(1,1,1,1);
insert into voteitem values(3,3,3,3);
insert into voteitem values(2,2,2,2);