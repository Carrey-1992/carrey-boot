DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);


DROP TABLE IF EXISTS tb_user;

CREATE TABLE tb_user (
    id char(32) NOT NULL,
    user_name varchar(32) DEFAULT NULL,
    password varchar(32) DEFAULT NULL,
    name varchar(32) DEFAULT NULL,
    age int(10) DEFAULT NULL,
    sex int(2) DEFAULT NULL,
    birthday date DEFAULT NULL,
    created datetime DEFAULT NULL,
    updated datetime DEFAULT NULL,
    PRIMARY KEY (id)
);

