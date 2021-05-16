DROP TABLE IF EXISTS goods;
CREATE TABLE goods
(
    id                INT (10) AUTO_INCREMENT NOT NULL,
    brand             VARCHAR(128)  DEFAULT '' NOT NULL,
    model             VARCHAR(128)  DEFAULT '' NOT NULL,
    `desc`            VARCHAR(1024) DEFAULT '' NOT NULL,
    `count`           INT (10) DEFAULT 0 NOT NULL,
    goods_category_id INT (10) DEFAULT 0 NOT NULL,
    create_time       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    update_time       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS goods_category;
CREATE TABLE goods_category
(
    id          INT (10) AUTO_INCREMENT NOT NULL,
    `name`      VARCHAR(128) DEFAULT '' NOT NULL,
    create_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE goods
    ADD CONSTRAINT category_of_goods FOREIGN KEY (goods_category_id) REFERENCES goods_category (id);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    id          INT (10) AUTO_INCREMENT NOT NULL,
    username    VARCHAR(128) NOT NULL,
    `password`  VARCHAR(128) NOT NULL,
    role        ENUM ('ROLE_USER', 'ROLE_ADMIN') NOT NULL,
    is_active   TINYINT (1) DEFAULT 1 NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);