DROP TABLE IF EXISTS TRANSACTION_DETAILS;
DROP TABLE IF EXISTS TRANSACTION;

CREATE SEQUENCE txn_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE txn_details_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE TRANSACTION (
                             trnsctn_id INT DEFAULT NEXT VALUE FOR txn_seq PRIMARY KEY,
                             cust_name VARCHAR(40) NOT NULL,
                             amount NUMERIC NOT NULL,
                             trnsctn_date VARCHAR(10) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE TRANSACTION_DETAILS (
                                     id INT DEFAULT NEXT VALUE FOR txn_details_seq PRIMARY KEY,
                                     trnsctn_id INT NOT NULL,
                                     json_details TEXT,

                                     CONSTRAINT fk_transaction
                                         FOREIGN KEY (trnsctn_id)
                                             REFERENCES TRANSACTION(trnsctn_id)
                                             ON DELETE CASCADE
);
--spring.sql.init.mode=embedded (default)