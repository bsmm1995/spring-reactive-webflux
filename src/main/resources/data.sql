INSERT INTO CUSTOMER (ID, IDENTIFICATION, NAME, LASTNAME, IDENTIFICATION_TYPE)
VALUES (1, '1900875327', 'Bladimir', 'Minga', 'CEDULA');

INSERT INTO ACCOUNT (ID, ACCOUNT_NUMBER, AMOUNT, STATUS, TYPE, CUSTOMER_ID)
VALUES (1, '123-1', 100, 'ACTIVE', 'SAVINGS', 1);

INSERT INTO TRANSACTION (ID, TRANSACTION_TYPE, AMOUNT, ACCOUNT_ID)
VALUES (1, 'CREDIT', 10, 1);