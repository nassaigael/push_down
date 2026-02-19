CREATE TABLE tax_config
(
    id    SERIAL PRIMARY KEY,
    label VARCHAR       NOT NULL,
    rate  NUMERIC(5, 2) NOT NULL
);

INSERT INTO tax_config (label, rate)
VALUES ('TVA STANDARD', 20);