CREATE TABLE category(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) not null,
)
CREATE TABLE product (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    id_user_created bigint,
    id_category bigint,
    name varchar(255) not null,
    description text null,
    quantity bigint not null,
    img text null,
    price bigint not null,
    constraint fk_p_u foreign key (id_user_created) references _user(id),
    constraint fk_p_c foreign key (id_category) references category(id)
)
--for category
DELIMITER $$
CREATE PROCEDURE findCategoryById(
   IN p_id bigint,
   OUT p_name varchar(255)
)
BEGIN
    SELECT `name`
    into p_name
    FROM `category` WHERE id = p_id;
END ;$$
DELIMITER ;

--for product
DELIMITER $$
CREATE PROCEDURE create_product(
   IN p_id_user_created bigint,
   IN p_id_category bigint,
   IN p_name varchar(255),
   IN p_description text,
   IN p_quantity bigint,
   IN p_img text,
   IN p_price bigint
)
BEGIN
    INSERT INTO product (id_user_created, id_category, name, description, quantity, img, price)
    VALUES (p_id_user_created, p_id_category, p_name, p_description, p_quantity, p_img, p_price);
END ;$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE findProductById(
    IN p_id bigint,
    OUT p_id_user_created bigint,
    OUT p_id_category bigint,
    OUT p_name varchar(255),
    OUT p_description text,
    OUT p_quantity bigint,
    OUT p_img text,
    OUT p_price bigint
)
BEGIN
    SELECT `id_user_created`, `id_category`, `name`, `description`, `quantity`, `img`, `price`
    into p_id_user_created, p_id_category, p_name, p_description, p_quantity, p_img, p_price
    FROM `product` WHERE id = p_id;
END ;$$
DELIMITER ;