CREATE TABLE kilometer_States
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
	car_id BIGINT NOT NULL,
    km INT NULL,
    date date NULL,
	CONSTRAINT pk_kilometer_States PRIMARY KEY (id)
);

CREATE TABLE sellers
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_sellers PRIMARY KEY (id)
);

CREATE TABLE cars
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
	seller_id BIGINT NULL,
    brand         VARCHAR(255) NULL,
    model          VARCHAR(255) NULL,
    age_In_Years	INT NULL,
    condition_Level	INT NULL,
    car_condition VARCHAR(255) NULL,
    CONSTRAINT pk_cars PRIMARY KEY (id)
);

ALTER TABLE cars
    ADD CONSTRAINT FK_CARS_ON_SELLER FOREIGN KEY (seller_id) REFERENCES sellers (id);

ALTER TABLE kilometer_States
    ADD CONSTRAINT fk_kilometer_States_on_car FOREIGN KEY (car_id) REFERENCES cars (id);