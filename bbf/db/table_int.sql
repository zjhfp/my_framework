CREATE TABLE TB_USER (
	ID varchar(36) NOT NULL,
	REAL_NAME varchar(255) DEFAULT NULL,
	EMAIL varchar(255) DEFAULT NULL,
	PHONE varchar(255) DEFAULT NULL,
	LOGIN_NAME varchar(255) DEFAULT NULL,
	PASSWORD varchar(255) DEFAULT NULL,
	LAST_LOGIN_DATE datetime DEFAULT NULL,
	CREATE_DATE datetime DEFAULT NULL,
	LAST_MODIFIED_DATE datetime DEFAULT NULL,
	IS_LOCKED tinyint(1) DEFAULT NULL,
	IS_ADMIN tinyint(1) DEFAULT NULL,
	PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;