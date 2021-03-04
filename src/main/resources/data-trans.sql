DROP TABLE IF EXISTS message cascade;
DROP TABLE IF EXISTS client_folder cascade;

CREATE TABLE message (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              date DATE,
                              author_name VARCHAR(250),
                              content VARCHAR(250),
                              channel VARCHAR(250)
);

CREATE TABLE client_folder (
                         id INT AUTO_INCREMENT  PRIMARY KEY,
                         client_name VARCHAR(250),
                         opened_date DATE,
                         reference VARCHAR(250)
);