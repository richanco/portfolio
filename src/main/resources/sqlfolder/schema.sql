DROP TABLE IF EXISTS tweet_table;

CREATE TABLE tweet_table
(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(100),
   tweet VARCHAR(256),
   PRIMARY KEY(id)
);