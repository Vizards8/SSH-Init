-- Check if the database exists and drop it if it does
DROP DATABASE IF EXISTS SSH_Init;

-- Create a new database
CREATE DATABASE SSH_Init;
USE SSH_Init;

-- Dropping the tables if they exist
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `permission`;
DROP TABLE IF EXISTS `order`;

-- Creating the 'user' table
CREATE TABLE `user`
(
    `user_id`  BIGINT       NOT NULL AUTO_INCREMENT,
    `email`    VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role`     INT          NOT NULL, # 0: buyer, 1:seller
    `username` VARCHAR(255),
    PRIMARY KEY (`user_id`)
);

-- Creating the 'permission' table
CREATE TABLE `permission`
(
    `permission_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `value`         VARCHAR(255) NOT NULL,
    `user_id`       BIGINT       NOT NULL,
    PRIMARY KEY (`permission_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

-- Creating the 'order' table
CREATE TABLE `order`
(
    `order_id`     BIGINT AUTO_INCREMENT PRIMARY KEY,
    `date_placed`  DATETIME(6)  NOT NULL,
    `order_status` VARCHAR(255) NOT NULL,
    `user_id`      BIGINT       NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

-- Inserting dummy data into 'user' table
INSERT INTO `user` (`email`, `password`, `role`, `username`)
VALUES ('user1@gmail.com', 'user1', 1, 'user1'),
       ('user2@gmail.com', 'user2', 2, 'user2');

-- Inserting dummy data into 'permission' table
INSERT INTO `permission` (`value`, `user_id`)
VALUES ('read', 1),
       ('write',1);

-- Inserting dummy data into 'order' table
INSERT INTO `order` (`date_placed`, `order_status`, `user_id`)
VALUES ('2024-04-09 12:00:00', 'Pending', 1),
       ('2024-04-10 13:00:00', 'Shipped', 1),
       ('2024-04-11 14:00:00', 'Delivered', 2);