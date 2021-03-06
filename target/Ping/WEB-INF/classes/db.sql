drop table if exists t_checkcode;
CREATE TABLE t_checkcode (
	checkCode_id VARCHAR(32) NOT NULL,
	checkCode_username VARCHAR(198) NOT NULL,
	checkCode_cc VARCHAR(32) NOT NULL,
	checkCode_generateTime DATETIME NOT NULL,
	PRIMARY KEY (checkCode_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index idx_t_checkcode_checkCode_id on t_checkcode(checkCode_id);


drop table if exists t_account;
CREATE TABLE t_account (
  account_id VARCHAR(32) NOT NULL COMMENT '用户Id',
  account_username VARCHAR(198) NOT NULL,
  account_email VARCHAR(198) NOT NULL,
  account_password VARCHAR(198) NOT NULL,
  account_age INT DEFAULT 0,
  account_sex VARCHAR(198) DEFAULT '男',
  account_introduction VARCHAR(1024) DEFAULT "",
  account_thumb VARCHAR(1024) DEFAULT "",
  account_registerDate DATETIME NOT NULL,
  account_lastLoginDate DATETIME,
  account_lock BOOLEAN DEFAULT TRUE,
  account_credit INT DEFAULT 0,
  account_fans INT DEFAULT 0,
  account_subscribeImageIds varchar(1024),
  account_collectImageIds VARCHAR(1024),
  account_subscribeIds VARCHAR(1024) DEFAULT "",
  account_publishIds VARCHAR(1024) DEFAULT "",
  account_salt VARCHAR(1024) DEFAULT "",
  account_roleIds VARCHAR(1024) DEFAULT "",
  PRIMARY KEY (account_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index idx_t_account_account_id on t_account(account_id);

INSERT INTO t_account VALUES (1, 'taylor swift', 'tyls@163.com', '12345678', 23, '女', 'Hello everybody, i am tl, so i got a Dj! fisrst...',
                                'static/image/user/taylor_thumb.jpg', now(), now(), FALSE, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO t_account VALUES (2, '黄家驹', 'beyond@fox.com', '12345678', 99, '男', 'beyond乐队主唱，摇滚乐...',
                                'static/image/user/hjj_thumb.jpg', now(), now(), FALSE, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO t_account VALUES (3, '双杰伦', 'show@fox.com', '12345678', 99, '男', '音乐才子',
                                'static/image/user/pic10.jpg', now(), now(), FALSE, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
                                
drop table if exists t_image;
CREATE TABLE t_image (
  image_id VARCHAR(32) NOT NULL,
  image_title VARCHAR(1024) NOT NULL,
  image_describe VARCHAR(1024),
  image_cataloge VARCHAR(1024) DEFAULT '大自然',
  image_hot INT DEFAULT 0,
  image_location VARCHAR(1024) NOT NULL,
  image_uploadTime DATETIME NOT NULL,
  image_checked BOOLEAN DEFAULT FALSE,
  image_username VARCHAR(198) NOT NULL,
  image_autoDelete BOOLEAN DEFAULT FALSE,
  image_autoDeleteTime DATETIME,
  PRIMARY KEY (image_id)
) ENGINE=innoDB DEFAULT CHARSET=utf8;

create index idx_t_image_image_id on t_image(image_id);
create index idx_t_image_image_username on t_image(image_username);

INSERT INTO t_image VALUES (1, '师大瑶湖', '江西师范大学位于江西省南昌市瑶湖紫阳大道99号，是...', '人文', 1, 'static/image/user/jxnormal.jpg',
                              now(), TRUE, 'taylor swift', FALSE, NULL);
INSERT INTO t_image VALUES (2, '我爱我家', '家居设计， 时尚， 时尚， 醉时尚！', '设计', 1, 'static/image/user/pic1.jpg',
                              now(), TRUE, 'taylor swift', FALSE, NULL);
INSERT INTO t_image VALUES (3, '沙发', '中国制造，美美哒！', '设计', 1, 'static/image/user/pic3.jpg',
                              now(), TRUE, 'taylor swift', FALSE, NULL);
INSERT INTO t_image VALUES (4, '呀呀呀', '合影， 星星， 删', '狂欢', 1, 'static/image/user/p4.jpg',
                              now(), TRUE, 'taylor swift', FALSE, NULL);     
INSERT INTO t_image VALUES (5, '梦', '真真的，佳佳的', '人文', 1, 'static/image/user/img1.jpg',
                              now(), TRUE, '黄家驹', FALSE, NULL);
INSERT INTO t_image VALUES (6, '马', '千里马，动漫是啊，凸(艹皿艹 )', '人文', 1, 'static/image/user/img2.jpg',
                              now(), TRUE, '黄家驹', FALSE, NULL);
INSERT INTO t_image VALUES (7, '山城', '公分修毛，喜洋洋惠爱玲', '人文', 1, 'static/image/user/img3.jpg',
                              now(), TRUE, '黄家驹', FALSE, NULL);
INSERT INTO t_image VALUES (8, '昆明', '迪士尼，冰雪奇缘，美高美', '人文', 1, 'static/image/user/img4.jpg',
                              now(), TRUE, '黄家驹', FALSE, NULL);                              
                           

drop table if exists t_cataloge;
CREATE TABLE t_cataloge (
  cataloge_id VARCHAR(32) NOT NULL,
  cataloge_name VARCHAR(1024) NOT NULL,
  PRIMARY KEY (cataloge_id)
) ENGINE=innoDB DEFAULT CHARSET=utf8;

INSERT INTO t_cataloge VALUES (1, '人文');
INSERT INTO t_cataloge VALUES (2, '狂欢');
INSERT INTO t_cataloge VALUES (3, '设计');
INSERT INTO t_cataloge VALUES (4, '静物');

drop table if exists t_comment;
CREATE TABLE t_comment (
  comment_id VARCHAR(32) NOT NULL,
  comment_date DATETIME NOT NULL,
  comment_approve INT DEFAULT 0,
  comment_content VARCHAR(1024) NOT NULL,
  comment_username VARCHAR(198) NOT NULL,
  comment_imageId INT NOT NULL,
  PRIMARY KEY (comment_id)
) ENGINE=innoDB DEFAULT CHARSET=utf8;

create index idx_t_comment_comment_id on t_comment(comment_id);
create index idx_t_comment_comment_username on t_comment(comment_username);
create index idx_t_comment_comment_imageId on t_comment(comment_imageId);

INSERT INTO t_comment VALUES (1, now(), 1, '艾欧，次噢， 这是我的评论我的态度', 'taylor swift', 1);

drop table if exists t_role;
create table t_role (
  role_id VARCHAR(32) NOT NULL,
  role_role varchar(127),
  role_description varchar(127),
  role_resourceIds varchar(127),
  role_available BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (role_id)
) ENGINE=innoDB DEFAULT CHARSET=utf8;
create index idx_t_role_resource_ids on t_role(role_resourceIds);

insert into t_role values('1', 'admin', '超级管理员', '21,31,41', true);

drop table if exists t_resource;
create table t_resource (
  resource_id VARCHAR(32) NOT NULL,
  resource_name VARCHAR(127),
  resource_permission VARCHAR(127),
  resource_available BOOLEAN DEFAULT FALSE,
  resource_url VARCHAR(1024),
  PRIMARY KEY (resource_id)
) ENGINE=innoDB DEFAULT CHARSET=utf8;

insert into t_resource values('1', '资源', '', true, null);

insert into t_resource values('21', '用户管理', 'user:*', true, null);
insert into t_resource values('22', '用户新增', 'user:create', true, null);
insert into t_resource values('23', '用户修改', 'user:update', true, null);
insert into t_resource values('24', '用户删除', 'user:delete', true, null);
insert into t_resource values('25', '用户查看', 'user:view', true, null);

insert into t_resource values('31', '资源管理', 'resource:*', true, null);
insert into t_resource values('32', '资源新增', 'resource:create', true, null);
insert into t_resource values('33', '资源修改', 'resource:update', true, null);
insert into t_resource values('34', '资源删除', 'resource:delete', true, null);
insert into t_resource values('35', '资源查看', 'resource:view', true, null);

insert into t_resource values('41', '角色管理', 'role:*', true, null);
insert into t_resource values('42', '角色新增', 'role:create', true, null);
insert into t_resource values('43', '角色修改', 'role:update', true, null);
insert into t_resource values('44', '角色删除', 'role:delete', true, null);
insert into t_resource values('45', '角色查看', 'role:view', true, null);




