DROP TABLE IF EXISTS quotes;

CREATE TABLE quotes(
    id int NOT NULL AUTO_INCREMENT,
    symbol varchar(8) NOT NULL,
    price float NOT NULL,
    volume int NOT NULL,
    dateTraded timestamp NOT NULL,
    PRIMARY KEY (id)
);