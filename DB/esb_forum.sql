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
--假資料方面畫面呈現，假的使用者無法登入，因密碼是直接存的沒加密，完整功能需從註冊開始--
insert into [User](userName, userNickname, email, phoneNumber, password, biography) values('向玉麟', 'GamerBee', 'bee@gmail.com', '0912345678', 'bbb666',  '還敢跳啊');
insert into [User](userName, userNickname, email, phoneNumber, password, biography) values('林立偉', 'OilKing', 'oil@gmail.com', '0987654321', 'kkk777',  '角落自殺式');

--Sboards一定要先加假資料，因目前先寫死2個--
insert into Sboards(sboardTitle, sbulletin, isDeleted) values('快打旋風', '公告:僅能討論快打旋風', 0);
insert into Sboards(sboardTitle, sbulletin, isDeleted) values('鐵拳', '公告:僅能討論鐵拳', 0);

insert into Post(postTitle, content, userID, sboardID, isDeleted) values('路克基本拳腳指南', '<p>我是GamerBee，詳情請看YT<p>', 1, 1, 0);
insert into Post(postTitle, content, userID, sboardID, isDeleted) values('拉希德昇龍我哭了', '<p>我是石油王，卡普空做事啊<p>', 2, 1, 0);

insert into Replies(replyContent, userID, postID, isDeleted) values('<p>我是石油王，2MP真的太超過了<p>', 2, 1, 0);
insert into Replies(replyContent, userID, postID, isDeleted) values('<p>我是GamerBee，該玩路克了吧<p>', 1, 1, 0);
insert into Replies(replyContent, userID, postID, isDeleted) values('<p>又是我石油王，卡普空敢不敢出薩斯<p>', 2, 2, 0);
insert into Replies(replyContent, userID, postID, isDeleted) values('<p>又是我GamerBee，別傻了出薩斯<p>', 1, 2, 0);

insert into Comment(content, userID, targetType, postID, replyID, isDeleted) values('我是GamerBee', 1, 'Posts', 1, null, 0);
insert into Comment(content, userID, targetType, postID, replyID, isDeleted) values('我是石油王', 2, 'Replies', null, 1, 0);


drop table Comment;
drop table Replies;
drop table Post;
drop table Sboards;
drop table [User];
