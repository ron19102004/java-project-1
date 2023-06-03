create table _user_details(
    id bigint primary key auto_increment,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    phoneNumber varchar(255) null,
    address text null,
    email varchar(255) not null unique,
    deleted bool default false
)
create table _user(
	id bigint primary key auto_increment,
	id_user_details bigint unique,
	role varchar(255) default "user",
	username varchar(255) not null unique,
	password_ varchar(255) not null,
	deleted bool default false,
	constraint fk_us_usdt foreign key (id_user_details) references _user_details(id)
)

DELIMITER $$
CREATE PROCEDURE create_user_details(
    IN firstName varchar(255),
    IN lastName varchar(255),
    IN phoneNumber varchar(255),
    IN address text,
    IN email varchar(255)
)
BEGIN
    INSERT INTO `_user_details` (`firstName`, `lastName`, `phoneNumber`, `address`, `email`)
    VALUES (firstName, lastName, phoneNumber, address, email);
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE create_user(
    IN id_user_details BIGINT,
    IN username VARCHAR(255),
    IN p_password VARCHAR(255)
)
BEGIN
    INSERT INTO `_user` (id_user_details, username, password_)
    VALUES (id_user_details, username, p_password);
END ;$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE create_user(
    IN id_user_details BIGINT,
    IN username VARCHAR(255),
    IN password VARCHAR(255)
)
BEGIN
    INSERT INTO `_user` (id_user_details, username, password)
    VALUES (id_user_details, username, password);
END ;$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE findUserDetailsByEmail(
   IN p_email varchar(255),
   OUT p_id bigint,
   OUT p_firstName varchar(255),
   OUT p_lastName varchar(255),
   OUT p_phoneNumber varchar(255),
   OUT p_address text
)
BEGIN
    SELECT id, firstName, lastName, phoneNumber, address
    into p_id, p_firstName, p_lastName, p_phoneNumber, p_address
    FROM `_user_details` WHERE email = p_email and deleted = false;
END ;$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE findUserByUsername(
   IN p_username varchar(255),
   OUT p_id bigint,
   OUT p_id_user_details bigint,
   OUT p_password varchar(255)
)
BEGIN
    SELECT id,id_user_details , password_
    into p_id ,p_id_user_details,p_password
    FROM `_user` WHERE username = p_username and deleted = false;
END ;$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE findUserDetailsById(
   IN p_id bigint,
   OUT p_firstName varchar(255),
   OUT p_lastName varchar(255),
   OUT p_phoneNumber varchar(255),
   OUT p_address text,
   OUT p_email varchar(255)
)
BEGIN
    SELECT `firstName`, `lastName`, `phoneNumber`, `address`, `email`
    into p_firstName, p_lastName, p_phoneNumber, p_address, p_email
    FROM `_user_details` WHERE id = p_id and deleted = false;
END ;$$
DELIMITER ;
