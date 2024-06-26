CREATE TABLE customer.Customer
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    fullName     VARCHAR(100),
    nickName     VARCHAR(50),
    birthDate    date,
    document     VARCHAR(14),
    email        VARCHAR(50),
    phone        VARCHAR(50),
    createdAt    TIMESTAMP WITHOUT TIME ZONE,
    street       VARCHAR(255),
    number       VARCHAR(255),
    neighborhood VARCHAR(255),
    city         VARCHAR(255),
    state        VARCHAR(255),
    zipCode      VARCHAR(255),
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE customer.customer_wallets
(
    customer_id    BIGINT NOT NULL,
    paymentMethod  VARCHAR(255),
    cardNumber     VARCHAR(255),
    cardName       VARCHAR(255),
    expirationDate VARCHAR(255),
    securityCode   VARCHAR(255),
    main           BOOLEAN,
    createdAt      TIMESTAMP WITHOUT TIME ZONE
);

ALTER TABLE customer.Customer
    ADD CONSTRAINT uc_customer_document UNIQUE (document);

ALTER TABLE customer.customer_wallets
    ADD CONSTRAINT fk_customer_wallets_on_customer FOREIGN KEY (customer_id) REFERENCES customer.Customer (id);

-- Inserir Usuário 1
INSERT INTO Customer (fullName, nickName, birthDate, document, email, phone, createdAt, street, number, neighborhood,
                      city, state, zipCode)
VALUES ('João Silva', 'Jão', '1990-05-15', '12345678900', 'joao.silva@example.com', '(11) 98765-4321',
        '2024-03-28 10:00:00', 'Rua da Amizade', '123', 'Centro', 'São Paulo', 'São Paulo', '12345-6789');

INSERT INTO customer_wallets (customer_id, PaymentMethod, cardNumber, cardName, expirationDate, securityCode, main,
                              createdAt)
VALUES (1, 'Cartão de Crédito', '1234 5678 9012 3456', 'João Silva', '12/26', '123', TRUE, '2024-03-28 10:00:00');


-- Inserir Usuário 2
INSERT INTO Customer (fullName, nickName, birthDate, document, email, phone, createdAt, street, number, neighborhood,
                      city, state, zipCode)
VALUES ('Maria Souza', 'Mary', '1985-10-20', '98765432100', 'maria.souza@example.com', '(21) 98765-1234',
        '2024-03-28 11:00:00', 'Avenida das Flores', '456', 'Jardim Botânico', 'Rio de Janeiro', 'Rio de Janeiro',
        '54321-987');

INSERT INTO customer_wallets (customer_id, PaymentMethod, cardNumber, cardName, expirationDate, securityCode, main,
                              createdAt)
VALUES (2, 'Cartão de Débito', '9876 5432 1098 7654', 'Maria Souza', '09/25', '456', TRUE, '2024-03-28 11:00:00');


-- Inserir Usuário 3
INSERT INTO Customer (fullName, nickName, birthDate, document, email, phone, createdAt, street, number, neighborhood,
                      city, state, zipCode)
VALUES ('Carlos Oliveira', 'Carlão', '1978-03-05', '45678912300', 'carlos.oliveira@example.com', '(31) 98765-6789',
        '2024-03-28 12:00:00', 'Praça da Liberdade', '789', 'Liberdade', 'Belo Horizonte', 'Minas Gerais', '98765-432');

INSERT INTO customer_wallets (customer_id, PaymentMethod, cardNumber, cardName, expirationDate, securityCode, main,
                              createdAt)
VALUES (3, 'Transferência Bancária', NULL, NULL, NULL, NULL, FALSE, '2024-03-28 12:00:00');


