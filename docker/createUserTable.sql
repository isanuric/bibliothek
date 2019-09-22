CREATE TABLE bib_users (
  userid int(10) NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (userid)
);

CREATE TABLE authorities (
  userid int(10) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (userid) REFERENCES bib_users(userid)
);

CREATE UNIQUE INDEX ix_auth_userId on authorities (userId, authority);