CREATE TABLE USER
(
	user_id NUMBER(19) IDENTITY PRIMARY KEY,
	user_email VARCHAR2(50) NOT NULL,
	username VARCHAR2(50) NOT NULL,
	user_password VARCHAR2(64) NOT NULL
);