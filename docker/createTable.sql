
CREATE TABLE book (
 id int(10) NOT NULL AUTO_INCREMENT,
 name varchar(80) NOT NULL,
 autor varchar(80) NOT NULL,
 isbn int(50) NOT NULL,
 status int (10) NOT NULL DEFAULT 1,
 timestamp TIMESTAMP,
 PRIMARY KEY (id) );
