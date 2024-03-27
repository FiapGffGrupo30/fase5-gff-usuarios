CREATE SEQUENCE IF NOT EXISTS Customer_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE Customer
(
    id           BIGINT NOT NULL,
    fullName     VARCHAR(100),
    nickName     VARCHAR(50),
    document     VARCHAR(14),
    email        VARCHAR(50),
    phone        VARCHAR(14),
    street       VARCHAR(255),
    number       VARCHAR(255),
    neighborhood VARCHAR(255),
    city         VARCHAR(255),
    state        VARCHAR(255),
    zipCode      VARCHAR(255),
    createdAt    TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

ALTER TABLE Customer
    ADD CONSTRAINT uc_customer_document UNIQUE (document);

INSERT INTO Customer
VALUES (nextval('Customer_seq'), 'Marcus Lima', 'Marcus', '99999999900', 'marcus@gff.com', '79999999991',
        'Rua José Santos', '55', 'Suiça', 'Cristovão', 'SE', '49000000', CURRENT_TIMESTAMP);

