CREATE TABLE person (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        username VARCHAR(50) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        password_hash VARCHAR(255) NOT NULL,
                        balance DECIMAL(19,2) NOT NULL,
                        role ENUM ('ADMIN','USER') NOT NULL,
                        PRIMARY KEY (id),
                        UNIQUE (username),
                        UNIQUE (email)
);

CREATE TABLE transaction (
                             id BIGINT NOT NULL AUTO_INCREMENT,
                             account ENUM ('BANK','CASH') NOT NULL,
                             amount DECIMAL(19,2) NOT NULL,
                             date_created DATETIME(6),
                             description VARCHAR(255),
                             type ENUM ('EXPENSE','INCOME') NOT NULL,
                             user_id BIGINT NOT NULL,
                             PRIMARY KEY (id),
                             CONSTRAINT fk_transaction_user
                                 FOREIGN KEY (user_id) REFERENCES person(id)
);
