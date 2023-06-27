CREATE TABLE ACCOUNT
(
    ID             INTEGER PRIMARY KEY,
    ACCOUNT_NUMBER VARCHAR(50) NOT NULL,
    AMOUNT         DECIMAL     NOT NULL,
    STATUS         VARCHAR(50) NOT NULL,
    TYPE           VARCHAR(50) NOT NULL,
    CUSTOMER_ID    INTEGER
);

CREATE TABLE CUSTOMER
(
    ID                  INTEGER PRIMARY KEY,
    IDENTIFICATION      VARCHAR(50) NOT NULL,
    NAME                VARCHAR(50) NOT NULL,
    LASTNAME            VARCHAR(50) NOT NULL,
    IDENTIFICATION_TYPE VARCHAR(50) NOT NULL
);

CREATE TABLE TRANSACTION
(
    ID               INTEGER PRIMARY KEY,
    TRANSACTION_TYPE VARCHAR(50) NOT NULL,
    AMOUNT           DECIMAL     NOT NULL,
    ACCOUNT_ID       INTEGER     NOT NULL
);