CREATE TABLE bib_users (
  userId int(10) NOT NULL,
  userName VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (userName)
);

CREATE TABLE authorities (
  userName VARCHAR(50) NOT NULL ,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (userName) REFERENCES bib_users(userName)
);

CREATE UNIQUE INDEX ix_auth_userName on authorities (userName, authority);