use ESB;

create table [User](
	userID int not null identity(1,1) primary key,
	userName nvarchar(50) not null unique,
	userNickname nvarchar(50) not null,
	email nvarchar(150) not null unique,
	phoneNumber varchar(20) UNIQUE NOT NULL,
	password nvarchar(MAX) NOT NULL,
	biography nvarchar(MAX));
select * from [User];

create table Sboards(
	sboardID int not null primary key identity(1,1), 
	sboardTitle nvarchar(50),
	sbulletin  nvarchar(max),
	createTime datetime default getdate(),
	isDeleted int not null);
select * from Sboards;

create table Post(
	postID int not null primary key identity(1,1),
	postTitle nvarchar(50) ,
	content nvarchar(max) ,
	userID int not null foreign key references [User](userID),
	sboardID int not null foreign key references Sboards(sboardID),
	createdAt datetime default getdate(),
	isDeleted int not null);
select * from Post;

create table Replies(
	replyID int not null primary key identity(1,1),
	replyContent nvarchar(max) ,
	userID int not null foreign key references [User](userID),
	postID int not null foreign key references Post(postID),
	createdAt datetime default getdate(),
	isDeleted int not null);
select * from Replies;

create table Comment(
	commentID int not null primary key identity(1,1),
	content nvarchar(max) ,
	userID int not null foreign key references [User](userID),
	targetType nvarchar(20) ,
	postID int foreign key references Post(postID),
	replyID int foreign key references Replies(replyID),
	createdAt datetime default getdate(),
	isDeleted int not null);
select * from Comment;

---------------------------------------------------------------------------------------------------------------
--����Ƥ譱�e���e�{�A�����ϥΪ̵L�k�n�J�A�]�K�X�O�����s���S�[�K�A����\��ݱq���U�}�l--
insert into [User](userName, userNickname, email, phoneNumber, password, biography) values('�V����', 'GamerBee', 'bee@gmail.com', '0912345678', 'bbb666',  '�ٴ�����');
insert into [User](userName, userNickname, email, phoneNumber, password, biography) values('�L�߰�', 'OilKing', 'oil@gmail.com', '0987654321', 'kkk777',  '�����۱���');

--Sboards�@�w�n���[����ơA�]�ثe���g��2��--
insert into Sboards(sboardTitle, sbulletin, isDeleted) values('�֥��ۭ�', '���i:�ȯ�Q�ק֥��ۭ�', 0);
insert into Sboards(sboardTitle, sbulletin, isDeleted) values('�K��', '���i:�ȯ�Q���K��', 0);

insert into Post(postTitle, content, userID, sboardID, isDeleted) values('���J�򥻮��}���n', '<p>�ڬOGamerBee�A�Ա��Ь�YT<p>', 1, 1, 0);
insert into Post(postTitle, content, userID, sboardID, isDeleted) values('�ԧƼw�@�s�ڭ��F', '<p>�ڬO�۪o���A�d���Ű��ư�<p>', 2, 1, 0);

insert into Replies(replyContent, userID, postID, isDeleted) values('<p>�ڬO�۪o���A2MP�u���ӶW�L�F<p>', 2, 1, 0);
insert into Replies(replyContent, userID, postID, isDeleted) values('<p>�ڬOGamerBee�A�Ӫ����J�F�a<p>', 1, 1, 0);
insert into Replies(replyContent, userID, postID, isDeleted) values('<p>�S�O�ڥ۪o���A�d���Ŵ������X�Ĵ�<p>', 2, 2, 0);
insert into Replies(replyContent, userID, postID, isDeleted) values('<p>�S�O��GamerBee�A�O�̤F�X�Ĵ�<p>', 1, 2, 0);

insert into Comment(content, userID, targetType, postID, replyID, isDeleted) values('�ڬOGamerBee', 1, 'Posts', 1, null, 0);
insert into Comment(content, userID, targetType, postID, replyID, isDeleted) values('�ڬO�۪o��', 2, 'Replies', null, 1, 0);


drop table Comment;
drop table Replies;
drop table Post;
drop table Sboards;
drop table [User];
