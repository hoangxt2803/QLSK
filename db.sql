drop database eventdb3;
create database eventdb3;
use eventdb3;

CREATE TABLE IF NOT EXISTS roles (
   id INT(11) NOT NULL AUTO_INCREMENT,
   name VARCHAR(30) NOT NULL UNIQUE,
   description VARCHAR(255),
   CONSTRAINT pk_role PRIMARY KEY(id)
);

create table IF NOT EXISTS users(
	id INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    fullname VARCHAR(100),
    password VARCHAR(100),
    avatar VARCHAR(255),
    phone VARCHAR(20),
    address VARCHAR(255),
    birthday varchar(25),
    gender INT(11) ,
    role_id INT(11)  NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY(id),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);
create table IF NOT EXISTS services(
	id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    banner varchar(255),
    description VARCHAR(255),
    content longtext,
    CONSTRAINT pk_service PRIMARY KEY(id)
);


create table IF NOT EXISTS events(
	id INT(11) NOT NULL AUTO_INCREMENT,
    address varchar(255) DEFAULT NULL,
    banner varchar(255) DEFAULT NULL,
    content longtext,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description varchar(255) DEFAULT NULL,
    startdate varchar(25) DEFAULT NULL,
    enddate varchar(25) DEFAULT NULL,
    starttime varchar(25) DEFAULT NULL,
    title varchar(255) DEFAULT NULL,
    status INT(11) ,
    service_id INT(11)  NOT NULL,
    user_id INT(11)  NOT NULL,
    CONSTRAINT pk_event PRIMARY KEY(id),
    CONSTRAINT fk_events_services FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE,
    CONSTRAINT fk_events_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    
    
);

create table IF NOT EXISTS news(
	id INT(11) NOT NULL AUTO_INCREMENT,
    title varchar(255) DEFAULT NULL,
    banner varchar(255) DEFAULT NULL,
    content longtext,
    user_id INT(11),
	CONSTRAINT pk_user PRIMARY KEY(id)
);	


