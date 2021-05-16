INSERT INTO goods_category (`name`)
VALUES ('手机'),
       ('电脑'),
       ('手表'),
       ('手环'),
       ('VR');

INSERT INTO goods (brand, model, `desc`, goods_category_id)
VALUES ('HUAWEI', 'Mate 40', '', 1),
       ('HUAWEI', 'Mate 40 Pro', '', 1),
       ('HUAWEI', 'Mate 40 RS', '保时捷版Mate 40', 1),
       ('Apple', 'iPhone 12 Mini', '', 1),
       ('Apple', 'iPhone 12', 'iPhone 11继任者', 1),
       ('Apple', 'iPhone 12 Pro', 'iPhone 11 Pro继任者', 1),
       ('Apple', 'iPhone 12 Pro Max', 'iPhone 11 Pro Max继任者', 1),
       ('Lenovo', 'ThinkBook', '', 2),
       ('Lenovo', 'ThinkPad', '', 2),
       ('Lenovo', '拯救者', '', 2),
       ('Lenovo', '小新', '', 2),
       ('Lenovo', 'YOGA', '', 2),
       ('Lenovo', '天逸', '', 2),
       ('Lenovo', 'GeekPro', '', 2),
       ('Lenovo', 'AIO', '', 2),
       ('Lenovo', '异能者', '', 2),
       ('Apple', 'MacBook Air', '', 2),
       ('Apple', 'MacBook Pro', '', 2),
       ('Apple', 'iMac', '', 2),
       ('Apple', 'iMac Pro', '', 2),
       ('Apple', 'Mac Pro', '', 2),
       ('Apple', 'Mac Mini', '', 2);

INSERT INTO `user` (username, `password`, role)
VALUES ('admin', '$2a$10$VveKpeee4gIL1kHp38e0C.ne5JOuQgWvI3BRY32T2PJAAmal1inSK', 'ROLE_ADMIN'),
       ('user', '$2a$10$Du3iPETwONYHF0NEGBgnWeRWIlt8brGzqsMnAAKxxClgv.lDfV4IC', 'ROLE_USER');