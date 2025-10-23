CREATE TABLE category
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE price
(
    id  BIGINT AUTO_INCREMENT NOT NULL,
    abc VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    CONSTRAINT pk_price PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    c_id          BIGINT NULL,
    price_id      BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE product_orders
(
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL
);

CREATE TABLE test_model
(
    id INT NOT NULL,
    CONSTRAINT pk_testmodel PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_C FOREIGN KEY (c_id) REFERENCES category (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_PRICE FOREIGN KEY (price_id) REFERENCES price (id);

ALTER TABLE product_orders
    ADD CONSTRAINT fk_proord_on_order FOREIGN KEY (order_id) REFERENCES `order` (id);

ALTER TABLE product_orders
    ADD CONSTRAINT fk_proord_on_product FOREIGN KEY (product_id) REFERENCES product (id);