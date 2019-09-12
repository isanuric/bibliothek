CREATE TABLE bib_users (
  userId int(10) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (userId)
);

CREATE TABLE authorities (
  userId int(10) NOT NULL ,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (userId) REFERENCES bib_users(userId)
);

CREATE UNIQUE INDEX ix_auth_userId on authorities (userId,authority);