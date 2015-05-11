CREATE TABLE t_account (
  account_id int(11) NOT NULL auto_increment COMMENT '�û�ID',
  account_nickName VARCHAR (32) NOT NULL COMMENT '�û��ǳ�',
  account_email VARCHAR (32) NOT NULL COMMENT '�û�����',
  account_password VARCHAR (32) NOT NULL COMMENT '�û�����',
  account_age VARCHAR (6) DEFAULT "��" COMMENT '�û�����',
  account_introduction VARCHAR (1024) DEFAULT "" COMMENT '�û����Ҽ��',
  account_location_thumb VARCHAR (1024) DEFAULT "",
  account_registerDate DATE DEFAULT "" COMMENT '�û�ע������',
  account_loginDate DATE DEFAULT "" COMMENT '��¼����',
  account_uploadImagesIds VARCHAR (10240) DEFAULT "" COMMENT '�û��ϴ�ͼƬ��Id����',
  account_subscribeIds VARCHAR (10240) DEFAULT "" COMMENT '�û����������û���Id����',
  account_privateLetterIds VARCHAR (10240) DEFAULT "" COMMENT '�û����ܵ�˽��Id����',
  PRIMARY KEY (account_id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE t_image (
  image_id int(11) NOT NULL auto_increment COMMENT 'ͼƬId',
  image_title VARCHAR (64) Not NULL DEFAULT "" COMMENT 'ͼƬ����',
  image_describe VARCHAR (1024) NOT NULL DEFAULT "" COMMENT 'ͼƬ����',
  image_cataloge VARCHAR (64) NOT NULL DEFAULT "" COMMENT 'ͼƬ���',
  image_location VARCHAR (1024) NO NULL COMMENT 'ͼƬ����ڷ�������λ��',
  image_uploadDate DATE NO NULL COMMENT '�ϴ���ʱ��',
  image_isChecked VARCHAR (12) COMMENT '�Ƿ����',
  account_id int(11) NOT NULL COMMENT '�ϴ�ͼƬ�û���Id',
  image_commentIds VARCHAR (10240) NOT NULL DEFAULT "" COMMENT 'ͼƬ������Id����',
  image_isAutoDelete VARCHAR (12) NOT NULL COMMENT '�Ƿ������ĺ󼴷�',
  image_autoDeleteTime DATE NOT NULL COMMENT '�ĺ󼴷ٵ�ʱ��',
  PRIMARY KEY (image_id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;