CREATE SEQUENCE IF NOT EXISTS Identity_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE Identity
(
    id          BIGINT NOT NULL,
    username    VARCHAR(255),
    password    VARCHAR(255),
    role        VARCHAR(255),
    customer_id BIGINT,
    createdAt   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_identity PRIMARY KEY (id)
);

ALTER TABLE Identity
    ADD CONSTRAINT uc_identity_customer UNIQUE (customer_id);

ALTER TABLE Identity
    ADD CONSTRAINT uc_identity_username UNIQUE (username);

ALTER TABLE Identity
    ADD CONSTRAINT FK_IDENTITY_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES Customer (id);

INSERT INTO Identity
VALUES (nextval('Identity_seq'), 'joao', '$2a$10$zmyGfm.SynJ/pf4NPwXHB.mmUXyzxRAld.um0ZTlryqg1W.qqdrZy', 'CUSTOMER',
        1, current_timestamp);

INSERT INTO Identity
VALUES (nextval('Identity_seq'), 'mary', '$2a$10$zmyGfm.SynJ/pf4NPwXHB.mmUXyzxRAld.um0ZTlryqg1W.qqdrZy', 'CUSTOMER',
        2, current_timestamp);

INSERT INTO Identity
VALUES (nextval('Identity_seq'), 'carlao', '$2a$10$zmyGfm.SynJ/pf4NPwXHB.mmUXyzxRAld.um0ZTlryqg1W.qqdrZy', 'CUSTOMER',
        3, current_timestamp);

